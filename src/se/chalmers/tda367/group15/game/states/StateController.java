package se.chalmers.tda367.group15.game.states;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
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

	private boolean pendingFullScreenAction;
	private AppGameContainer gameContainer;
	private Music music;

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
		MenuStateMain mainMenu = new MenuStateMain(
				Constants.GAME_STATE_MAIN_MENU);
		MenuStateOptions optionsMenu = new MenuStateOptions(
				Constants.GAME_STATE_OPTIONS_MENU);
		GameOverState gameOverState = new GameOverState(
				Constants.GAME_STATE_GAME_OVER);

		this.initMusic();
		this.addState(playState);
		this.addState(mainMenu);
		this.addState(optionsMenu);
		this.addState(gameOverState);
		this.enterState(Constants.GAME_STATE_MAIN_MENU);
	}

	public void initMusic() {
		try {
			music = new Music("res/music/menu.aif");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		music.loop();
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
			gameContainer.setShowFPS(false);
			gameContainer.setIcons(new String[] { "res/menu/ContainerIcon.tga",
					"res/menu/ContainerTabIcon.tga",
					"res/menu/ContainerTabIcon2.tga" });
			this.setAppFullScreen(fullscreen);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * Also checks for alt+enter to temporary change between fullscreen and
	 * windowed. IF PLAYING THIS CAN BREAK YOUR GAME!
	 */
	public final void update(GameContainer container, int delta)
			throws SlickException {
		super.update(container, delta);

		// Alt + enter change between windowed and fullscreen.
		if (container.getInput().isKeyDown(Input.KEY_LALT)
				&& container.getInput().isKeyDown(Input.KEY_ENTER)) {
			pendingFullScreenAction = true;
		} else if (pendingFullScreenAction
				&& !container.getInput().isKeyDown(Input.KEY_ENTER)) {
			pendingFullScreenAction = false;
			this.setAppFullScreen(!container.isFullscreen());
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
	public AppGameContainer getAppGameContainer() {
		return gameContainer;
	}

	/**
	 * Enter a new State and set appropriate cursor.
	 */
	@Override
	public void enterState(int ID) {
		super.enterState(ID);

		try {
			if (ID == Constants.GAME_STATE_PLAYING) {
				this.getContainer().setMouseCursor("res/images/crosshair.png",
						16, 16);
			} else {
				this.getContainer().setMouseCursor("res/menu/cursor.png", 0, 0);
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @return true if close is ok
	 */
	@Override
	public boolean closeRequested() {
		music.release();
		return true;
	}
}