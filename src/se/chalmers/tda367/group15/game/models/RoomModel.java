package se.chalmers.tda367.group15.game.models;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.room.RoomManager;

public class RoomModel implements Model {
	private final RoomManager roomManager;

	public RoomModel(final RoomManager roomManager) {
		this.roomManager = roomManager;
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		roomManager.getCurrentRoom().getRoomModel().update(container, delta);
	}

}
