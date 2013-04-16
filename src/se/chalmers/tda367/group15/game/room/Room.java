package se.chalmers.tda367.group15.game.room;

import se.chalmers.tda367.group15.game.models.AbstractRoomModel;
import se.chalmers.tda367.group15.game.views.AbstractRoomView;

/**
 * A Room object holding a view and a model representing a room. A room can be
 * seen as a level in the game.
 * 
 * @author Peter
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
		roomView.setRoomModel(roomModel);
	}

	/**
	 * Gets the view associated with the room.
	 * @return
	 */
	public AbstractRoomView getRoomView() {
		return roomView;
	}
	
	/**
	 * Gets the model associated with the room.
	 * @return
	 */
	public AbstractRoomModel getRoomModel() {
		return roomModel;
	}
}
