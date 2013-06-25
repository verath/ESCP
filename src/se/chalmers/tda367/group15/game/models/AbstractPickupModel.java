package se.chalmers.tda367.group15.game.models;

/**
 * Abstract class for a pickup. A pickup might empower the hero with certain
 * attributes if the hero moves over it.
 * 
 * @author Erik
 */
public class AbstractPickupModel extends AbstractMovingModel {
	
	private String animationPath;

	/**
	 * Create a new pickup at a given position.
	 * 
	 * @param x
	 *            x-position of the pickup.
	 * @param y
	 *            y-position of the pickup.
	 */
	public AbstractPickupModel(float x, float y) {
		setX(x);
		setY(y);
		setHeight(32);
		setWidth(32);
		setAlive(true);
		setMoving(false);
	}
	
	public String getAnimationPath() {
		return animationPath;
	}

	void setAnimationPath(String path) {
		this.animationPath = path;
	}
}
