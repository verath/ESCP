package se.chalmers.tda367.group15.game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.group15.game.controllers.GameController;
import se.chalmers.tda367.group15.game.controllers.SoundEffectsController;
import se.chalmers.tda367.group15.game.controllers.SoundEffectsController.GameMusic;
import se.chalmers.tda367.group15.game.settings.Constants;

/**
 * The playing state of the game (as to be compared to the menu states),
 * forwards slick calls to the gameController.
 * 
 * @author Carl Jansson, Peter, Simon Persson
 * 
 */
class PlayState extends BasicGameState {

	private final int ID;

	private boolean pendingEscpAction;

	/**
	 * A list of state-ids that should be updated when the game ends.
	 */
	private final static int[] gameOverListeningStates = {
			Constants.GAME_STATE_MENU_HIGH_SCORE,
			Constants.GAME_STATE_MENU_MAIN };

	/**
	 * The GameController controlling the game.
	 */
	private final GameController gameController = new GameController();

	/**
	 * Creates a new PlayState.
	 * 
	 * @param ID
	 *            The id used to identify the state.
	 */
	public PlayState(int ID) {
		this.ID = ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		gameController.render(container, game, g);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		gameController.init(container, game);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_COMMA)) {
			Constants.SHOW_BOUNDS = !Constants.SHOW_BOUNDS;
		}

		// Check for esc key
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			pendingEscpAction = true;
		} else if (pendingEscpAction
				&& !container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			pendingEscpAction = false;
			game.enterState(Constants.GAME_STATE_MENU_MAIN);
		}

		gameController.update(container, game, delta);

		boolean[] gameOverStatus = gameController.isGameOver();
		boolean gameOver = gameOverStatus[0];
		boolean gameWon = gameOverStatus[1];
		if (gameOver) {
			// Make sure we notice states that should know about a game over.
			for (int id : gameOverListeningStates) {
				GameState state = game.getState(id);
				if (state instanceof AbstractGameState) {
					((AbstractGameState) state).gameOver(gameWon);
				}
			}

			// Enter the appropriate state for game over (lost or won)
			if (gameWon) {
                ((GameWonState) game.getState(Constants.GAME_STATE_GAME_WON))
                        .setScoreController(gameController.getScoreController());
				game.enterState(Constants.GAME_STATE_GAME_WON);
			} else {
				game.enterState(Constants.GAME_STATE_GAME_LOST);
			}

		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getID() {
		return ID;
	}
}
