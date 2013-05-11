package se.chalmers.tda367.group15.game.states;

import java.util.prefs.Preferences;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.menu.Button;
import se.chalmers.tda367.group15.game.menu.CheckBox;

/**
 * A state with different settings you can choose between. Class based on
 * tutorial for writing menus in Slick2D Originally posted on
 * http://slick.javaunlimited.net/ by user shiroto. Remade to suit our purpose.
 * 
 * @author Carl Jansson
 * @version 2.0
 */
public class MenuStateOptions extends AbstractMenuBasedState {

	/**
	 * the upper left corner of button group
	 */
	private int MENUX = 200;
	private int MENUY = 100;

	/**
	 * Used to store variables between restarts.
	 */
	private Preferences prefSettings;

	/**
	 * Creates a new OptionsMenuState.
	 * 
	 * @param id
	 *            The int used to identify the state.
	 */
	public MenuStateOptions(int id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init() {
		// Setup the Preferences for this application, by class.
		prefSettings = Preferences.userNodeForPackage(MenuStateOptions.class);
		// init menu items
		this.initMenuItems();
		// Create background image
		try {
			background = new Image("res/menu/backgroundOptions.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initMenuItems() {
		this.initButtons();
		this.initCheckBoxes();
	}

	/**
	 * Create the buttons used in this state.
	 */
	private void initButtons() {
		try {
			Image backImage = new Image("res/menu/returnButton.png");
			Image backImageMO = new Image("res/menu/returnButtonMO.png");

			// Button for returning to main menu.
			Button returnButton = new Button(container, backImage, backImageMO,
					MENUX, MENUY) {
				@Override
				public void performAction() {
					game.enterState(Constants.GAME_STATE_MAIN_MENU);
				}
			};
			this.addMenuItem(returnButton);

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the checkboxes used in this state.
	 * 
	 * Get variable using preferences, set the setting and then create the box.
	 */
	private void initCheckBoxes() {

		// checkbox for V-Sync
		boolean vSync = prefSettings.getBoolean("V-Sync", false);
		checkActionVSync(vSync);
		CheckBox vSyncBox = new CheckBox(container, "Activate V-Sync", vSync,
				MENUX, MENUY + 50) {
			@Override
			public void performAction() {
				super.performAction();
				checkActionVSync(this.isChecked());
			}
		};
		// checkbox for music.
		boolean music = prefSettings.getBoolean("Music", true);
		checkActionMusic(music);
		CheckBox musicBox = new CheckBox(container, "Activate Music", music,
				MENUX, MENUY + 100) {
			@Override
			public void performAction() {
				super.performAction();
				checkActionMusic(this.isChecked());
			}
		};
		// checkbox for sound.
		boolean sound = prefSettings.getBoolean("Sound", true);
		checkActionSound(sound);
		CheckBox soundBox = new CheckBox(container, "Activate Sound", sound,
				MENUX, MENUY + 150) {
			@Override
			public void performAction() {
				super.performAction();
				checkActionSound(this.isChecked());
			}
		};
		// checkbox for toggling fullscreen.
		boolean fullScreen = prefSettings.getBoolean("Fullscreen", false);
		checkActionFullScreen(fullScreen);
		CheckBox toggleFullScreen = new CheckBox(container,
				"toggle fullscreen", fullScreen, MENUX, MENUY + 200) {
			@Override
			public void performAction() {
				super.performAction();
				checkActionFullScreen(this.isChecked());
			}
		};

		this.addMenuItem(vSyncBox);
		this.addMenuItem(musicBox);
		this.addMenuItem(soundBox);
		this.addMenuItem(toggleFullScreen);
	}

	/**
	 * Set V-Sync and save variable.
	 * 
	 * @param toBe
	 *            true to activate
	 */
	private void checkActionVSync(boolean toBe) {
		container.setVSync(toBe);
		prefSettings.putBoolean("V-Sync", toBe);
	}

	/**
	 * Set music on or off and save variable.
	 * 
	 * @param toBe
	 *            true to activate
	 */
	private void checkActionMusic(boolean toBe) {
		container.setMusicOn(toBe);
		prefSettings.putBoolean("Music", toBe);
	}

	/**
	 * Set sound on or off and save variable.
	 * 
	 * @param toBe
	 *            true to activate
	 */
	private void checkActionSound(boolean toBe) {
		container.setSoundOn(toBe);
		prefSettings.putBoolean("Sound", toBe);
	}

	/**
	 * Set fullscreen or windowed and save variable.
	 * 
	 * @param toBe
	 *            true to activate
	 */
	private void checkActionFullScreen(boolean toBe) {
		try {
			((StateController) game).setAppFullScreen(toBe);
			prefSettings.putBoolean("Fullscreen", toBe);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void escpAction() {
		// escape returns you to main menu.
		game.enterState(Constants.GAME_STATE_MAIN_MENU);
	}

}
