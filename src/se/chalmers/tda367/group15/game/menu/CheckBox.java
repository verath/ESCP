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
public class CheckBox implements MenuItem{

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
	 * Empty checkbox with mouse over.
	 */
	private Image boxImgMO;

	/**
	 * filled checkbox.
	 */
	private Image CheckedBoxImg;
	
	/**
	 * Mouse over filled box.
	 */
	private Image CheckedBoxImgMO;

	/**
	 * Image of the currently active box
	 */
	private Image theNormalImg;
	
	/**
	 * Image of currently active box when mouse is over.
	 */
	private Image theNormalImgMO;
	
	/**
	 * Image of currently active box when mouse down over it.
	 */
	private Image theNormalImgMDown;

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
	public CheckBox(GUIContext guiContext, String text, boolean value, int x,
			int y) {
		this.guiContext = guiContext;
		this.theText = text;
		this.checked = value;
		this.xPos = x;
		this.yPos = y;

		loadPictures();
		createMoa();
		updateMoa();
	}
	private void loadPictures(){
		try {
			boxImg = new Image("res/menu/checkBox.png");
			CheckedBoxImg = new Image("res/menu/checkBox2.png");
			CheckedBoxImgMO = new Image("res/menu/checkBox2MO.png");
			boxImgMO = new Image("res/menu/checkBoxMO.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		moa.render(guiContext, g);
		Color tmpC = g.getColor();
		g.setColor(Color.black);
		g.drawString(theText, xPos + 45, yPos + 10);
		g.setColor(tmpC);
	}

	/**
	 * This method MUST be called with super from implementation!!!
	 */
	public void performAction() {
		if (checked) {
			checked = false;
		} else {
			checked = true;
		}
		updateMoa();
	}

	/**
	 * creates moa with proper picture.
	 */
	private void createMoa() {
		moa = new MouseOverArea(guiContext, boxImg, xPos, yPos);
	}
	
	public void updateMoa() {
		if (checked) {
			theNormalImg = CheckedBoxImg;
			theNormalImgMO = CheckedBoxImgMO;
			theNormalImgMDown = boxImg;
			
		} else {
			theNormalImg = boxImg;
			theNormalImgMO = boxImgMO;
			theNormalImgMDown = CheckedBoxImg;
		}
		moa.setNormalImage(theNormalImg);
		moa.setMouseOverImage(theNormalImgMO);
		moa.setMouseDownImage(theNormalImgMDown);
	}

	/**
	 * Checks if mouse is above picture used for checkbox.
	 * 
	 * @return true if mouse is above moa.
	 */
	public boolean isMouseOver() {
		return moa.isMouseOver();
	}

	/**
	 * Check if checkbox is used.
	 * 
	 * @return true if box is activated.
	 */
	public boolean isChecked() {
		return checked;
	}
}