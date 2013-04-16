package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * Interface for representing a moving model.
 * 
 * @author simon
 * 
 */
public abstract class MovingModel implements Model {
	private float x, y, velocity;

	/**
	 * Method for getting the x coordinate of the player.
	 * 
	 * @return x coordinate
	 */
	public float getX() {
		return x;
	}

	/**
	 * Method for getting the y coordinate of the player.
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
	 * Method for setting the x coordinate of the player.
	 * 
	 * @param x
	 *            coordinate
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Method for setting the y coordinate of the player.
	 * 
	 * @param y
	 *            coordinate
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Method for setting the movement velocity of the player.
	 * 
	 * @param velocity
	 */
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	/*
	 * RoomManager manager = RoomManager.getInstance(); Room currentRoom =
	 * manager.getCurrentRoom(); List<Rectangle2D.Float> collisionBounds =
	 * currentRoom .getCollisionBounds();
	 */

	/**
	 * Method for checking if the model has collided with a blocked tile.
	 * 
	 * @param collisionBounds the collision bounds to check against
	 * @return true if collision is detected, false otherwise
	 */
	public boolean isCollision(List<Rectangle2D.Float> collisionBounds) {
		Rectangle2D.Float modelBounds = new Rectangle2D.Float(getX(), getY(), 64, 64);
		boolean isCollision = false;
		for (Rectangle2D.Float r : collisionBounds) {
			if (r.intersects(modelBounds)) {
				isCollision = true;
				break;
			}
		}
		return isCollision;
	}

}
