package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;

/**
 * An abstract class for representing a moving model.
 * 
 * @author simon
 * 
 */
public abstract class AbstractMovingModel {

	/**
	 * The currently equipped weapon
	 */
	private AbstractWeaponModel currentWeapon;

	/**
	 * The current health
	 */
	private int health;

	/**
	 * The height of the model
	 */
	private float height;

	/**
	 * Flag for whether the model is currently moving or not
	 */
	private boolean isMoving;

	/**
	 * The amount of pixels from the edge of the image itself, to the preferred
	 * collision bound rectangle
	 */
	private int offset;

	/**
	 * The angle the model is facing.
	 */
	private double rotation;

	/**
	 * Velocity of the model, pixels per mili-second
	 */
	private float velocity;

	/**
	 * The width of the model
	 */
	private float width;

	/**
	 * Current x-coordinate of the model
	 */
	private float x;

	/**
	 * Current y-coordinate of the model
	 */
	private float y;

	/**
	 * Method for getting the collision bounds of the model.
	 * 
	 * @return a rectangle representing the collision bounds
	 */
	public Rectangle2D.Float getBounds() {
		return new Rectangle2D.Float(x, y, width, height);
	}

	/**
	 * Method for getting the current weapon.
	 * 
	 * @return the current weapon
	 */
	public AbstractWeaponModel getCurrentWeapon() {
		return currentWeapon;
	}

	/**
	 * Method for getting the health.
	 * 
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Method for getting the height of the model.
	 * 
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * Method for getting the location offset
	 * 
	 * @return the location offset as an integer
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Method for getting the rotation angle of the model.
	 * 
	 * @return the rotation angle, in degrees.
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * Method for getting the movement velocity of the player.
	 * 
	 * @return the velocity
	 */
	public float getVelocity() {
		return velocity;
	}

	/**
	 * Method for getting the width of the model.
	 * 
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * Method for getting the x coordinate of the MovingModel.
	 * 
	 * @return x coordinate
	 */
	public float getX() {
		return x;
	}

	/**
	 * Method for getting the y coordinate of the MovingModel.
	 * 
	 * @return y coordinate
	 */
	public float getY() {
		return y;
	}

	/**
	 * Method for checking wether the model is moving or not.
	 * 
	 * @return True if model is moving, false otherwise.
	 */
	public boolean isMoving() {
		return isMoving;
	}

	/**
	 * Method for setting the current weapon.
	 * 
	 * @param the
	 *            new weapon to be set as current
	 */
	public void setCurrentWeapon(AbstractWeaponModel weapon) {
		currentWeapon = weapon;
	}

	/**
	 * Method for setting the health.
	 * 
	 * @param health
	 *            - preferably 0-100
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Method for setting the height of the model.
	 * 
	 * @param the
	 *            height to be set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * Method for setting the location offset
	 * 
	 * @param offset
	 *            The amount of pixels from the edge of the image itself, to the
	 *            preferred collision bound rectangle
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * Method for setting wether the model is moving or not
	 * 
	 * @param isMoving
	 *            Should true if model is moving, false otherwise
	 */
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}

	/**
	 * Method for setting the rotation angle of the model.
	 * 
	 * @param rotation
	 *            The roration angle, in degrees.
	 */
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	/**
	 * Method for setting the movement velocity of the MovingModel.
	 * 
	 * @param velocity
	 */
	public void setVelocity(float velocity) {
		this.velocity = velocity;

	}

	/**
	 * Method for setting the width of the model.
	 * 
	 * @param the
	 *            width to be set
	 */
	public void setWidth(float width) {
		this.width = width;
	}

	/**
	 * Method for setting the x coordinate of the MovingModel.
	 * 
	 * @param x
	 *            coordinate
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Method for setting the y coordinate of the MovingModel.
	 * 
	 * @param y
	 *            coordinate
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Method for receiving damage.
	 * 
	 * @param the
	 *            amount of damage that should be dealt
	 */
	public void takeDamage(int damage) {
		health -= damage;
	}
}
