package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Abstract class for representing a room. This class holds all of the common
 * functionality that each room should have.
 * 
 * @author simon
 * 
 */
public abstract class Room {

	/**
	 * A list of rectangles representing the collision bounds of all blocked
	 * tiles in a tiled map.
	 */
	private List<Rectangle2D.Float> collisionBounds = new ArrayList<Rectangle2D.Float>();
	
	/**
	 * The tiled map used by this room.
	 */
	private TiledMap map;

	public Room(String pathToMap) {
		try {
			map = new TiledMap(pathToMap);
			generateCollisionBounds();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method for getting the tiled map of the room
	 * 
	 * @return a TiledMap object
	 */
	public TiledMap getTiledMap() {
		return map;
	}

	// Private method for generating rectangles representing the bounds of
	// blocked tiles on the map.
	private void generateCollisionBounds() {
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				int tileID = map.getTileId(i, j, 0);
				String property = map.getTileProperty(tileID, "blocked",
						"false");
				if (property.equals("true")) {
					collisionBounds.add(new Rectangle2D.Float(i * 32, j * 32,
							32, 32));
				}
			}
		}
	}

	/**
	 * Method for getting the rectangles representing the map's collisionbounds
	 * 
	 * @return collision bounds
	 */
	public List<Rectangle2D.Float> getCollisionBounds() {
		return collisionBounds;
	}
}
