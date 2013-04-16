package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.models.GameModel;
import se.chalmers.tda367.group15.game.views.GameView;

/**
 * The main play controller for the slick2d implementation of PsychoHero.
 * 
 * @author Peter, Carl
 * 
 */
public class PlayState extends BasicGameState {
	
	public final int ID;
	/**
	 * The GameView, essentially a view container. Should receive render and
	 * init.
	 */
	private final GameView gameView;

	/**
	 * The GameModel, essentially a model container. Should revive update.
	 */
	private final GameModel gameModel;

	/**
	 * Creates a new PlayState
	 * 
	 * @param gameView The GameView that should receive render and init
	 * @param gameModel The GameModel that should receive update
	 */
	public PlayState(GameView gameView, GameModel gameModel, int id) {
		ID = id;
		this.gameView = gameView;
		this.gameModel = gameModel;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		gameView.init(container);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		gameView.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if ( container.getInput().isKeyDown(Input.KEY_ESCAPE)){
			game.enterState(Constants.MAIN_MENU);
		}
		gameModel.update(container, delta);
	}

	@Override
	public int getID() {
		return ID;
	}

}
