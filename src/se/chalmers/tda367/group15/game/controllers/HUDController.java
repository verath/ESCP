package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.views.HUDView;

/**
 * The controller for the HUD (ie. the information displayed "on top of" the
 * game, such as health and ammo)
 * 
 * @author Peter
 * 
 */
public class HUDController {

	private final HUDView hudView;

	public HUDController() {
		hudView = new HUDView();
	}
	/**
	 * Method for rendering all views.
	 * 
	 * @param container
	 *            The container holding this game.
	 * @param g
	 *            The graphics context that can be used to render. However,
	 *            normal rendering routines can also be used.
	 * @throws SlickException
	 *             Throw to indicate a internal error
	 */
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		hudView.render(container, g);
	}
}
