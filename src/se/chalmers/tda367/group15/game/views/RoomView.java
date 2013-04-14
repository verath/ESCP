package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.tda367.group15.game.views.room.RoomManager;

public class RoomView implements View {

	private final RoomManager roomManager;
	private TiledMap map;

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
		map.render(0, 0);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		map = new TiledMap(roomManager.getCurrentRoom().getTiledMapPath());
	}
}
