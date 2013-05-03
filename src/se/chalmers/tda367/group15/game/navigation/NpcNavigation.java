package se.chalmers.tda367.group15.game.navigation;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import se.chalmers.tda367.group15.game.controllers.DummyEnemyController;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;

/**
 * Interface for npc behavior pattern.
 * 
 * @author Carl Jansson
 * 
 */
public interface NpcNavigation {

	/**
	 * 
	 * @return the x position to use
	 */
	public float getNewX();

	/**
	 * 
	 * @return the y position to use
	 */
	public float getNewY();

	/**
	 * 
	 * @return the direction to face
	 */
	public double getNewDirection();

	/**
	 * 
	 * @param dummyController
	 *            the controller to update
	 * @param model
	 *            the controllers model
	 * @param delta
	 *            time since last update in milliseconds
	 * @param staticBounds
	 *            the static bounds to avoid
	 * @param dynamicBounds
	 *            the other movingmodels in current map.
	 */
	public void update(DummyEnemyController dummyController,
			AbstractMovingModel model, int delta, List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds);
}
