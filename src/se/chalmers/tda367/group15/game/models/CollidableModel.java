package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;

/**
 * An interface describing a model that moving objects can collide with.
 * 
 * @author Peter
 * 
 */
public interface CollidableModel extends Model {
	/**
	 * Getter for the collision bounds defined by the model.
	 * 
	 * @return
	 */
	public List<Float> getCollisionBounds();
}
