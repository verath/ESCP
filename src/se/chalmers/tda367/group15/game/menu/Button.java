package se.chalmers.tda367.group15.game.menu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * A class for representation buttons in a graphical environment. Supposed to be
 * used together with AbstractMenuBasedState.java. Class based on tutorial for
 * writing menus in Slick2D Originally posted on http://slick.javaunlimited.net/
 * by user shiroto. Remade to suit our purpose.
 * 
 * @author Carl Jansson
 * @version 2.0
 */
public class Button implements MenuItem {

	/**
	 * The pictures and area of the button. This is the size of the image used
	 * when creating the moa.
	 */
	protected MouseOverArea moa;

	/**
	 * The container containing the button.
	 */
	protected GUIContext guiContext;

	/**
	 * Create a new button with only one picture.
	 * 
	 * @param guiContext
	 *            the container.
	 * @param image
	 *            the image used. Also gives size of moa.
	 * @param x
	 *            x coordinate for button upper left corner.
	 * @param y
	 *            y coordinate for button upper left corner.
	 */
	public Button(GUIContext guiContext, Image image, int x, int y) {
		this(guiContext, image, image, x, y);
	}

	/**
	 * Create a new button with one default picture and one hover/pressed image.
	 * 
	 * @param guiContext
	 *            the container.
	 * @param image
	 *            the default image used. Also gives size of moa.
	 * @param moImage
	 *            the picture displayed when hovering or pressed.
	 * @param x
	 *            x coordinate for button upper left corner.
	 * @param y
	 *            y coordinate for button upper left corner.
	 */
	public Button(GUIContext guiContext, Image image, Image moImage, int x,
			int y) {
		this(guiContext, image, moImage, moImage, x, y);
	}

	/**
	 * Create a new button with one default, one hover and one pressed image.
	 * 
	 * @param guiContext
	 *            the container.
	 * @param image
	 *            the default image used. Also gives size of moa.
	 * @param moImage
	 *            the picture displayed when hovering.
	 * @param mdImage
	 *            the picture displayed when button pressed.
	 * @param x
	 *            x coordinate for button upper left corner.
	 * @param y
	 *            y coordinate for button upper left corner.
	 */
	public Button(GUIContext guiContext, Image image, Image moImage,
			Image mdImage, int x, int y) {
		this.guiContext = guiContext;
		moa = new MouseOverArea(guiContext, image, x, y);
		moa.setMouseOverImage(moImage);
		moa.setMouseDownImage(mdImage);
	}

	/**
	 * Paint the button.
	 */
	public void render(Graphics g) {
		moa.render(guiContext, g);
	}

	/**
	 * Method in which to declare what is to happen when button is pressed.
	 */
	public void performAction() {
	}

	/**
	 * @return true if mouse is above button.
	 */
	public boolean isMouseOver() {
		return moa.isMouseOver();
	}
}