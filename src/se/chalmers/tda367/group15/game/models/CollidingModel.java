package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * An interface describing a model that should perform an action when colliding
 * with a CollidableModel.
 * 
 * 
 * @author Peter
 * 
 */
public interface CollidingModel extends Model {

	/**
	 * Method called for the model after the update method. Should check
	 * collision against the provided collidableModels and define the action
	 * taken by the model on collision.
	 * 
	 * Note: The parameter will also hold the model's own collision bounds if
	 * the model also implements the CollidableModel interface. This must be
	 * handled by the model itself.
	 * 
	 * @param collisionBounds
	 *            A list of rectangles representing the collision bounds that
	 *            collision should be checked against
	 */
	public abstract void collide(final List<Rectangle2D.Float> collisionBounds);

}