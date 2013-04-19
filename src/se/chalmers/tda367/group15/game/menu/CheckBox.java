package se.chalmers.tda367.group15.game.menu;

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

	protected MouseOverArea moa;
	protected GUIContext guiContext;
	private Image boxImg;
	private Image CheckedBoxImg;
	private Image theImg;
	private boolean checked;
	private int xPos;
	private int yPos;
	private String theText;

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
		setMoa();
	}

	public void render(Graphics g) {
		moa.render(guiContext, g);
		g.drawString(theText, xPos+40, yPos+10);
	}

	public void performAction(){
		if ( checked ) {
			checked = false;
			theImg = boxImg;
		} else {
			checked = true;
			theImg = CheckedBoxImg;
		}
		setMoa();
	}
	
	public void setMoa(){
		if ( checked ) {
			theImg = CheckedBoxImg;
		} else {
			theImg = boxImg;
		}
		moa = new MouseOverArea(guiContext, theImg, xPos, yPos);
	}

	public boolean isMouseOver() {
		return moa.isMouseOver();
	}
	
	public boolean isChecked(){
		return checked;
	}
}