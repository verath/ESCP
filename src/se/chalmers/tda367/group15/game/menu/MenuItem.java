package se.chalmers.tda367.group15.game.menu;

import org.newdawn.slick.Graphics;

/**
 * Interface for a menu item that is supposed to do something when used.
 * Supposed to be used together with AbstractMenuBasedState.java. Class is
 * extension by me to tutorial for writing menus in Slick2D Originally posted on
 * http://slick.javaunlimited.net/ by user shiroto
 * 
 * @author Carl Jansson
 */
public interface MenuItem {

	public void render(Graphics g);

	public void performAction();

	public boolean isMouseOver();

}
