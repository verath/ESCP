package se.chalmers.tda367.group15.game.models;

import org.newdawn.slick.GameContainer;

/**
 * Interface representing an animated model in the game.
 * 
 * @author simon
 * 
 */
public interface AnimatedModel {
	/**
	 * Method for updating the logic of an animated model.
	 * 
	 * @param the
	 *            container holding the current game
	 * @param the
	 *            amount of time thats passed since last update in milliseconds
	 */
	public void update(GameContainer container, int delta);
}
