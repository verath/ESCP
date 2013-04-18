package se.chalmers.tda367.group15.game.menu;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

/**
 * A class with the purpose of making it easy to create menus in
 * a graphical environment.
 * Class based on tutorial for writing menus in Slick2D Originally 
 * posted on http://slick.javaunlimited.net/ by user shiroto.
 * Remade to suit our purpose.
 * 
 * @author Carl Jansson
 */
public abstract class MenuBasedGameState extends AbstractedGameState {
	
	private boolean pendingEscpAction;

	/**
	 * indicates mouse button i was just pressed.
	 */
	protected boolean leftMouseButtonReleased;
	
	/**
	 * List with all buttons on page.
	 */
	protected ArrayList<Button> buttons = new ArrayList<Button>();
	
	/**
	 * The background for current menu page.
	 */
	protected Image background;

	/**
	 * @param id the state identification with which state is fetched.
	 */
	public MenuBasedGameState(int id) {
		super(id);
		pendingEscpAction = false;
	}

	@Override
	public void init() {
		this.initButtons();
	}

	protected abstract void initButtons();
	
	/**
	 * this method is called whenever escape is pressed.
	 */
	protected abstract void escpAction();
	
	@Override
	public void render(Graphics g) {
		background.draw();
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).render(g);
		}
	}
	
	@Override
	public void update(int delta) {
		this.checkForButtonClicks();
		if ( container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
			pendingEscpAction = true;
		} else if ( pendingEscpAction && !container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			pendingEscpAction = false;
			this.escpAction();
		}
	}

	@Override
	public void mouseReleased(int button, int x, int y) {
		if (button == Input.MOUSE_LEFT_BUTTON) {
			leftMouseButtonReleased = true;
		}
	}

	/**
	 * Check if mouse clicked and perform action.
	 */
	protected final void checkForButtonClicks() {
		if(leftMouseButtonReleased) {
			leftMouseButtonReleased = false;
			this.clickButton();
		}
	}

	/**
	 * goes through the buttons, checks if mouse is above and if it is performs action.
	 */
	private void clickButton() {
		for (int i = 0; i < buttons.size(); i++) {
			if ( buttons.get(i).isMouseOver() ) {
				buttons.get(i).performAction();
			}
		}
	}

	/**
	 * Adds button to ArayList buttons.
	 * @param aButton the button to add to list.
	 */
	public final void addButton(Button aButton) {
		this.buttons.add(aButton);
	}
}