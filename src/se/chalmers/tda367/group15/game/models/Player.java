package se.chalmers.tda367.group15.game.models;

/**
 * Interface for representing a player.
 * @author simon
 *
 */
public abstract class Player {
	private float x, y, vel;
	
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getVel() {
		return vel;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setVel(float vel) {
		this.vel = vel;
	}
}
