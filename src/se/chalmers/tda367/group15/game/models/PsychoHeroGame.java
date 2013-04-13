package se.chalmers.tda367.group15.game.models;

import java.util.List;

import se.chalmers.tda367.group15.game.views.Renderable;

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
