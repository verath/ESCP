package se.chalmers.tda367.group15.game.settings;

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
	public static final boolean DEBUG = false;

	/**
	 * Boolean controlling whether collision bounds should be drawn.
	 */
	// TODO: This property should be final.
	public static boolean SHOW_BOUNDS = false;

	/**
	 * The different states. They should each be a unique int.
	 */
	public static final int GAME_STATE_MENU_MAIN = 1;
	public static final int GAME_STATE_MENU_OPTIONS = 2;
	public static final int GAME_STATE_MENU_KEY_BINDS = 3;
	public static final int GAME_STATE_MENU_HIGH_SCORE = 4;
	public static final int GAME_STATE_PLAYING = 5;
	public static final int GAME_STATE_GAME_LOST = 6;
	public static final int GAME_STATE_GAME_WON = 7;

	/**
	 * The upper left corner of every menu
	 */
	public static final int MENU_UPPER_X = 200;
	public static final int MENU_UPPER_Y = 100;

	/**
	 * The resolutions of the games maps.
	 */
	public static final int GAME_WIDTH = 1024;
	public static final int GAME_HEIGHT = 768;

	/**
	 * The starting score of the game.
	 */
	public static final int STARTING_SCORE = 600;

	/**
	 * The amount of milliseconds between each decrease in score.
	 */
	public final static int SCORE_DECREASE_INTERVAL = 500;
}
