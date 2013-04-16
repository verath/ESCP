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
public interface CollidingModel {

	/**
	 * Getter for the moving model's collision bounds
	 * 
	 * @return
	 */
	public abstract Rectangle2D.Float getCollsionBounds();

	/**
	 * Method called for the model for each applicable CollidingModel. Should
	 * define the action taken by the model on collision.
	 * 
	 * @param collisionBounds
	 * @return
	 */
	public abstract void collide(List<Rectangle2D.Float> collisionBounds);

}