package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.tiled.TiledMap;

/**
 * Abstract class for representing a room. This class holds all of the common
 * functionality that each room should have.
 * 
 * @author simon
 * 
 */
public abstract class AbstractRoomModel implements CollidableModel,
		CollidingModel {

	/**
	 * A list of rectangles representing the collision bounds of all blocked
	 * tiles in a tiled map.
	 */
	private List<Rectangle2D.Float> collisionBounds = new LinkedList<Rectangle2D.Float>();

	/**
	 * Generates rectangles representing the bounds of blocked tiles on the map.
	 * Should only be called once for each map, and must not be called during
	 * the game loop as this operation is expensive.
	 */
	public void generateCollisionBounds(final TiledMap map) {
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				int tileId = map.getTileId(i, j, 0);
				String property = map.getTileProperty(tileId, "blocked",
						"false");
				if (property.equals("true")) {
					collisionBounds.add(new Rectangle2D.Float(i * 32, j * 32,
							32, 32));
				}
			}
		}
	}

	@Override
	public List<Rectangle2D.Float> getCollisionBounds() {
		return collisionBounds;
	}
}
