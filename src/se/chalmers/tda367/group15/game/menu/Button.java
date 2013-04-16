package se.chalmers.tda367.group15.game.menu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public abstract class Button  {
	   protected MouseOverArea moa;
	   protected GUIContext guiContext;
	   
	   public Button(GUIContext guiContext, Image image, int x, int y) {
	      this.guiContext = guiContext;
	      moa = new MouseOverArea(guiContext, image, x, y);
	   }
	   
	   public void render(Graphics g) {
	      moa.render(guiContext, g);
	   }
	   
	   public abstract void performAction();
	   
	   public boolean isMouseOver() {
	      return moa.isMouseOver();
	   }
	}