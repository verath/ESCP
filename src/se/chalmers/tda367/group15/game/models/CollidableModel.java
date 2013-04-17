package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * An interface describing a model that moving objects can collide with.
 * 
 * @author Peter
 * 
 */
public interface CollidableModel extends Model {

	/**
	 * Method for getting the rectangles representing the map's collisionbounds
	 * 
	 * @return collision bounds
	 */
	public List<Rectangle2D.Float> getCollisionBounds();
}
