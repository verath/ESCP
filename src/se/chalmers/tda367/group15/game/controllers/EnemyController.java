package se.chalmers.tda367.group15.game.controllers;

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
				m.setX(m.getX() + (m.getVelocity() * delta));
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

}
