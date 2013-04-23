package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.group15.game.constants.Constants;

/**
 * a container for the different states.
 * 
 * @author Carl
 * @version 2.0
 */
public class StateController extends StateBasedGame {

	private AppGameContainer gameContainer;

	/**
	 * creates a new StateController
	 * 
	 * @param name
	 *            The name of the window containing the game.
	 */
	public StateController(String name) {
		super(name);
	}

	/**
	 * Initialize different states the game can assume.
	 */
	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		PlayState playState = new PlayState(Constants.GAME_STATE_PLAYING);
		MainMenuState mainMenu = new MainMenuState(
				Constants.GAME_STATE_MAIN_MENU);
		OptionsMenuState optionsMenu = new OptionsMenuState(
				Constants.GAME_STATE_OPTIONS_MENU);

		this.addState(playState);
		this.addState(mainMenu);
		this.addState(optionsMenu);
		this.enterState(Constants.GAME_STATE_MAIN_MENU);
	}

	/**
	 * Create the window in which to place the game!
	 * 
	 * @param fullscreen
	 *            true if you want to create a fullscreen game.
	 */
	public void initAppContainer(boolean fullscreen) {
		ScalableGame scalableGame = new ScalableGame(this,
				Constants.GAME_WIDTH, Constants.GAME_HEIGHT, true);

		try {
			gameContainer = new AppGameContainer(scalableGame);
			gameContainer.setVerbose(Constants.DEBUG);
			gameContainer.setTargetFrameRate(120);
			this.setAppFullScreen(fullscreen);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sett AppContainer to fullscreen or windowed.
	 * 
	 * @param fullscreen
	 *            true to set fullscreen.
	 * @throws SlickException
	 */
	public void setAppFullScreen(boolean fullscreen) throws SlickException {
		if (fullscreen) {
			gameContainer.setDisplayMode(gameContainer.getScreenWidth(),
					gameContainer.getScreenHeight(), true);
		} else {
			gameContainer.setDisplayMode(Constants.GAME_WIDTH,
					Constants.GAME_HEIGHT, false);
		}
	}

	/**
	 * Get the AppGameContainer
	 * 
	 * @return the AppGameContainer in which game is.
	 */
	public AppGameContainer getTheAppContainer() {
		return gameContainer;
	}
	
	@Override
	public void enterState(int ID){
		super.enterState(ID);
		
		try {
			if ( ID == Constants.GAME_STATE_PLAYING ) {
				this.getContainer().setMouseCursor("res/tiles/crosshair.png", 16, 16);
			} else {
				this.getContainer().setMouseCursor("res/menu/cursor.png", 16, 16);
			}
		} catch ( SlickException e ) {
			e.printStackTrace();
		}
	}

}
