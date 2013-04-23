package se.chalmers.tda367.group15.game.controllers.room;

import java.awt.geom.Rectangle2D.Float;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.tda367.group15.game.models.MovingModel;

public abstract class Room {

	public abstract void update(GameContainer container, int delta)
			throws SlickException;

	public abstract void render(GameContainer container, Graphics g)
			throws SlickException;

	public abstract void init(GameContainer container, StateBasedGame game)
			throws SlickException;

	public Map<MovingModel, Float> dynamicCollisionMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<TiledMap, Float> staticCollisionMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
