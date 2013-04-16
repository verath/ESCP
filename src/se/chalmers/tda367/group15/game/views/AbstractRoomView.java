package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.tda367.group15.game.models.AbstractRoomModel;

abstract public class AbstractRoomView implements View {
	
	/**
	 * The tiled map used by this room.
	 */
	private TiledMap map = null;

	/**
	 * Method for getting the tiled map of the room
	 * 
	 * @return The TiledMap representing the room.
	 */
	public TiledMap getTiledMap() {
		return map;
	}

	/**
	 * Sets a TiledMap as the current TiledMap representing this room. This will
	 * also trigger a (re)calculation of the room's collision bounds.
	 * 
	 * @param map
	 *            A TiledMap representing the room. Must not be null
	 */
	protected void setTiledMap(final TiledMap map) {
		this.map = map;
	}

	public abstract void setRoomModel(AbstractRoomModel roomModel);
}
