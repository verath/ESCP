package se.chalmers.tda367.group15.game.views;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

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

	private List<Rectangle2D.Float> collisionBounds = new ArrayList<Rectangle2D.Float>();

	public AbstractRoomView(String mapPath) {
		try {
			map = new TiledMap(mapPath);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		generateCollisionBounds();
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

	/**
	 * Method for getting the collision bounds of the blocked object in the
	 * tiled map.
	 * 
	 * @return A list of rectangles representing collision bounds.
	 */
	public List<Rectangle2D.Float> getCollisionBounds() {
		return collisionBounds;
	}

	private void generateCollisionBounds() {
		for (int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				int tileId = map.getTileId(x, y, 1);
				String property = map.getTileProperty(tileId, "blocked",
						"false");
				if (property.equals("true")) {
					// assuming that the map is created with 32x32 tiles
					collisionBounds.add(new Rectangle2D.Float(x * 32, y * 32,
							32, 32));
				}
			}
		}
	}

}
