package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.room.Room;
import se.chalmers.tda367.group15.game.room.RoomManager;

public class RoomView implements View {

	private final RoomManager roomManager;

	/**
	 * Create a new room view
	 * @param the room assigned to the view
	 * @param the path to the tiled map
	 */
	public RoomView(final RoomManager roomManager) {
		this.roomManager = roomManager;
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		roomManager.getCurrentRoom().getRoomView().render(container, g);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		for(Room r : roomManager ) {
			r.getRoomView().init(container);
		}
	}
}
