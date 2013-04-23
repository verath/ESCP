package se.chalmers.tda367.group15.game.menu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * A class for representation buttons in a graphical environment. Class taken
 * from tutorial for writing menus in Slick2D Originally posted on
 * http://slick.javaunlimited.net/ by user shiroto. Remade to suit our
 * purpose.
 * 
 * @author Carl Jansson
 */
public class Button implements MenuItem{
	protected MouseOverArea moa;
	protected GUIContext guiContext;

	public Button(GUIContext guiContext, Image image, int x, int y){
		this(guiContext, image, image, x, y);
	}
	public Button(GUIContext guiContext, Image image, Image moImage, int x, int y){
		this(guiContext, image, moImage, moImage, x, y);
	}
	public Button(GUIContext guiContext, Image image, Image moImage, Image mdImage, int x, int y) {
		this.guiContext = guiContext;
		moa = new MouseOverArea(guiContext, image, x, y);
		moa.setMouseOverImage(moImage);
		moa.setMouseDownImage(mdImage);
	}

	public void render(Graphics g) {
		moa.render(guiContext, g);
	}

	public void performAction(){}

	public boolean isMouseOver() {
		return moa.isMouseOver();
	}
}