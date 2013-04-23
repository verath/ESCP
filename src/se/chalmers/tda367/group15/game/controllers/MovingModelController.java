package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.MovingModel;

/**
 * Interface that should be implemented by all controllers handling moving
 * models.
 * 
 * @author simon
 * 
 */
public interface MovingModelController {

	/**
	 * Method for rendering all views.
	 * 
	 * @param container
	 *            The container holding this game.
	 * @param g
	 *            The graphics context that can be used to render. However,
	 *            normal rendering routines can also be used.
	 * @throws SlickException
	 *             Throw to indicate a internal error
	 */
	public abstract void render(GameContainer container, Graphics g)
			throws SlickException;

	/**
	 * Method for updating the logic of all the models
	 * 
	 * @param container
	 *            The container holding this game.
	 * @param delta
	 *            The amount of time thats passed since last update in
	 *            milliseconds
	 * @throws SlickException
	 *             Throw to indicate a internal error
	 */
	public abstract void update(GameContainer container, int delta)
			throws SlickException;

	/**
	 * Method for returning the model this controller is handling
	 * @return model
	 */
	public abstract MovingModel getModel();
}
