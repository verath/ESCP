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

public class RandomPathNavigation implements NpcNavigation, Mover {

	private float newX;
	private float newY;
	private double newRot;
	private AStarPathFinder myPathFinder;
	private Path myPath;
	private int currentStep;

	public RandomPathNavigation(TileBasedMap map) {
		this.myPathFinder = new AStarPathFinder(map, 500, true);
	}

	@Override
	public float getNewX() {
		return newX;
	}

	@Override
	public float getNewY() {
		return newY;
	}

	@Override
	public double getNewDirection() {
		return newRot;
	}

	@Override
	public void update(DummyEnemyController dummyController,
			AbstractMovingModel model, int delta, List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds) {

		int currX = (int) (model.getX() + (model.getWidth() / 2)) / 32;
		int currY = (int) (model.getY() + (model.getHeight() / 2)) / 32;

		if (myPath == null || currentStep == myPath.getLength()) {

			int tarX = (int) (Math.random() * 30);
			int tarY = (int) (Math.random() * 30);

			System.out.println("New target: " + tarX + " " + tarY);
			myPath = myPathFinder.findPath(null, currX, currY, tarX, tarY);
			currentStep = 1;
		} else {
			float diffX = model.getX() - (myPath.getX(currentStep) * 32);
			float diffY = model.getY() - (myPath.getY(currentStep) * 32);

			double dir = Math.atan2(diffY, diffX);
			model.setRotation(Math.toDegrees(dir));

			float speedY = (float) (model.getVelocity() * Math.sin(dir));
			float speedX = (float) (model.getVelocity() * Math.cos(dir));

			float newX = model.getX() - (delta * speedX);
			float newY = model.getY() - (delta * speedY);

			if (dummyController.isDynamicCollision(newX, newY, dynamicBounds)) {
				myPath = null;
			} else {
				if (!dummyController.isCollision(newX, model.getY(),
						new ArrayList<Rectangle2D.Float>(), dynamicBounds)) {
					model.setX(newX);
				}

				if (!dummyController.isCollision(model.getX(), newY,
						new ArrayList<Rectangle2D.Float>(), dynamicBounds)) {
					model.setY(newY);
				}

				if (currX == myPath.getX(currentStep)
						&& currY == myPath.getY(currentStep)) {
					currentStep++;
				}
			}
		}
	}
}
