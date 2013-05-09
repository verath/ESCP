package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
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
	 * waiting time start for creating new path
	 */
	private long pauseTime;

	/**
	 * Time to wait before creating new path
	 */
	private long waitTime;

	/**
	 * with of x to walk in
	 */
	private int deltaX;

	/**
	 * height of y to walk in
	 */
	private int deltaY;

	/**
	 * X coordinate of area to walk in
	 */
	private int startX;

	/**
	 * Y coordinate of area to walk in
	 */
	private int startY;

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
		this(gameController, pathFinder, 0, 32, 0, 24);
	}

	/**
	 * Create a new AbstractNpcController using a path finder and setting
	 * default tiles to visit
	 * 
	 * @param gameController
	 *            the main controller
	 * @param pathFinder
	 *            the path finder to use
	 * @param x
	 *            min x pos
	 * @param x2
	 *            max x pos
	 * @param y
	 *            min y pos
	 * @param y2
	 *            max y pos
	 */
	public AbstractNpcController(GameController gameController,
			AStarPathFinder pathFinder, int x, int x2, int y, int y2) {
		super(gameController);
		this.setpathFinder(pathFinder);
		this.setDefaultTiles(x, x2, y, y2);
		this.pauseTime = 0;
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
	 * Makes npc move to random position restricted by setDefaultTiles(). If
	 * collision occurs or target is reached a new random position is set as
	 * target.
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

		// If path is null or end of path reached
		if (myPath == null || currentStep == myPath.getLength()) {

			
			if (pauseTime == 0) {
				pauseTime = System.currentTimeMillis();
				waitTime = (long) (2000 * Math.random());
			} else if (System.currentTimeMillis() >= waitTime + pauseTime) {
				int tarX = startX + (int) (Math.random() * deltaX);
				int tarY = startY + (int) (Math.random() * deltaY);

				myPath = getPathFinder().findPath(null, currX, currY, tarX,
						tarY);
				currentStep = 1;
				pauseTime = 0;
			}

		// If traveling along path
		} else {
			float diffX = model.getX() - (myPath.getX(currentStep) * 32);
			float diffY = model.getY() - (myPath.getY(currentStep) * 32);

			double dir = Math.atan2(diffY, diffX);
			model.setRotation(Math.toDegrees(dir));

			float speedY = (float) (model.getVelocity() * Math.sin(dir));
			float speedX = (float) (model.getVelocity() * Math.cos(dir));

			float tmpNewX = model.getX() - (delta * speedX);
			float tmpNewY = model.getY() - (delta * speedY);

			// if dynamic collision set path to null so a new random path will be created
			if (isDynamicCollision(tmpNewX, tmpNewY, dynamicBounds)) {
				myPath = null;
			// Set the new positions
			} else {
				if (!isDynamicCollision(tmpNewX, model.getY(), dynamicBounds)) {
					model.setX(tmpNewX);
				}

				if (!isDynamicCollision(model.getX(), tmpNewY, dynamicBounds)) {
					model.setY(tmpNewY);
				}

				if (currX == myPath.getX(currentStep)
						&& currY == myPath.getY(currentStep)) {
					currentStep++;
				}
			}
		}
	}

	/**
	 * Set max and min positions. Of given coordinates not allowed sets entire
	 * map as default.
	 * 
	 * @param x
	 *            min x pos
	 * @param x2
	 *            max x pos
	 * @param y
	 *            min y pos
	 * @param y2
	 *            max y pos
	 */
	public void setDefaultTiles(int x, int x2, int y, int y2) {

		if (x >= 0 && y >= 0 && x < x2 && y < y2 && x2 <= 32 && y2 <= 24) {
			deltaX = x2 - x;
			deltaY = y2 - y;
			startX = x;
			startY = y;

		} else {
			deltaX = 32;
			deltaY = 24;
			startX = 0;
			startY = 0;

		}

	}

}
