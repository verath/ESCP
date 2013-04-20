package se.chalmers.tda367.group15.game.models;


/**
 * Class representing the model of a hero.
 * 
 * @author simon, Carl, tholene
 * 
 */

public class Hero extends MovingModel {

	private double rotation;
	private boolean goingUp;
	private boolean goingDown;
	private boolean goingRight;
	private boolean goingLeft;

	/**
	 * Create a new Hero.
	 */
	public Hero() {

		setX(44f);
		setY(44f);
		setVelocity(0.15f);
		setWidth(64);
		setHeight(64);

	}

	/**
	 * Returns the angle the hero is currently facing in degrees. The angle
	 * starts from the left of the hero.
	 * 
	 * @return
	 */
	public double getRotation() {
		return rotation;
	}
	
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	/**
	 * Returns whether the hero is moving or not.
	 * 
	 * @return True if hero is moving.
	 */
	public boolean isMoving() {
		int speedY = (goingUp ? 1 : 0) - (goingDown ? 1 : 0);
		int speedX = (goingLeft ? 1 : 0) - (goingRight ? 1 : 0);

		return (speedY != 0 || speedX != 0);
	}
}
