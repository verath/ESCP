package se.chalmers.tda367.group15.game.models;

/**
 * Simple class for representing a dummy enemy. This enemy is only intended for
 * testing.
 * 
 * @author simon
 * 
 */
public class DummyEnemy extends MovingModel {

	private double rotation;
	/**
	 * Creates a new dummy enemy.
	 */
	public DummyEnemy() {
		setX(32f);
		setY(128f);
		setVelocity(0.1f);
		setWidth(32);
		setHeight(32);
		setRotation(180);
	}

	/**
	 * Returns the angle the hero is currently facing in degrees. The angle
	 * starts from the left of the hero.
	 * 
	 * @return The angle in degrees
	 */
	public double getRotation() {
		return rotation;
	}
	
	/**
	 * Method for setting the rotation angle that the hero should be facing. 
	 * @param rotation
	 */
	public void setRotation(double rotation) {
		this.rotation = rotation % 360;
	}

}
