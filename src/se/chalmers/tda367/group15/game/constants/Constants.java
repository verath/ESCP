package se.chalmers.tda367.group15.game.constants;

/**
 * A class for holding constant values that are global across the entire game.
 * Such as the game name, preferred FPS and preferred size.
 * 
 * @author Peter
 * 
 */
public class Constants {
	/**
	 * The name of the game.
	 */
	public static final String GAME_NAME = "Psycho Hero";

	/**
	 * Boolean controlling whether we should print debug output or not.
	 */
	public static final boolean DEBUG = true;
	
	
	/**
	 * Ints for controlling gamestate. They should all be uniqe.
	 */
	public static final int MAIN_MENU = 1;
	public static final int PLAYING = 2;

}
