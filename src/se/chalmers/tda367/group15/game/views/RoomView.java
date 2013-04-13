package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.tda367.group15.game.models.Room;

public class RoomView implements Renderable {

	private Room room;
	private TiledMap map;

	/**
	 * Create a new room view
	 * @param the room assigned to the view
	 * @param the path to the tiled map
	 */
	public RoomView(Room room) {
		this.room = room;
		try {
			map = new TiledMap(room.getTiledMapPath());
		} catch (SlickException e) {
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
	
	/**
	 * Method for getting the room assigned to this object
	 * @return
	 */
	public Room getRoom() {
		return room;
	}

	public void render() {
		map.render(0, 0);
		
	}
}
