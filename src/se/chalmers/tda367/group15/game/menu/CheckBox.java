package se.chalmers.tda367.group15.game.menu;


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;


/**
 * A class for representation a CheckBox in a graphical environment.
 * 
 * @author Carl Jansson
 */
public class CheckBox {

	/**
	 * the area compromising the checkbox.
	 */
	protected MouseOverArea moa;
	
	/**
	 * the area to paint in
	 */
	protected GUIContext guiContext;
	
	/**
	 * Empty checkbox.
	 */
	private Image boxImg;
	
	/**
	 * filled checkbox.
	 */
	private Image CheckedBoxImg;
	
	/**
	 * Image of the currently active box
	 */
	private Image theImg;
	
	/**
	 * Is box checked.
	 */
	private boolean checked;
	
	/**
	 * upper left corner of box.
	 */
	private int xPos, yPos;
	
	/**
	 * the text to print with the box.
	 */
	private String theText;

	/**
	 * creates a new checkbox.
	 * 
	 * @param guiContext
	 * @param text
	 * @param value
	 * @param x
	 * @param y
	 */
	public CheckBox(GUIContext guiContext, String text, boolean value, int x, int y) {
		this.guiContext = guiContext;
		this.theText = text;
		this.checked = value;
		this.xPos = x;
		this.yPos = y;

		try {
			boxImg = new Image("res/menu/checkBox.png");
			CheckedBoxImg = new Image("res/menu/checkBox2.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		createMoa();
	}

	public void render(Graphics g) {
		moa.render(guiContext, g);
		Color tmpC = g.getColor();
		g.setColor(Color.black);
		g.drawString(theText, xPos+45, yPos+10);
		g.setColor(tmpC);
	}

	/**
	 * This method MUST be called with super from implementation!!!
	 */
	public void performAction(){
		if ( checked ) {
			checked = false;
		} else {
			checked = true;
		}
		createMoa();
	}
	
	/**
	 * creates moa with proper picture. 
	 */
	public void createMoa(){
		if ( checked ) {
			theImg = CheckedBoxImg;
		} else {
			theImg = boxImg;
		}
		moa = new MouseOverArea(guiContext, theImg, xPos, yPos);
	}

	/**
	 * Checks if mouse is above picture used for checkbox.
	 * @return true if mouse is above moa.
	 */
	public boolean isMouseOver() {
		return moa.isMouseOver();
	}
	
	/**
	 * Check if checkbox is used.
	 * @return true if box is activated.
	 */
	public boolean isChecked(){
		return checked;
	}
}