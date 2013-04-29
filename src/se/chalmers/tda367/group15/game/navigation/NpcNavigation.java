package se.chalmers.tda367.group15.game.navigation;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import se.chalmers.tda367.group15.game.controllers.DummyEnemyController;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;

/**
 * Interface for npc behavior pattern.
 * @author Carl Jansson
 *
 */
public interface NpcNavigation {
	
	
	/**
	 * 
	 * @param x current x position
	 * @param rot current rotation
	 * @param delta time since last update
	 * @param speed how fast we are moving 
	 * @return a new x position
	 */
	public float getNewX();
	
	/**
	 * 
	 * @param y current y position
	 * @param rot current rotation
	 * @param delta time since last update
	 * @param speed how fast we are moving 
	 * @return a new y position
	 */
	public float getNewY();
	
	/**
	 * @return a new direction
	 */
	public double getNewDirection();

	public void update(DummyEnemyController dummyController, AbstractMovingModel model, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds);
}
