package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * An interface describing a model that other models can collide with.
 * 
 * @author Peter
 * 
 */
public interface CollidableModel extends Model {

	/**
	 * Method for getting the rectangles representing the model's collision
	 * bounds.
	 * 
	 * @return A list of rectangles that describes the collision bounds for the
	 *         model.
	 */
	public List<Rectangle2D.Float> getCollisionBounds();
}
