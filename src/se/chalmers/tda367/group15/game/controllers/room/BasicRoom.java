package se.chalmers.tda367.group15.game.controllers.room;

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
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.tda367.group15.game.controllers.DummyEnemyController;
import se.chalmers.tda367.group15.game.controllers.MovingModelController;
import se.chalmers.tda367.group15.game.models.DummyEnemy;
import se.chalmers.tda367.group15.game.models.MovingModel;

public class BasicRoom extends Room {

	private TiledMap map;
	private List<MovingModelController> enemyControllers = new ArrayList<MovingModelController>();
	private List<Rectangle2D.Float> staticBounds;
	private Map<MovingModel, Rectangle2D.Float> dynamicBounds;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		staticBounds = new ArrayList<Rectangle2D.Float>();
		dynamicBounds = new HashMap<MovingModel, Rectangle2D.Float>();

		this.map = new TiledMap("res/levels/untitled.tmx");

		// add static collision bounds
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				int tileID = map.getTileId(i, j, 1);
				String property = map.getTileProperty(tileID, "blocked", "false");
				if(property.equals("true")) {
					staticBounds.add(new Rectangle2D.Float(i * 32, j * 32, 32, 32));
				}
			}
		}

		// create an enemy, and add controller for that enemy to the update list
		DummyEnemy e1 = new DummyEnemy();
		enemyControllers.add(new DummyEnemyController(e1));

		for (MovingModelController controller : enemyControllers) {
			MovingModel model = controller.getModel();
			dynamicBounds.put(model, model.getBounds());
		}

	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {

		// tell enemy controllers to move
		for (MovingModelController controller : enemyControllers) {
			controller.update(container, delta);
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		// render map
		map.render(0, 0);

		// tell enemy controllers to render all enemy views
		for (MovingModelController controller : enemyControllers) {
			controller.render(container, g);
		}
		
		g.setColor(Color.red);
		for(Rectangle2D.Float e : staticBounds) {
			g.drawRect((int)e.getX(), (int)e.getY(), (int)e.getWidth(), (int)e.getHeight());
		}
		
		for(MovingModelController controller : enemyControllers) {
			Rectangle2D.Float e = controller.getModel().getBounds();
			g.drawRect((int)e.getX(), (int)e.getY(), (int)e.getWidth(), (int)e.getHeight());
		}
	}

	@Override
	public List<MovingModelController> getControllers() {
		return enemyControllers;
	}

	@Override
	public List<Float> getStaticBounds() {
		return staticBounds;
	}

	public Map<MovingModel, Rectangle2D.Float> getDynamicBounds() {
		// update bounds
		for(MovingModel model : dynamicBounds.keySet()) {
			dynamicBounds.put(model, model.getBounds());
		}
		return dynamicBounds;
	}

}
