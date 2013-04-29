package se.chalmers.tda367.group15.game.navigation;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import se.chalmers.tda367.group15.game.controllers.DummyEnemyController;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;

public class RotatingNavigation implements NpcNavigation {
	
	private float newX;
	private float newY;
	private double newRot;
	
	public RotatingNavigation() {
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

		double rot = model.getRotation();
		
		float tmpX = (float) (model.getX() - Math.cos(Math.PI * rot / 180) * delta * model.getVelocity());
		float tmpY = (float) (model.getY() - Math.sin(Math.PI * rot / 180) * delta * model.getVelocity());
		
		boolean collision = dummyController.isCollision(tmpX, tmpY, staticBounds,
				dynamicBounds);
		
		
		this.newRot = (rot+2)%360;
		if (!collision) {
			this.newX = tmpX;
			this.newY = tmpY;
		}
	}

}
