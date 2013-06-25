package se.chalmers.tda367.group15.game.models;

/**
 * A health pickup. When the hero moves over this pickup, a small amount of
 * health is restored.
 * 
 * @author Erik
 * 
 */
public class HealthPickupModel extends AbstractPickupModel {

	/**
	 * Create a new health pickup at a given position.
	 * 
	 * @param x
	 *            x-position of the health pickup.
	 * @param y
	 *            y-position of the health pickup.
	 */
	public HealthPickupModel(float x, float y) {
		super(x, y);
		setAnimationPath("health/");
	}

}
