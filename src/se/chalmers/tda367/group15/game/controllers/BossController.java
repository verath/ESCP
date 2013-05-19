package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import se.chalmers.tda367.group15.game.models.AbstractCharacterModel;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.BossModel;
import se.chalmers.tda367.group15.game.util.CollisionHelper;

/**
 * NOTE: This class is pretty much hard coded!
 * Class for representing a boss controller
 * 
 * Boss is in boss room. Only moves in a small space in upper part of map. Path
 * Finding is supposed to not be used, instead has predefined scripted movement.
 * 
 * 
 * @author Simon Persson, Carl Jansson
 * 
 */
public class BossController extends AbstractNpcController {

	private boolean hasFired;

	private boolean heroTracking;

	private long swingTimer = 0;

	private int goalX;
	private int goalY;

	/**
	 * Creates a new boss controller.
	 * 
	 * @param model
	 *            the BossModel model
	 * @param map
	 *            The map to use.
	 * @param gameController
	 *            A reference to the gameController
	 */
	public BossController(BossModel model, TileBasedMap map,
			GameController gameController) {
		super(gameController, model, map);
		this.ENEMY_DAMAGE_MODIFIER = 3;
		this.goalX = -1;
		this.goalY = -1;
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
			createDonut();
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
		if (CollisionHelper.isInSight(staticBounds, currX, currY, heroX, heroY)) {
			heroTracking = true;
			// TODO Maybe go after hero?
		} else {
			heroTracking = false;
		}

		// If path is null or end of path reached
		if (this.goalX == -1 || this.goalY == -1) {
			
			// After a short pause make new path.
			if (pauseTimer()) {
				newRandomGoal();
			}
		} else {
			// travel along path
			moveToGoal(model, delta, staticBounds, dynamicBounds);
		}

		// If hero is in sight set new direction and possibly attack
		if (heroTracking) {

			model.setRotation(Math.toDegrees(Math.atan2((currY - heroY),
					(currX - heroX))));

			// If hero is in reach and no other npc is in the way attack!
			if (isWayClear(dynamicBounds, currX, currY, heroX, heroY)) {
				fireTimed();
			}
		}

		// NPC new position
		float newX = model.getX();
		float newY = model.getY();

		// Set whether model is moving or not
		this.getModel().setMoving(!((oldX == newX) || (oldY == newY)));

	}

	/**
	 * Make new random walking goal in hard coded space
	 */
	private void newRandomGoal() {
		int minX = 32 * 8;
		int maxX = (int) (32 * 22 - getModel().getWidth());
		int minY = 32 * 2;
		int maxY = (int) (32 * 5 - getModel().getHeight());

		int newX = (int) Math.round(minX + Math.random() * (maxX - minX));
		int newY = (int) Math.round(minY + Math.random() * (maxY - minY));

		newGoal(newX, newY);
	}

	/**
	 * Make new walking goal. Boss tries to walk straight to this!
	 * @param newX
	 * @param newY
	 */
	private void newGoal(int newX, int newY) {
		this.goalX = newX;
		this.goalY = newY;
	}

	/**
	 * Moves straight to the walking goal. If path blocked Boss will not be able to get loose.
	 * @param model
	 * @param delta
	 * @param staticBounds
	 * @param dynamicBounds
	 */
	private void moveToGoal(AbstractMovingModel model, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds) {
		float diffX = model.getX() - this.goalX;
		float diffY = model.getY() - this.goalY;

		double dir = Math.atan2(diffY, diffX);
		model.setRotation(Math.toDegrees(dir));

		float speedY = (float) (model.getVelocity() * Math.sin(dir));
		float speedX = (float) (model.getVelocity() * Math.cos(dir));

		float tmpNewX = model.getX() - (delta * speedX);
		float tmpNewY = model.getY() - (delta * speedY);

		if (!isCollision(tmpNewX, model.getY(), model.getWidth(),
				model.getHeight(), staticBounds, dynamicBounds)) {
			model.setX(tmpNewX);
		}

		if (!isCollision(model.getX(), tmpNewY, model.getWidth(),
				model.getHeight(), staticBounds, dynamicBounds)) {
			model.setY(tmpNewY);
		}

		if (Math.abs(model.getX() - this.goalX) < 10
				&& Math.abs(model.getY() - this.goalY) < 10) {
			this.goalX = -1;
			this.goalY = -1;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fire() {
		hasFired = true;

	}

	/**
	 * The boss is kinda slower than everybody else
	 */
	@Override
	public void fireTimed() {
		if (System.currentTimeMillis() - swingTimer > ((AbstractCharacterModel) getModel())
				.getCurrentWeapon().getFiringSpeed() * 2) {
			swingTimer = System.currentTimeMillis();
			fire();
		}
	}
}
