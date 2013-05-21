package se.chalmers.tda367.group15.game.states;

import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import se.chalmers.tda367.group15.game.menu.MenuItem;

/**
 * A class with the purpose of making it easy to create menus in a graphical
 * environment. Class based on tutorial for writing menus in Slick2D Originally
 * posted on http://slick.javaunlimited.net/ by user shiroto. Remade to suit our
 * purpose.
 * 
 * @author shiroto, Carl Jansson
 * @version 4.0
 */
public abstract class AbstractMenuBasedState extends AbstractGameState {

	private boolean pendingEscpAction;

	/**
	 * indicates mouse button i was just pressed.
	 */
	private boolean leftMouseButtonReleased;

	/**
	 * List with all MenuItems on page.
	 */
	private final Set<MenuItem> menuItems = new HashSet<MenuItem>();

	/**
	 * The background for current menu page.
	 */
	private Image background;

	/**
	 * @param id
	 *            the state identification with which state is fetched.
	 */
	AbstractMenuBasedState(int id) {
		super(id);
		pendingEscpAction = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init() {
		this.initMenuItems();
	}

	/**
	 * In this method you create all buttons.
	 */
	protected abstract void initMenuItems();

	/**
	 * this method is called whenever escape is pressed.
	 */
	protected abstract void escpAction();

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void render(Graphics g) {
		if (getBackground() != null) {
			getBackground().draw();
		}
		if (menuItems != null) {
			for (MenuItem item : menuItems) {
				item.render(g);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void update(int delta) {
		this.checkForButtonClicks();
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			pendingEscpAction = true;
		} else if (pendingEscpAction
				&& !container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			pendingEscpAction = false;
			this.escpAction();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mouseReleased(int button, int x, int y) {
		if (button == Input.MOUSE_LEFT_BUTTON) {
			leftMouseButtonReleased = true;
		}
	}

	/**
	 * Check if mouse clicked and perform action.
	 */
	final void checkForButtonClicks() {
		if (leftMouseButtonReleased) {
			leftMouseButtonReleased = false;
			this.clickButton();
		}
	}

	/**
	 * goes through the buttons, checks if mouse is above and if it is performs
	 * action.
	 */
	private void clickButton() {
		for (MenuItem item : menuItems) {
			if (item.isMouseOver()) {
				item.performAction();
			}
		}
	}

	/**
	 * Adds a MenuItem to ArrayList menuItems.
	 * 
	 * @param item
	 *            the item to add to list.
	 */
	final void addMenuItem(MenuItem item) {
		this.menuItems.add(item);
	}

	Image getBackground() {
		return background;
	}

	void setBackground(Image background) {
		this.background = background;
	}
}
