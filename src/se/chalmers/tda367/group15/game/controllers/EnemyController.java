package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.controllers.room.RoomController;
import se.chalmers.tda367.group15.game.models.AbstractRoomModel;
import se.chalmers.tda367.group15.game.models.DummyEnemy;
import se.chalmers.tda367.group15.game.models.MovingModel;
import se.chalmers.tda367.group15.game.views.View;

public class EnemyController implements MovingModelController {

	/**
	 * A room controller set by the constructor of the class. The room
	 * controller is used to get views and models of the enemies in the current
	 * room.
	 */
	private RoomController roomController;

	/**
	 * Create a new controller for the enemies.
	 * 
	 * @param roomController
	 *            The room controller to be used
	 */
	public EnemyController(RoomController roomController) {
		this.roomController = roomController;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		AbstractRoomModel roomModel = roomController.getCurrentRoom()
				.getRoomModel();
		for (MovingModel m : roomModel.getMovingModels()) {
			if (m instanceof DummyEnemy) {
				move((DummyEnemy) m, delta);
			}
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		List<View> enemyViews = roomController.getCurrentRoom().getEnemyViews();
		for (View v : enemyViews) {
			v.render(container, g);
		}
	}

	public void move(DummyEnemy model, int delta) {
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

	private boolean isCollision(float x, float y) {
		List<Rectangle2D.Float> staticBounds = roomController.getCurrentRoom()
				.getRoomView().getCollisionBounds();
		// TODO collisionbounds for the hero is 2px smaller than it should. Just
		// as a quickfix for moving through doors..
		Rectangle2D.Float enemy = new Rectangle2D.Float(x, y, 64, 64);
		for (Rectangle2D.Float bound : staticBounds) {
			if (enemy.intersects(bound)) {
				return true;
			}
		}
		return false;
	}

}