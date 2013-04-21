package se.chalmers.tda367.group15.game.controllers.room;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.group15.game.models.AbstractRoomModel;
import se.chalmers.tda367.group15.game.models.DummyEnemy;
import se.chalmers.tda367.group15.game.models.MovingModel;
import se.chalmers.tda367.group15.game.views.AbstractRoomView;
import se.chalmers.tda367.group15.game.views.DummyEnemyView;
import se.chalmers.tda367.group15.game.views.View;

/**
 * A Room object holding a view and a model representing a room. A room can be
 * seen as a level in the game. The room also holds all the views of the moving
 * models from the room model.
 * 
 * @author Peter, Simon
 * 
 */
public class Room {
	/**
	 * The view representing this room
	 */
	private final AbstractRoomView roomView;

	/**
	 * The model representing this room
	 */
	private final AbstractRoomModel roomModel;

	/**
	 * A list of all the enemy views. Used in the EnemyController to render all
	 * the enemies.
	 */
	private final List<View> enemyViews = new ArrayList<View>();

	/**
	 * Creates a new Room with the provided view and model. None of the
	 * arguments may be null.
	 * 
	 * @param roomView
	 * @param roomModel
	 */
	public Room(final AbstractRoomView roomView,
			final AbstractRoomModel roomModel) {
		this.roomModel = roomModel;
		this.roomView = roomView;
		for (MovingModel m : roomModel.getMovingModels()) {
			if (m instanceof DummyEnemy) {
				enemyViews.add(new DummyEnemyView((DummyEnemy) m));
			}
		}
	}

	/**
	 * Gets the view associated with the room.
	 * 
	 * @return the room view
	 */
	public AbstractRoomView getRoomView() {
		return roomView;
	}

	/**
	 * Gets the model associated with the room.
	 * 
	 * @return the room model
	 */
	public AbstractRoomModel getRoomModel() {
		return roomModel;
	}

	/**
	 * Metod for getting all the enemy views of the current room. Used by the
	 * enemy controller to render all the enemies.
	 * 
	 * @return the enemy views
	 */
	public List<View> getEnemyViews() {
		return enemyViews;
	}
}
