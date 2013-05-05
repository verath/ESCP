package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;

/**
 * AbstractNpcController implements methods for pathfinding and movement of npc
 * characters.
 * 
 * @author Carl Jansson
 */
public abstract class AbstractNpcController extends
		AbstractMovingModelController {

	/**
	 * The path controller is traveling
	 */
	private Path myPath;

	/**
	 * Current step of the path
	 */
	private int currentStep;

	/**
	 * the path finder
	 */
	private AStarPathFinder myPathFinder;

	/**
	 * Create a new AbstractNpcController using a map.
	 * 
	 * @param gameController
	 *            the main controller
	 * @param map
	 *            the map to path find on
	 */
	public AbstractNpcController(GameController gameController, TileBasedMap map) {
		this(gameController, new AStarPathFinder(map, 500, true));
	}

	/**
	 * Create a new AbstractNpcController using a path finder.
	 * 
	 * @param gameController
	 *            the main controller
	 * @param pathFinder
	 *            the path finder to use
	 */
	public AbstractNpcController(GameController gameController,
			AStarPathFinder pathFinder) {
		super(gameController);
		this.setpathFinder(pathFinder);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void render(GameContainer container, Graphics g)
			throws SlickException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds)
			throws SlickException;

	/**
	 * Set path finder
	 * 
	 * @param pathFinder
	 *            the pathfinder to use
	 */
	public void setpathFinder(AStarPathFinder pathFinder) {
		this.myPathFinder = pathFinder;
	}

	/**
	 * get current pathfinder
	 * 
	 * @return the active pathfinder
	 */
	public AStarPathFinder getPathFinder() {
		return myPathFinder;
	}

	/**
	 * Makes npc move to random positions. If collision occurs or target is
	 * reached a new random position is set as target.
	 * 
	 * @param container
	 *            The container holding this game.
	 * @param delta
	 *            The amount of time thats passed since last update in
	 *            milliseconds
	 * @param dynamicBounds
	 *            the dynamic bounds of moving objects
	 * @param staticBounds
	 *            the static bounds of current map
	 * @throws SlickException
	 *             Throw to indicate a internal error
	 */
	public void randomPosMove(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds)
			throws SlickException {

		AbstractMovingModel model = getModel();

		int currX = (int) (model.getX() + (model.getWidth() / 2)) / 32;
		int currY = (int) (model.getY() + (model.getHeight() / 2)) / 32;

		if (myPath == null || currentStep == myPath.getLength()) {

			int tarX = (int) (Math.random() * 30);
			int tarY = (int) (Math.random() * 30);

			myPath = getPathFinder().findPath(null, currX, currY, tarX, tarY);
			currentStep = 1;
		} else {
			float diffX = model.getX() - (myPath.getX(currentStep) * 32);
			float diffY = model.getY() - (myPath.getY(currentStep) * 32);

			double dir = Math.atan2(diffY, diffX);
			model.setRotation(Math.toDegrees(dir));

			float speedY = (float) (model.getVelocity() * Math.sin(dir));
			float speedX = (float) (model.getVelocity() * Math.cos(dir));

			float tmpNewX = model.getX() - (delta * speedX);
			float tmpNewY = model.getY() - (delta * speedY);

			if (isDynamicCollision(tmpNewX, tmpNewY, dynamicBounds)) {
				myPath = null;
			} else {
				if (!isCollision(tmpNewX, model.getY(),
						new ArrayList<Rectangle2D.Float>(), dynamicBounds)) {
					model.setX(tmpNewX);
				}

				if (!isCollision(model.getX(), tmpNewY,
						new ArrayList<Rectangle2D.Float>(), dynamicBounds)) {
					model.setY(tmpNewY);
				}

				if (currX == myPath.getX(currentStep)
						&& currY == myPath.getY(currentStep)) {
					currentStep++;
				}
			}
		}
	}

}
