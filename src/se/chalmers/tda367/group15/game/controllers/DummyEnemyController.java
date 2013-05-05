package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.DummyEnemyModel;
import se.chalmers.tda367.group15.game.views.DummyEnemyView;

/**
 * Creates a new dummy enemy
 * @author ?????, Carl Jansson
 *
 */
public class DummyEnemyController extends AbstractNpcController {

	/**
	 * Creates a new dummyenemy controller with navigation of your choice.
	 * 
	 * @param model
	 *            the DummyEnemy model
	 * @param navigator
	 *            The navigator to use.
	 * @param gameController
	 *            A reference to the controller
	 */
	public DummyEnemyController(DummyEnemyModel model, TileBasedMap map,
			GameController gameController) {
		super(gameController, new AStarPathFinder(map, 500, true));
		setModel(model);
		setView(new DummyEnemyView(getModel()));
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

		randomPosMove(container, delta, staticBounds, dynamicBounds);

	}
}
