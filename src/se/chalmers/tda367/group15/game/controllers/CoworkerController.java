package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.CoworkerModel;
import se.chalmers.tda367.group15.game.util.CollisionHelper;

/**
 * Class for representing a coworker controller.
 * 
 * @author Simon Persson, Carl Jansson
 * 
 */
public class CoworkerController extends AbstractNpcController {

	private boolean hasFired;

	/**
	 * Creates a new CoworkerController.
	 * 
	 * @param model
	 *            the CoworkerModel model
	 * @param map
	 *            The map to use.
	 * @param gameController
	 *            A reference to the gameController
	 */
	public CoworkerController(CoworkerModel model, TileBasedMap map,
			GameController gameController) {
		super(gameController, model, map);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g) {
		getView().render(container, g);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds) {

		if (hasFired) {
			swingWeapon();
			hasFired = false;
		}

		AbstractMovingModel model = getModel();
		AbstractMovingModel heroModel = getGameController().getHeroController()
				.getModel();

		// Save old position
		float oldX = model.getX();
		float oldY = model.getY();

		// NPC current position
		int currX = (int) (oldX + (model.getWidth() / 2));
		int currY = (int) (oldY + (model.getHeight() / 2));

		float heroX = heroModel.getX() + heroModel.getWidth() / 2;
		float heroY = heroModel.getY() + heroModel.getHeight() / 2;

		// if hero is in sight.
		boolean heroTracking;
		if (CollisionHelper.isInSight(staticBounds, currX, currY, heroX, heroY)) {
			heroTracking = true;
			// New path after hero pos
			calculateNewPath((int) heroX / 32, (int) heroY / 32);
		} else {
			heroTracking = false;
		}

		// If path is null or end of path reached
		if (!existsPath()) {
			// After a short pause make new path.
			if (pauseTimer()) {
				calculateRandomPath();
			}
		} else {
			// travel along path
			moveAlongPath(model, delta, dynamicBounds);
		}

		// If hero is in sight set new direction and possibly attack
		if (heroTracking) {

			model.setRotation(Math.toDegrees(Math.atan2((currY - heroY),
					(currX - heroX))));

			// If hero is in reach and no other npc is in the way attack!
			if (Math.hypot(currX - heroX, currY - heroY) < 100
					&& isWayClear(dynamicBounds, currX, currY, heroX, heroY)) {
				fireTimed();
			}
		}

		// NPC new position
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
