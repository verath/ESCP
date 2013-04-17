package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * A Colliding model is a model that should perform an action on collision with
 * another model.
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
	 * @param collisionBounds
	 *            The collision bounds that should be checked against
	 */
	public abstract void collide(List<Rectangle2D.Float> collisionBounds);

}