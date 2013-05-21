package se.chalmers.tda367.group15.game.states;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.menu.Button;
import se.chalmers.tda367.group15.game.settings.Constants;

/**
 * State displayed when game is lost
 * 
 * @author Peter
 * 
 */
public class GameLostState extends AbstractMenuBasedState {

	/**
	 * Creates a new GameLostState.
	 * 
	 * @param id
	 *            The int used to identify the state.
	 */
	public GameLostState(int id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init() {
		try {
			setBackground(new Image("res/menu/backgroundGameOver.png"));
			addBackButton();
		} catch (SlickException e) {
			if (Constants.DEBUG) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void initMenuItems() {
	}

	private void addBackButton() throws SlickException {
		Image backImage = new Image("res/menu/returnButton.png");
		Image backImageMO = new Image("res/menu/returnButtonMO.png");

		// Button for returning to main menu.
		Button returnButton = new Button(container, backImage, backImageMO,
				Constants.MENU_UPPER_X, Constants.MENU_UPPER_Y) {
			@Override
			public void performAction() {
				game.enterState(Constants.GAME_STATE_MENU_MAIN);
			}
		};

		addMenuItem(returnButton);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void escpAction() {
		// escape returns you to main menu.
		game.enterState(Constants.GAME_STATE_MENU_MAIN);
	}
}
