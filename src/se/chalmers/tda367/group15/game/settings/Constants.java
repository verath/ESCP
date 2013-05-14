package se.chalmers.tda367.group15.game.settings;

import org.newdawn.slick.Input;

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
	 * Boolean controlling whether collision bounds should be drawn.
	 */
	// TODO: This property should be final.
	public static boolean SHOW_BOUNDS = false;

	/*
	 * The different states. They should each be a unique int.
	 */
	public static final int GAME_STATE_MAIN_MENU = 1;
	public static final int GAME_STATE_PLAYING = 2;
	public static final int GAME_STATE_OPTIONS_MENU = 3;
	public static final int GAME_STATE_GAME_OVER = 4;

	/*
	 * The resolutions of the games maps.
	 */
	public static final int GAME_WIDTH = 1024;
	public static final int GAME_HEIGHT = 768;

	/**
	 * The starting score of the game.
	 */
	public static final int STARTING_SCORE = 10_000;

	/**
	 * The default binding for going left
	 */
	public static final int DEFAULT_KEY_BIND_LEFT = Input.KEY_LEFT;

	/**
	 * The default binding for going up
	 */
	public static final int DEFAULT_KEY_BIND_UP = Input.KEY_UP;

	/**
	 * The default binding for going down
	 */
	public static final int DEFAULT_KEY_BIND_DOWN = Input.KEY_DOWN;

	/**
	 * The default binding for going right
	 */
	public static final int DEFAULT_KEY_BIND_RIGHT = Input.KEY_RIGHT;
}
