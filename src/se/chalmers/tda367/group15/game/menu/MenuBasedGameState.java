package se.chalmers.tda367.group15.game.menu;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

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