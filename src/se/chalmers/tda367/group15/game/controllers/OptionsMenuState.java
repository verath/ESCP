package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.menu.Button;
import se.chalmers.tda367.group15.game.menu.CheckBox;
import se.chalmers.tda367.group15.game.menu.MenuBasedGameState;

/**
 * A screen with different settings you can choose between.
 * 
 * @author Carl Jansson
 */
public class OptionsMenuState extends MenuBasedGameState {

	/**
	 * Images representing buttons
	 */
	private Image backImage;

	/**
	 * the upper left corner of button group
	 */
	private int MENUX = 200;
	private int MENUY = 100;

	/**
	 * Creates a new options menu.
	 * @param id
	 */
	public OptionsMenuState(int id) {
		super(id);
	}

	@Override
	public void init() {
		this.initButtons();
		try {
			background = new Image("res/menu/backgroundOptions.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void initButtons() {
		try {
			backImage = new Image("res/menu/returnButton.png");
			
			// Activate buttons
			Button returnButton = new Button(container, backImage, MENUX, MENUY) {
				@Override
				public void performAction() {
					game.enterState( Constants.GAME_STATE_MAIN_MENU );
				}
			};
			
			this.addButton(returnButton);
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		// Activate checkboxes
		CheckBox vSyncBox = new CheckBox(container, "Activate V-Sync", false, MENUX, MENUY+50){
			@Override
			public void performAction() {
				super.performAction();
				container.setVSync(this.isChecked());
			}
		};
		CheckBox musicBox = new CheckBox(container, "Activate Music", true, MENUX, MENUY+100){
			@Override
			public void performAction() {
				super.performAction();
				container.setMusicOn(this.isChecked());
			}
		};
		CheckBox soundBox = new CheckBox(container, "Activate Sound", true, MENUX, MENUY+150){
			@Override
			public void performAction() {
				super.performAction();
				container.setSoundOn(this.isChecked());
			}
		};
		this.addCheckButton(vSyncBox);
		this.addCheckButton(musicBox);
		this.addCheckButton(soundBox);
	}

	@Override
	protected void escpAction() {
		game.enterState( Constants.GAME_STATE_MAIN_MENU );
	}

}