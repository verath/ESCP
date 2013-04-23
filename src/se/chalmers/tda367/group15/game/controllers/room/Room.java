package se.chalmers.tda367.group15.game.controllers.room;

import java.awt.geom.Rectangle2D;
import java.util.List;

import org.newdawn.slick.Graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public abstract class Room {

	public abstract void update(GameContainer container, int delta)
			throws SlickException;

	public abstract void render(GameContainer container, Graphics g)
			throws SlickException;

	public abstract void init(GameContainer container, StateBasedGame game)
			throws SlickException;

	public abstract List<Rectangle2D.Float> getCollisionBounds();
}
