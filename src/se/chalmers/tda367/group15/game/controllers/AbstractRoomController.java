package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;

/**
 * Abstract class for representing a room containing a tiled map and controllers
 * for all moving models.
 * 
 * @author simon
 * 
 */
public abstract class AbstractRoomController {

	/**
	 * Method for updating the logic of all the rooms
	 * 
	 * @param container
	 *            The container holding this game.
	 * @param delta
	 *            The amount of time thats passed since last update in
	 *            milliseconds.
	 * @param staticBounds
	 *            the static collision bounds to be sent to all controllers of
	 *            the room.
	 * @param dynamicBounds
	 *            the dynamic collision bounds to be sent to all controllers of
	 *            the room.
	 * @throws SlickException
	 *             Throw to indicate an internal error.
	 */
	public abstract void update(GameContainer container, int delta,
			List<Float> staticBounds, Map<AbstractMovingModel, Float> dynamicBounds)
			throws SlickException;

	/**
	 * Method for rendering all model views in the room.
	 * 
	 * @param container
	 *            The container holding this game.
	 * @param g
	 *            The graphics context that can be used to render. However,
	 *            normal rendering routines can also be used.
	 * @throws SlickException
	 *             Throw to indicate an internal error
	 */
	public abstract void render(GameContainer container, Graphics g)
			throws SlickException;

	/**
	 * Method for initializing the room.
	 * 
	 * @param container
	 *            The container holding this game.
	 * @param game
	 *            The game.
	 * @throws SlickException
	 *             Throw to indicate an internal error.
	 */
	public abstract void init(GameContainer container, StateBasedGame game)
			throws SlickException;

	public abstract List<Rectangle2D.Float> getStaticBounds();

	public abstract List<AbstractMovingModelController> getControllers();

	public abstract Map<AbstractMovingModel, Rectangle2D.Float> getDynamicBounds();
}
