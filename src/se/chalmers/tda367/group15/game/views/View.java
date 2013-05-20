package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * An interface for a View in the game.
 * 
 * @author Peter
 * 
 */
public interface View {
	/**
	 * Render a view here.
	 * 
	 * @param container
	 *            The container holding this game
	 * @param g
	 *            The graphics context that can be used to render. However,
	 *            normal rendering routines can also be used.
	 */
	public void render(GameContainer container, Graphics g)
    ;
}
