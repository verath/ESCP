package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.util.List;

import se.chalmers.tda367.group15.game.views.room.RoomManager;

/**
 * Interface for representing a moving model.
 * @author simon
 *
 */
public abstract class MovingModel {
	private float x, y, velocity;
	
	/**
	 * Method for getting the x coordinate of the player.
	 * @return x coordinate
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Method for getting the y coordinate of the player.
	 * @return y coordinate
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Method for getting the movement velocity of the player.
	 * @return the velocity
	 */
	public float getVelocity() {
		return velocity;
	}
	
	/**
	 * Method for setting the x coordinate of the player.
	 * @param x coordinate
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/**
	 * Method for setting the y coordinate of the player.
	 * @param y coordinate
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * Method for setting the movement velocity of the player.
	 * @param velocity
	 */
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Method for checking if the model has collided with a blocked tile.
	 * @return true if collision is detected, false otherwise
	 */
	public boolean isCollision() {
		RoomManager manager = RoomManager.getInstance();
		manager.generateCollisionBounds();
		List<Rectangle2D.Float> collisionBounds = manager.getCollisionBounds();
		Rectangle2D.Float tmp = new Rectangle2D.Float(getX(), getY(), 64, 64);
		boolean isCollision = false;
		for(Rectangle2D.Float r: collisionBounds) {
			if(r.intersects(tmp)) {
				isCollision = true;
			}
		}
		System.out.println(isCollision);
		return isCollision;
	}

}
