package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.DummyEnemyModel;

public class BasicRoomController extends AbstractRoomController {

	protected BasicRoomController(GameController gameController) {
		super(gameController);
	}

	private TiledMap map;
	private List<AbstractMovingModelController> movingModelControllers = new ArrayList<AbstractMovingModelController>();
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
		movingModelControllers.add(new DummyEnemyController(e1, getGameController()));

		for (AbstractMovingModelController controller : movingModelControllers) {
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
		Iterator<AbstractMovingModelController> it = movingModelControllers.iterator();
		while(it.hasNext()) {
			AbstractMovingModelController controller = (AbstractMovingModelController) it.next();
			AbstractMovingModel model = controller.getModel();
			if(!model.isAlive()) {
				it.remove();
			}
		}
		int i = 0;

		for (AbstractMovingModelController controller : movingModelControllers) {
			i++;
			controller.update(container, delta, staticBounds, dynamicBounds);
		}
		System.out.println(i);
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
		for (AbstractMovingModelController controller : movingModelControllers) {
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
		return movingModelControllers;
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
		Iterator<AbstractMovingModel> it = dynamicBounds.keySet().iterator();
		while(it.hasNext()) {
			AbstractMovingModel model = (AbstractMovingModel) it.next();
			if(model.isAlive()) {
				dynamicBounds.put(model, model.getBounds());
			}else{
				it.remove();
			}
		}
		return dynamicBounds;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addProjectile(AbstractMovingModel projectile) {
		movingModelControllers.add(new ProjectileController(getGameController(), projectile));
		dynamicBounds.put(projectile, projectile.getBounds());
	}

}
