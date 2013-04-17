package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.room.RoomManager;

/**
 * The current room's model. Uses the RoomManager class to dynamically represent
 * the current room's model.
 * 
 * @author Peter
 * 
 */
public class RoomModel implements CollidableModel {

	private final RoomManager roomManager;

	/**
	 * Sets up a new RoomModel with the provided roomManager
	 * @param roomManager
	 */
	public RoomModel(final RoomManager roomManager) {
		this.roomManager = roomManager;
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		roomManager.getCurrentRoom().getRoomModel().update(container, delta);
	}

	@Override
	public List<Rectangle2D.Float> getCollisionBounds() {
		return roomManager.getCurrentRoom().getRoomModel().getCollisionBounds();
	}

}
