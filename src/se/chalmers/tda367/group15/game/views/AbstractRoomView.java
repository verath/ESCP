package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * An abstract representation of a view for a room.
 * 
 * @author Peter
 * 
 */
abstract public class AbstractRoomView implements View {

	/**
	 * The tiled map used by this room.
	 */
	private TiledMap map = null;

	public AbstractRoomView(String mapPath) {
		try {
			map = new TiledMap(mapPath);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method for getting the tiled map of the room
	 * 
	 * @return The TiledMap representing the room.
	 */
	public TiledMap getTiledMap() {
		return map;
	}

	/**
	 * Sets a TiledMap as the current TiledMap representing this room.
	 * 
	 * @param map
	 *            A TiledMap representing the room. Must not be null
	 */
	protected void setTiledMap(final TiledMap map) {
		this.map = map;
	}

}
