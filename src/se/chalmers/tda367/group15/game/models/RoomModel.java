package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.room.RoomManager;

public class RoomModel implements CollidableModel {
	
	private final RoomManager roomManager;

	public RoomModel(final RoomManager roomManager) {
		this.roomManager = roomManager;
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		roomManager.getCurrentRoom().getRoomModel().update(container, delta);
	}

	@Override
	public List<Float> getCollisionBounds() {
		return roomManager.getCurrentRoom().getRoomModel().getCollisionBounds();
	}


}
