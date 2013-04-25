package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.MovingModel;
import se.chalmers.tda367.group15.game.views.View;

/**
 * Interface that should be implemented by all controllers handling moving
 * models.
 * 
 * @author simon
 * 
 */
public abstract class MovingModelController {

	/**
	 * The model that the controller is managing
	 */
	private MovingModel model;

	/**
	 * The view that the controller is managing
	 */
	private View view;

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
	 * @param dynamicBounds
	 * @param staticBounds
	 * @throws SlickException
	 *             Throw to indicate a internal error
	 */
	public abstract void update(GameContainer container, int delta,
			List<Float> staticBounds, Map<MovingModel, Float> dynamicBounds)
			throws SlickException;

	/**
	 * Method for getting the model that the controller is managing
	 * 
	 * @return the model
	 */
	public MovingModel getModel() {
		return model;
	}

	/**
	 * Method for getting the view that the controller is managing
	 * 
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/**
	 * Method for setting the model that the controller is managing
	 * 
	 * @param model
	 *            to be set
	 */
	public void setModel(MovingModel model) {
		this.model = model;
	}

	/**
	 * Method for setting the view that the controller is managing
	 * 
	 * @param view
	 *            to be set
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * Method for checking if collision is about to happen.
	 * 
	 * @param x
	 *            the x coordinate
	 * @param y
	 *            the y coordinate
	 * @param staticBounds
	 *            list of rectangles representing static blocked object on the
	 *            map
	 * @param dynamicBounds
	 *            map with moving models and their collisionbounds
	 * @return true if collision, false otherwise
	 */
	public boolean isCollision(float x, float y,
			List<Rectangle2D.Float> staticBounds,
			Map<MovingModel, Rectangle2D.Float> dynamicBounds) {

		boolean staticCollision = false;
		boolean dynamicCollsion = false;

		Rectangle2D.Float bound1 = new Rectangle2D.Float(x, y,
				model.getWidth(), model.getHeight());

		// check static collisions
		for (Rectangle2D.Float bound2 : staticBounds) {
			if (bound1.intersects(bound2))
				staticCollision = true;
		}

		// check dynamic collisions
		for (MovingModel otherModel : dynamicBounds.keySet()) {
			Rectangle2D.Float bound2 = otherModel.getBounds();
			if (bound1.intersects(bound2) && this.model != otherModel)
				dynamicCollsion = true;
		}
		return staticCollision || dynamicCollsion;
	}
}
