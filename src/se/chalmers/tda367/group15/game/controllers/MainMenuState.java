package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.menu.Button;
import se.chalmers.tda367.group15.game.menu.MenuBasedGameState;

/**
 * A class representing a main menu in a graphical application. Class based on
 * tutorial for writing menus in Slick2D Originally posted on
 * http://slick.javaunlimited.net/ by user shiroto. Remade to suit our purpose.
 * 
 * @author Carl
 */
public class MainMenuState extends MenuBasedGameState {

	/**
	 * Resume button created separately, therefore you need to be able to see if
	 * it is null.
	 */
	private Button resumeGameButton;

	/**
	 * the upper left corner of button group
	 */
	private int MENUX = 200;
	private int MENUY = 100;

	/**
	 * True if you have started a game.
	 */
	private boolean existsGameCurrently;

	/**
	 * creates a new main menu.
	 * 
	 * @param id
	 */
	public MainMenuState(int id) {
		super(id);
		this.existsGameCurrently = false;
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
			Image newGameImage = new Image("res/menu/newGame.png");
			Image newGameImageMO = new Image("res/menu/newGameMO.png");
			Image quitImage = new Image("res/menu/quit.png");
			Image quitImageMO = new Image("res/menu/quitMO.png");
			Image optionsImage = new Image("res/menu/options.png");
			Image optionsImageMO = new Image("res/menu/optionsMO.png");
			
			// Start a new game.
			Button newGameButton = new Button(container, newGameImage, newGameImageMO, MENUX,
					MENUY + 50) {
				@Override
				public void performAction() {

					try { // Init PlayState to reset game.
						game.getState(Constants.GAME_STATE_PLAYING).init(
								container, game);
						existsGameCurrently = true;
					} catch (SlickException e) {
						e.printStackTrace();
					}

					game.enterState(Constants.GAME_STATE_PLAYING);
					addResumeButton();
				}
			};
			// open options
			Button optionsButton = new Button(container, optionsImage, optionsImageMO, MENUX,
					MENUY + 100) {
				@Override
				public void performAction() {
					game.enterState(Constants.GAME_STATE_OPTIONS_MENU);
				}
			};
			// Quit application
			Button exitButton = new Button(container, quitImage, quitImageMO, MENUX,
					MENUY + 150) {
				@Override
				public void performAction() {
					container.exit();
				}
			};

			this.addMenuItem(newGameButton);
			this.addMenuItem(optionsButton);
			this.addMenuItem(exitButton);

		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a resume button. The existence of a resume button indicates that
	 * there is something to resume and therefore this is done separately from
	 * the rest of the buttons.
	 */
	public void addResumeButton() {
		if (resumeGameButton == null) {
			try {
				Image resumeImage = new Image("res/menu/resumeGame.png");
				Image resumeImageMO = new Image("res/menu/resumeGameMO.png");
				resumeGameButton = new Button(container, resumeImage, resumeImageMO, MENUX,
						MENUY) {
					@Override
					public void performAction() {
						// Returns to currently active game.
						game.enterState(Constants.GAME_STATE_PLAYING);
					}
				};
			} catch (SlickException e) {
				e.printStackTrace();
			}
			this.addMenuItem(resumeGameButton);
		}
	}

	@Override
	protected void escpAction() {
		// Escape only usable if game started.
		if (existsGameCurrently) {
			// Returns you to game.
			game.enterState(Constants.GAME_STATE_PLAYING);
		}
	}
}