package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface MovingModelController {

	public abstract void render(GameContainer container, Graphics g) throws SlickException;
	
	public abstract void update(GameContainer container, int delta) throws SlickException;
}
