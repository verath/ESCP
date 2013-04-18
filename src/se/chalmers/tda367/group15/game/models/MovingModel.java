package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * An abstract class for representing a moving model that can both collide and
 * be collided with.
 * 
 * @author simon
 * 
 */
public abstract class MovingModel implements CollidingModel, CollidableModel {
	private float x, y, velocity;
//	private Weapon currentWeapon;
	private int health;

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
	 * Method for setting the movement velocity of the MovingModel.
	 * 
	 * @param velocity
	 */
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	/**
	 * Method for checking if the model has collided with a blocked tile.
	 * 
	 * @param collisionBounds
	 *            the collision bounds to check against
	 * @return true if collision is detected, false otherwise
	 */
	public boolean isCollision(List<Rectangle2D.Float> collisionBounds) {
		List<Rectangle2D.Float> modelBounds = getCollisionBounds();

		boolean isCollision = false;
		for (Rectangle2D.Float mb : modelBounds) {
			for (Rectangle2D.Float r : collisionBounds) {
				// Don't check against our own bounds
				if (modelBounds.contains(r)) {
					continue;
				} else if (r.intersects(mb)) {
					isCollision = true;
					break;
				}
			}
		}
		return isCollision;
	}
	
	/**
	 * Method for getting the current weapon.
	 * @return the current weapon
	 */
	public Weapon getCurrentWeapon() {
		return weapon;
	}
	
	/**
	 * Method for setting the current weapon.
	 * @param the new weapon to be set as current
	 */
	public void setCurrentWeapon(Weapon weapon) {
		currentWeapon = weapon;
	}
	
	/**
	 * Method for setting the health.
	 * @param health - preferably 0-100
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Method for getting the health.
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Method for receiving damage.
	 * @param the amount of damage that should be dealt
	 */
	public void takeDamage(int damage) {
		health -= health;
	}
}
