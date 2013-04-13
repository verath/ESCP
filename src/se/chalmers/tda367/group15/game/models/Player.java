package se.chalmers.tda367.group15.game.models;

import org.newdawn.slick.Animation;

/**
 * Interface for representing a player.
 * @author simon
 *
 */
public abstract class Player {
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
	 * Method for getting the current sprite animation of the player.
	 * @return current sprite animation
	 */
	public abstract Animation getCurrentSprite();
}
