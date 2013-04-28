package se.chalmers.tda367.group15.game.navigation;

/**
 * Interface for npc behavior pattern.
 * @author Carl Jansson
 *
 */
public interface NpcNavigation {
	
	/**
	 * 
	 * @param x current x position
	 * @param rot current rotation
	 * @param delta time since last update
	 * @param speed how fast we are moving 
	 * @return a new x position
	 */
	public float getNewX(float x, double rot, int delta, float speed);
	
	/**
	 * 
	 * @param y current y position
	 * @param rot current rotation
	 * @param delta time since last update
	 * @param speed how fast we are moving 
	 * @return a new y position
	 */
	public float getNewY(float y, double rot, int delta, float speed);
	
	/**
	 * @return a new direction
	 */
	public double getNewDirection();
}
