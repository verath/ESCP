package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.menu.Button;
import se.chalmers.tda367.group15.game.menu.CheckBox;
import se.chalmers.tda367.group15.game.menu.MenuBasedGameState;

/**
 * A state with different settings you can choose between.
 * 
 * @author Carl Jansson
 * @version 2.0
 */
public class OptionsMenuState extends MenuBasedGameState {

	/**
	 * the upper left corner of button group
	 */
	private int MENUX = 200;
	private int MENUY = 100;

	/**
	 * Creates a new OptionsMenuState.
	 * 
	 * @param id The int used to identify the state.
	 */
	public OptionsMenuState(int id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init() {
		this.initButtons();
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
	protected void initButtons() {
		try {
			Image backImage = new Image("res/menu/returnButton.png");
			Image backImageMO = new Image("res/menu/returnButtonMO.png");

			// Button for returning to main menu.
			Button returnButton = new Button(container, backImage, backImageMO, MENUX, MENUY) {
				@Override
				public void performAction() {
					game.enterState(Constants.GAME_STATE_MAIN_MENU);
				}
			};
			this.addMenuItem(returnButton);

		} catch (SlickException e) {
			e.printStackTrace();
		}
		// checkboxe for slick function V-Sync
		CheckBox vSyncBox = new CheckBox(container, "Activate V-Sync", false,
				MENUX, MENUY + 50) {
			@Override
			public void performAction() {
				super.performAction();
				container.setVSync(this.isChecked());
			}
		};
		// checkboxe for slick function music.
		CheckBox musicBox = new CheckBox(container, "Activate Music", true,
				MENUX, MENUY + 100) {
			@Override
			public void performAction() {
				super.performAction();
				container.setMusicOn(this.isChecked());
			}
		};
		// checkboxe for slick function sound.
		CheckBox soundBox = new CheckBox(container, "Activate Sound", true,
				MENUX, MENUY + 150) {
			@Override
			public void performAction() {
				super.performAction();
				container.setSoundOn(this.isChecked());
			}
		};
		// checkboxe for toggling fullscreen.
		CheckBox toggleFullScreen = new CheckBox(container,
				"toggle fullscreen", false, MENUX, MENUY + 200) {
			@Override
			public void performAction() {
				super.performAction();
				try {
					((StateController) game).setAppFullScreen(this.isChecked());
				} catch (SlickException e) {
					e.printStackTrace();
				}
			}
		};

		this.addMenuItem(vSyncBox);
		this.addMenuItem(musicBox);
		this.addMenuItem(soundBox);
		this.addMenuItem(toggleFullScreen);
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
