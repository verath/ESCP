package se.chalmers.tda367.group15.game.navigation;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import se.chalmers.tda367.group15.game.controllers.DummyEnemyController;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;

public class RandomNavigation implements NpcNavigation {

	private float newX;
	private float newY;
	private double newRot;
	/**
	 * Creates a new random navigation controller.
	 */
	public RandomNavigation() {
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
	public void update(DummyEnemyController dummyController, AbstractMovingModel model, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds) {
		
		double rot = model.getRotation();
		
		float tmpX = (float) (model.getX() - Math.cos(Math.PI * rot / 180) * delta * model.getVelocity());
		float tmpY = (float) (model.getY() - Math.sin(Math.PI * rot / 180) * delta * model.getVelocity());
		
		boolean collision = dummyController.isCollision(tmpX, tmpY, staticBounds,
				dynamicBounds);
		
		if ( collision ) {
			this.newRot = Math.random() * 360;
		} else {
			this.newRot = rot;
			this.newX = tmpX;
			this.newY = tmpY;
		}
	}
}
