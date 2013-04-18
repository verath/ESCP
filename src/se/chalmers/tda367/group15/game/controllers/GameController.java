package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.GameModel;
import se.chalmers.tda367.group15.game.views.GameView;

/**
 * The main controller for the slick2d implementation of PsychoHero.
 * 
 * @author Peter
 * 
 */
public class GameController extends BasicGame {

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
	 * Creates a new GameController
	 * 
	 * @param title
	 *            The title of the game
	 * @param gameView
	 *            The GameView that should receive render and init
	 * @param gameModel
	 *            The GameModel that should receive update
	 */
	public GameController(String title, GameView gameView, GameModel gameModel) {
		super(title);
		this.gameView = gameView;
		this.gameModel = gameModel;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		gameView.render(container, g);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		gameView.init(container);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		gameModel.update(container, delta);
	}

}
