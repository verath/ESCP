package se.chalmers.tda367.group15.game.navigation;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import se.chalmers.tda367.group15.game.controllers.DummyEnemyController;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;

/**
 * This navigator makes DummyEnemyController walk to random positions. If it
 * reaches destination or hits something it calculates a new position.
 * 
 * @author Carl Jansson
 */
public class RandomPathNavigation implements NpcNavigation, Mover {

	private float newX;
	private float newY;
	private double newRot;
	private AStarPathFinder myPathFinder;
	private Path myPath;
	private int currentStep;

	/**
	 * Creates a new RandomPathNavigator for specific map.
	 * 
	 * @param map
	 */
	public RandomPathNavigation(TileBasedMap map) {
		this.myPathFinder = new AStarPathFinder(map, 500, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getNewX() {
		return newX;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getNewY() {
		return newY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getNewDirection() {
		return newRot;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(DummyEnemyController dummyController,
			AbstractMovingModel model, int delta, List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds) {

		newX = model.getX();
		newY = model.getY();

		int currX = (int) (model.getX() + (model.getWidth() / 2)) / 32;
		int currY = (int) (model.getY() + (model.getHeight() / 2)) / 32;

		if (myPath == null || currentStep == myPath.getLength()) {

			int tarX = (int) (Math.random() * 30);
			int tarY = (int) (Math.random() * 30);

			myPath = myPathFinder.findPath(null, currX, currY, tarX, tarY);
			currentStep = 1;
		} else {
			float diffX = model.getX() - (myPath.getX(currentStep) * 32);
			float diffY = model.getY() - (myPath.getY(currentStep) * 32);

			double dir = Math.atan2(diffY, diffX);
			newRot = Math.toDegrees(dir);

			float speedY = (float) (model.getVelocity() * Math.sin(dir));
			float speedX = (float) (model.getVelocity() * Math.cos(dir));

			float tmpNewX = model.getX() - (delta * speedX);
			float tmpNewY = model.getY() - (delta * speedY);

			if (dummyController.isDynamicCollision(tmpNewX, tmpNewY,
					dynamicBounds)) {
				myPath = null;
			} else {
				if (!dummyController.isCollision(tmpNewX, model.getY(),
						new ArrayList<Rectangle2D.Float>(), dynamicBounds)) {
					newX = tmpNewX;
				}

				if (!dummyController.isCollision(model.getX(), tmpNewY,
						new ArrayList<Rectangle2D.Float>(), dynamicBounds)) {
					newY = tmpNewY;
				}

				if (currX == myPath.getX(currentStep)
						&& currY == myPath.getY(currentStep)) {
					currentStep++;
				}
			}
		}
	}
}
