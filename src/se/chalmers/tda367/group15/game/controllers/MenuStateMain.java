package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.menu.AbstractMenuBasedState;
import se.chalmers.tda367.group15.game.menu.Button;

/**
 * A state representing a Main Menu in a graphical application. Class based on
 * tutorial for writing menus in Slick2D Originally posted on
 * http://slick.javaunlimited.net/ by user shiroto. Remade to suit our purpose.
 * 
 * @author Carl Jansson
 * @version 2.0
 */
public class MenuStateMain extends AbstractMenuBasedState {

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
	 * creates a new MainMenuState.
	 * 
	 * @param id
	 *            The int used to identify the state.
	 */
	public MenuStateMain(int id) {
		super(id);
		this.existsGameCurrently = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init() {
		this.initMenuItems();
		try {
			background = new Image("res/menu/background.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initMenuItems() {
		try {
			this.createButtons();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates the buttons. Separate method to avoid big try catch.
	 * 
	 * @throws SlickException
	 *             If a image fail to load.
	 */
	private void createButtons() throws SlickException {
		Image newGameImage = new Image("res/menu/newGame.png");
		Image newGameImageMO = new Image("res/menu/newGameMO.png");
		Image quitImage = new Image("res/menu/quit.png");
		Image quitImageMO = new Image("res/menu/quitMO.png");
		Image optionsImage = new Image("res/menu/options.png");
		Image optionsImageMO = new Image("res/menu/optionsMO.png");

		// Start a new game.
		Button newGameButton = new Button(container, newGameImage,
				newGameImageMO, MENUX, MENUY + 50) {
			@Override
			public void performAction() {

				try { // Init PlayState to reset game.
					game.getState(Constants.GAME_STATE_PLAYING).init(container,
							game);
					existsGameCurrently = true;
				} catch (SlickException e) {
					e.printStackTrace();
				}

				game.enterState(Constants.GAME_STATE_PLAYING);
				addResumeButton();
			}
		};
		// open options
		Button optionsButton = new Button(container, optionsImage,
				optionsImageMO, MENUX, MENUY + 100) {
			@Override
			public void performAction() {
				game.enterState(Constants.GAME_STATE_OPTIONS_MENU);
			}
		};
		// Quit application
		Button exitButton = new Button(container, quitImage, quitImageMO,
				MENUX, MENUY + 150) {
			@Override
			public void performAction() {
				container.exit();
			}
		};

		// add items to state.
		this.addMenuItem(newGameButton);
		this.addMenuItem(optionsButton);
		this.addMenuItem(exitButton);
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
				resumeGameButton = new Button(container, resumeImage,
						resumeImageMO, MENUX, MENUY) {
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void escpAction() {
		// Escape only usable if game started.
		if (existsGameCurrently) {
			// Returns you to game.
			game.enterState(Constants.GAME_STATE_PLAYING);
		}
	}
}