package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.tda367.group15.game.models.Room;
import se.chalmers.tda367.group15.game.models.room.RoomManager;

public class RoomView implements Renderable {

	private RoomManager roomManager;
	private TiledMap map;

	/**
	 * Create a new room view
	 * @param the room assigned to the view
	 * @param the path to the tiled map
	 */
	public RoomView(Room startingroom) {
		roomManager = new RoomManager();
		roomManager.addStartingRoom(startingroom);
		try {
			map = new TiledMap(roomManager.getCurrentRoom().getTiledMapPath());
		} catch (SlickException e) {
			System.out.println("HEJ");
			e.printStackTrace();
		}
	}

	/**
	 * Method for getting the TiledMap of a room
	 * 
	 * @return the TiledMap
	 */
	public TiledMap getTiledMap() {
		return map;
	}

	public void render() {
		map.render(0, 0);
	}
}
