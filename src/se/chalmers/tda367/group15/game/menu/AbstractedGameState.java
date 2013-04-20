package se.chalmers.tda367.group15.game.menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * A class for cutting down the implementation needed if using BasicGameState.
 * Class taken from tutorial for writing menus in Slick2D Originally 
 * posted on http://slick.javaunlimited.net/ by user shiroto.
 * 
 * @author unknown, shiroto?
 * @version 1.1
 */
public abstract class AbstractedGameState extends BasicGameState {
	   protected GameContainer container;
	   protected StateBasedGame game;
	   public final int ID;
	   protected Input input;
	   
	   public AbstractedGameState(int id) {
	      ID = id;
	   }
	   
	   @Override
	   public final void init(GameContainer container, StateBasedGame game) {
	      this.setInput(container.getInput());
	      this.container = container;
	      this.game = game;
	      this.init();
	   }
	   
	   @Override
	   public final void render(GameContainer container, StateBasedGame game, Graphics g) {
	      this.render(g);
	   }
	   
	   @Override
	   public final void update(GameContainer container, StateBasedGame game, int delta)
	         throws SlickException {
	      this.update(delta);
	   }
	   
	   public abstract void init();
	   public abstract void render(Graphics g);
	   public abstract void update(int delta);
	   
	   @Override
	   public final int getID() {
	      return ID;
	   }
	   
	   public void setInput(Input input) {
	      this.input = input;
	   }
	}