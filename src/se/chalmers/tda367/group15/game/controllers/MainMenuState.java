package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.menu.Button;
import se.chalmers.tda367.group15.game.menu.MenuBasedGameState;

/**
 * A class representing a main menu in a graphical application.
 * Class originaly taken from tutorial for writing menus in Slick2D
 * Originaly posted on http://slick.javaunlimited.net/
 * by user shiroto
 * remade by Carl Jansson to suit our purpose.
 * Comments added by Carl Jansson.
 * 
 * @author Carl Jansson
 */
public class MainMenuState extends MenuBasedGameState {
	private Image playImage;
	private Image quitImage;
	
	
	public MainMenuState(int id) {
		super(id);
	}

	@Override
	public void init() {
		this.initButtons();
		try {
			background = new Image("res/menu/background.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initButtons() {
		
		try {
			playImage = new Image("res/menu/play.png");
			quitImage = new Image("res/menu/quit.png");
			Button newGameButton = new Button(container, playImage, 100, 100) {
				@Override
				public void performAction() {
					game.enterState( Constants.PLAYING );
				}
			};
			Button exitButton = new Button(container, quitImage, 100, 200) {
				@Override
				public void performAction() {
					container.exit();
				}
			};
			this.setButtons(newGameButton, exitButton);
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}
}