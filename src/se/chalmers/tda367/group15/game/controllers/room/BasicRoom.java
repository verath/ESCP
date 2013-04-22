package se.chalmers.tda367.group15.game.controllers.room;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.tda367.group15.game.models.AbstractRoomModel;
import se.chalmers.tda367.group15.game.models.DummyEnemy;
import se.chalmers.tda367.group15.game.models.MovingModel;
import se.chalmers.tda367.group15.game.models.room.BasicRoomModel;
import se.chalmers.tda367.group15.game.views.DummyEnemyView;
import se.chalmers.tda367.group15.game.views.View;

public class BasicRoom extends Room {

	private AbstractRoomModel roomModel;
	private TiledMap map;
	private List<MovingModel> movingModels = new ArrayList<MovingModel>();
	private List<View> enemyViews = new ArrayList<View>();
	private List<Rectangle2D.Float> staticBounds = new ArrayList<Rectangle2D.Float>();

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.roomModel = new BasicRoomModel();
		this.map = new TiledMap("res/levels/untitled.tmx");
		this.movingModels = roomModel.getMovingModels();
		for (MovingModel model : movingModels) {
			// Använd en factory, typ:
			// EnemyViewFactory.createView(model)
			enemyViews.add(new DummyEnemyView((DummyEnemy) model));
		}

	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		for (MovingModel model : movingModels) {
			float newX = model.getX() + (model.getVelocity() * delta);
			float newY = model.getY();

			if (isCollision(newX, newY)) {
				model.setRotation(model.getRotation() + 180);
			}

			if (model.getRotation() == 180) {
				model.setX(model.getX() + (model.getVelocity() * delta));
			} else {
				model.setX(model.getX() - (model.getVelocity() * delta));

			}
		}

	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		map.render(0, 0);
		for (View view : enemyViews) {
			view.render(container, g);
		}
	}

	public boolean isCollision(float newX, float newY) {
		Rectangle2D.Float enemy = new Rectangle2D.Float(newX, newY, 64, 64);
		for (Rectangle2D.Float bound : staticBounds) {
			if (enemy.intersects(bound)) {
				return true;
			}
		}

		return false;
	}

	@Override
	public List<Float> getCollisionBounds() {
		return staticBounds;
	}

}
