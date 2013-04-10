package se.chalmers.tda367.group15.game.models.level;


/**
 * A basic level class for the game Psycho Hero.
 * 
 * @author tholene
 *
 */
public class Level {
	
	public int width;
	public int height;
	
	
	/**
	 * Constructs a new level with a given width and height.
	 * 
	 * @param width The width of this level.
	 * @param height The height of this level.
	 */
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
	}
}
