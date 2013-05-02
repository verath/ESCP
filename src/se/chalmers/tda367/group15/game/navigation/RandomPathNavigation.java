package se.chalmers.tda367.group15.game.navigation;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Mover;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import se.chalmers.tda367.group15.game.controllers.DummyEnemyController;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;

public class RandomPathNavigation implements NpcNavigation, Mover{

	private float newX;
	private float newY;
	private double newRot;
	private AStarPathFinder myPathFinder;
	private Path myPath;
	private int currentStep;
	private int counting;

	public RandomPathNavigation(TileBasedMap map) {
		this.myPathFinder = new AStarPathFinder(map, 5000, true);
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

		float curentX = (float) model.getX();
		float curentY = (float) model.getY();
		
		if (myPath == null) {
			System.out.println("HEJ2");
			myPath = myPathFinder.findPath(this, (int) curentX/32, (int) curentY/24, (int) (Math.random()*32), (int) (Math.random()*24));
			
			currentStep = 1;
			counting = 0;
		}
		if ( myPath != null ) {
			counting = counting +1;
			if ( counting%50==0) {
				this.newX = myPath.getX(currentStep)*32;
				this.newX = myPath.getY(currentStep)*24;
				currentStep = currentStep +1;
				if (myPath.getLength() == currentStep) {
					myPath = null;
				}
			}
			
		} else {
			this.newX = curentX;
			this.newX = curentY;
			this.newRot = model.getRotation();
		}
		
	}
}
