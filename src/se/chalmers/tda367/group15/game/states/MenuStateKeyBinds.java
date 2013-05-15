package se.chalmers.tda367.group15.game.states;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.settings.Constants;

/**
 * A game over state. Displays a high score and allows the user to either replay
 * or go back to menu.
 * 
 * @author Peter
 * 
 */
public class MenuStateKeyBinds extends AbstractMenuBasedState {

	/**
	 * Creates a new MenuStateKeyBinds.
	 * 
	 * @param id
	 *            The int used to identify the state.
	 */
	public MenuStateKeyBinds(int id) {
		super(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init() {
		try {
			// TODO: create a background image for keybinds?
			setBackground(new Image("res/menu/backgroundOptions.png"));
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void render(Graphics g) {
		super.render(g);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void initMenuItems() {
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
