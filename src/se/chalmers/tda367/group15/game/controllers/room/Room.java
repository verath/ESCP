package se.chalmers.tda367.group15.game.controllers.room;

import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.group15.game.controllers.MovingModelController;
import se.chalmers.tda367.group15.game.models.MovingModel;

public abstract class Room {

	public abstract void update(GameContainer container, int delta)
			throws SlickException;

	public abstract void render(GameContainer container, Graphics g)
			throws SlickException;

	public abstract void init(GameContainer container, StateBasedGame game)
			throws SlickException;

	public abstract List<Rectangle2D.Float> getStaticBounds();

	public abstract List<MovingModelController> getControllers();
	
	public abstract Map<MovingModel, Rectangle2D.Float> getDynamicBounds();
}
