package se.chalmers.tda367.group15.game.models;

/**
 * Interface for a PsychoHeroGame. Used to allow for the possibility of
 * different implementations of the game.
 * 
 * @author Peter
 * 
 */
public interface PsychoHeroGame {
	/**
	 * Starts the game
	 */
	public void start();
	
	/**
	 * Stops the game
	 */
	public void stop();
}
