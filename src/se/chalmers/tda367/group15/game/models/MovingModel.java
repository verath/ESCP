package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;

import se.chalmers.tda367.group15.game.models.weapons.Weapon;

/**
 * An abstract class for representing a moving model that can both collide and
 * be collided with.
 * 
 * @author simon
 * 
 */
public abstract class MovingModel {
	private float x, y, velocity;
	private Weapon currentWeapon;
	private int health;
	private int width, height;
	private Rectangle2D.Float bounds;

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
	 * Method for getting the width of the model.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Method for getting the height of the model.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return height;
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
	 * Method for setting the width of the model.
	 * 
	 * @param the
	 *            width to be set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Method for setting the height of the model.
	 * 
	 * @param the
	 *            height to be set
	 */
	public void setHeight(int height) {
		this.height = height;
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
	 * Method for getting the current weapon.
	 * 
	 * @return the current weapon
	 */
	public Weapon getCurrentWeapon() {
		return currentWeapon;
	}

	/**
	 * Method for setting the current weapon.
	 * 
	 * @param the
	 *            new weapon to be set as current
	 */
	public void setCurrentWeapon(Weapon weapon) {
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
	 * Method for getting the health.
	 * 
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Method for receiving damage.
	 * 
	 * @param the
	 *            amount of damage that should be dealt
	 */
	public void takeDamage(int damage) {
		health -= health;
	}

	/**
	 * Method for getting the collision bounds of the model.
	 * 
	 * @return a rectangle representing the collision bounds
	 */
	public Rectangle2D.Float getBounds() {
			
		return new Rectangle2D.Float(x, y, width, height);
	}
}
