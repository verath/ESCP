package se.chalmers.tda367.group15.game.controllers.room;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Map<TiledMap, Rectangle2D.Float> mapCollisionBounds;
	private Map<MovingModel, Rectangle2D.Float> enemyCollisionBounds;

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		mapCollisionBounds = new HashMap<TiledMap, Rectangle2D.Float>();
		enemyCollisionBounds = new HashMap<MovingModel, Rectangle2D.Float>();
		this.map = new TiledMap("res/levels/untitled.tmx");

		// generate map collision bounds
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				mapCollisionBounds
						.put(map, new Rectangle2D.Float(i, j, 32, 32));
			}
		}

		// create an enemy, and add controller for that enemy to the update list
		DummyEnemy e1 = new DummyEnemy();
		enemyControllers.add(new DummyEnemyController(e1));

		for (MovingModelController controller : enemyControllers) {
			MovingModel model = controller.getModel();
			enemyCollisionBounds.put(model, new Rectangle2D.Float(model.getX(),
					model.getY(), model.getHeight(), model.getWidth()));
		}

	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		
		// tell enemy controllers to move
		for (MovingModelController controller : enemyControllers) {
			controller.update(container, delta);
		}

		// update the enemy collision bounds
		for (MovingModel model : enemyCollisionBounds.keySet()) {
			enemyCollisionBounds.put(model, new Rectangle2D.Float(model.getX(),
					model.getY(), model.getWidth(), model.getHeight()));
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
	}

	@Override
	public Map<MovingModel, Rectangle2D.Float> dynamicCollisionMap() {
		return enemyCollisionBounds;
	}

	@Override
	public Map<TiledMap, Rectangle2D.Float> staticCollisionMap() {
		return mapCollisionBounds;
	}

}
