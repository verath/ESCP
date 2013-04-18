package se.chalmers.tda367.group15.game.models;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

/**
 * An interface representing a model in the game.
 * 
 * @author Peter
 * 
 */
public interface Model {
	/**
	 * Update the game logic here. No rendering should take place in this method
	 * though it won't do any harm.
	 * 
	 * @param container
	 *            The container holing this game
	 * @param delta
	 *            The amount of time thats passed since last update in
	 *            milliseconds
	 * @throws SlickException
	 *             Throw to indicate an internal error
	 */
	public void update(GameContainer container, int delta)
			throws SlickException;
}
