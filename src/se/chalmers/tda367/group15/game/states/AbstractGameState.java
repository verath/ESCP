package se.chalmers.tda367.group15.game.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * A class for cutting down the implementation needed if using BasicGameState.
 * Class taken from tutorial for writing menus in Slick2D Originally posted on
 * http://slick.javaunlimited.net/ by user shiroto. Comments added by Carl
 * Jansson.
 * 
 * @author shiroto
 * @version 1.1
 */
abstract class AbstractGameState extends BasicGameState {

	/**
	 * the container for the game.
	 */
	GameContainer container;

	/**
	 * The controller containing to states.
	 */
	StateBasedGame game;

	/**
	 * The identification for the state.
	 */
	private final int ID;

	/**
	 * input received.
	 */
	Input input;

	/**
	 * Creates a new abstracted game state
	 * 
	 * @param id
	 *            the identification for the state.
	 */
	AbstractGameState(int id) {
		ID = id;
	}

	/**
	 * Initializes the state.
	 */
	@Override
	public final void init(GameContainer container, StateBasedGame game) {
		this.setInput(container.getInput());
		this.container = container;
		this.game = game;
		this.init();
	}

	/**
	 * paint the state.
	 */
	@Override
	public final void render(GameContainer container, StateBasedGame game,
			Graphics g) {
		this.render(g);
	}

	/**
	 * update the state.
	 */
	@Override
	public final void update(GameContainer container, StateBasedGame game,
			int delta) throws SlickException {
		this.update(delta);
	}

	/**
	 * Called when the game is over to allow the states to re-adjust their
	 * current state.
	 * 
	 * @param win
	 *            True if the game was won.
	 */
	public void gameOver(boolean win) {
	}

	/**
	 * init your state.
	 */
	protected abstract void init();

	/**
	 * Paint your state.
	 * 
	 * @param g
	 *            The graphics object used to draw.
	 */
	protected abstract void render(Graphics g);

	/**
	 * update your state.
	 * 
	 * @param delta
	 *            Time passed since last game update call.
	 */
	protected abstract void update(int delta);

	/**
	 * Returns this states ID.
	 * 
	 * @return the state ID.
	 */
	@Override
	public final int getID() {
		return ID;
	}

	/**
	 * set input for this state.
	 */
	public void setInput(Input input) {
		this.input = input;
	}
}
