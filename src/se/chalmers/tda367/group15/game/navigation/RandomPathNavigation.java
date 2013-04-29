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

	private AStarPathFinder myPathFinder;
	private Path myPath;

	public RandomPathNavigation(TileBasedMap map) {
		this.myPathFinder = new AStarPathFinder(map, 500, true);
	}

	@Override
	public float getNewX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getNewY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getNewDirection() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(DummyEnemyController dummyController,
			AbstractMovingModel model, int delta, List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds) {
		// TODO Auto-generated method stub
		
	}
}
