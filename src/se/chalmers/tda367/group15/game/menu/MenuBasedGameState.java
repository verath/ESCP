package se.chalmers.tda367.group15.game.menu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

/**
 * A class with the purpose of making it easy to create menus in
 * a graphical environment.
 * Class taken from tutorial for writing menus in Slick2D
 * Originaly posted on http://slick.javaunlimited.net/
 * by user shiroto
 * Comments added by Carl Jansson.
 * 
 * @author Carl Jansson
 */
public abstract class MenuBasedGameState extends AbstractedGameState {
	   protected boolean leftMouseButtonReleased;
	   protected Button[] buttons;
	   protected Image background;
	   
	   public MenuBasedGameState(int id) {
	      super(id);
	   }

	   @Override
	   public void init() {
	      this.initButtons();
	   }
	   
	   protected abstract void initButtons();

	   @Override
	   public void render(Graphics g) {
	      background.draw();
	      for(Button b : buttons)
	         b.render(g);
	   }

	   @Override
	   public void update(int delta) {
	      this.checkForButtonClicks();
	   }
	   
	   @Override
	   public void mouseReleased(int button, int x, int y) {
	      if (button == Input.MOUSE_LEFT_BUTTON) {
	         leftMouseButtonReleased = true;
	      }
	   }
	   
	   protected final void checkForButtonClicks() {
	      if(leftMouseButtonReleased) {
	         leftMouseButtonReleased = false;
	         this.clickButton();
	      }
	   }
	   
	   private void clickButton() {
	      for(Button b : buttons) {
	         if(b.isMouseOver())
	            b.performAction();
	      }
	   }
	   
	   public final void setButtons(Button...buttons) {
	      this.buttons = buttons;
	   }
	}