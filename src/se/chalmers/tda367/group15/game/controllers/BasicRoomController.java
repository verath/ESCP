package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.util.pathfinding.PathFindingContext;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.DummyEnemyModel;
import se.chalmers.tda367.group15.game.navigation.RandomPathNavigation;

public class BasicRoomController extends AbstractRoomController {

	protected BasicRoomController(GameController gameController) {
		super(gameController);
	}

	private TiledMap map;
	private List<AbstractMovingModelController> enemyControllers = new ArrayList<AbstractMovingModelController>();
	private List<Rectangle2D.Float> staticBounds;
	private Map<AbstractMovingModel, Rectangle2D.Float> dynamicBounds;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(GameContainer container) throws SlickException {

		staticBounds = new ArrayList<Rectangle2D.Float>();
		dynamicBounds = new HashMap<AbstractMovingModel, Rectangle2D.Float>();

		this.map = new TiledMap("res/levels/untitled.tmx");

		// add static collision bounds
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				int tileID = map.getTileId(i, j, 1);
				String property = map.getTileProperty(tileID, "blocked",
						"false");
				if (property.equals("true")) {
					staticBounds.add(new Rectangle2D.Float(i * 32, j * 32, 32,
							32));
				}
			}
		}

		// create an enemy, and add controller for that enemy to the update list
		DummyEnemyModel e1 = new DummyEnemyModel();
		DummyEnemyModel e2 = new DummyEnemyModel(400, 200);
		DummyEnemyModel e3 = new DummyEnemyModel(100, 270);
		DummyEnemyModel e4 = new DummyEnemyModel(200, 200);
		DummyEnemyModel e5 = new DummyEnemyModel(200, 270);
		enemyControllers.add(new DummyEnemyController(e1, new RandomPathNavigation(this), getGameController()));
		enemyControllers.add(new DummyEnemyController(e2, new RandomPathNavigation(this), getGameController()));
		enemyControllers.add(new DummyEnemyController(e3, new RandomPathNavigation(this), getGameController()));
		enemyControllers.add(new DummyEnemyController(e4, new RandomPathNavigation(this), getGameController()));
		enemyControllers.add(new DummyEnemyController(e5, new RandomPathNavigation(this), getGameController()));

		for (AbstractMovingModelController controller : enemyControllers) {
			AbstractMovingModel model = controller.getModel();
			dynamicBounds.put(model, model.getBounds());
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds)
			throws SlickException {

		// tell enemy controllers to move
		for (AbstractMovingModelController controller : enemyControllers) {
			controller.update(container, delta, staticBounds, dynamicBounds);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		// render map
		map.render(0, 0);

		// tell enemy controllers to render all enemy views
		for (AbstractMovingModelController controller : enemyControllers) {
			controller.render(container, g);
		}

		if (Constants.SHOW_BOUNDS) {
			g.setColor(Color.red);
			for (Rectangle2D.Float e : staticBounds) {
				g.drawRect((int) e.getX(), (int) e.getY(), (int) e.getWidth(),
						(int) e.getHeight());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AbstractMovingModelController> getControllers() {
		return enemyControllers;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Float> getStaticBounds() {
		return staticBounds;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<AbstractMovingModel, Rectangle2D.Float> getDynamicBounds() {
		// update bounds
		for (AbstractMovingModel model : dynamicBounds.keySet()) {
			if(model.isAlive()) {
				dynamicBounds.put(model, model.getBounds());
			}else{
				dynamicBounds.remove(model);
			}
		}
		return dynamicBounds;
	}

	@Override
	public int getWidthInTiles() {
		return map.getWidth(); //*map.getTileWidth(); if using pixels instead
	}

	@Override
	public int getHeightInTiles() {
		return map.getHeight(); //*map.getTileHeight(); if using pixels instead
	}

	@Override
	public void pathFinderVisited(int x, int y) {
		// This is for debugging new heuristics.
	}

	@Override
	public boolean blocked(PathFindingContext context, int tx, int ty) {
		// TODO How to use PathFindingContext?
		try {
			int tileID = map.getTileId(tx, ty, 1);
			String property = map.getTileProperty(tileID, "blocked",
					"false");
			if (property.equals("false")) {
				return false;
			}
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			return true;
		}
	}

	@Override
	public float getCost(PathFindingContext context, int tx, int ty) {
		return 1;
	}

}
