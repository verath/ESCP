package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.CoworkerModel;

/**
 * Creates a new dummy enemy
 * 
 * @author Simon Persson, Carl Jansson
 * 
 */
public class CoworkerController extends AbstractNpcController {

	private boolean hasFired;

	/**
	 * Creates a new dummyenemy controller.
	 * 
	 * @param model
	 *            the DummyEnemy model
	 * @param navigator
	 *            The navigator to use.
	 * @param gameController
	 *            A reference to the controller
	 */
	public CoworkerController(CoworkerModel model, TileBasedMap map,
			GameController gameController) {
		super(gameController, model, map );
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		getView().render(container, g);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds)
			throws SlickException {
		if (hasFired) {
			swingWeapon();
			hasFired = false;
		}

		// Save old position
		float oldX = this.getModel().getX();
		float oldY = this.getModel().getY();

		// this moves the npc
		randomPosMove(container, delta, staticBounds, dynamicBounds);

		// get new position
		float newX = this.getModel().getX();
		float newY = this.getModel().getY();

		// Set whether model is moving or not
		this.getModel().setMoving(!((oldX == newX) || (oldY == newY)));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fire() {
		hasFired = true;

	}
}
