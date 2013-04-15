package se.chalmers.tda367.group15.game.views.room;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.tda367.group15.game.models.Room;

/**
 * A class for abstractly handling rooms.
 * 
 * @author Peter
 * 
 */
public class RoomManager {
	
	public enum RelativePos {
		ABOVE, BELOW, LEFTOF, RIGHTOF;
	}
	
	/**
	 * The instance object of this class.
	 */
	public static RoomManager instance;
	/**
	 * The starting point of the first room.
	 */
	public static final Point STARTING_POINT = new Point(0, 0);

	/**
	 * A 2-dimensional array of rooms, describing the layout of the rooms in the
	 * game.
	 */
	private Map<Point, Room> rooms;

	/**
	 * The current "position", used as key to query for the room from the map.
	 */
	private Point currentPosition = STARTING_POINT;
	
	/**
	 * A list of the bounds of all blocked tiles in the current map.
	 */
	private List<Rectangle2D.Float> collisionBounds = new ArrayList<Rectangle2D.Float>();

	/**
	 * RoomManager constructor
	 */
	private RoomManager() {
		rooms = new HashMap<Point, Room>();
	}
	
	public static RoomManager getInstance() {
		if(instance == null) {
			instance = new RoomManager();
		}
		return instance;
	}

	/**
	 * Checks whether a room is attached to this RoomManager
	 * 
	 * @return True if the room is attached, else False.
	 */
	public boolean roomExist(final Room room) {
		// If room is null, we can discard it at once
		if (room == null) {
			return false;
		}

		return rooms.containsValue(room);
	}

	/**
	 * Adds a room to the starting position and sets it as the currently active
	 * room.
	 * 
	 * @param room
	 *            The room that should be set as the starting room. Must not be
	 *            null.
	 */
	public void addStartingRoom(final Room room) {
		if (rooms.containsKey(STARTING_POINT)) {
			throw new RoomAlreadyExistAtPositionException();
		} else {
			rooms.put(STARTING_POINT, room);
		}
		
		generateCollisionBounds();
	}

	private Point getRoomPosition(Room room) {
		Point position = null;

		for (Entry<Point, Room> entry : rooms.entrySet()) {
			if (entry.getValue().equals(room)) {
				position = new Point(entry.getKey());
			}
		}

		if (position == null) {
			throw new RoomDoesNotExistException(
					"The reference room did not exist.");
		} else { 
			return position;
		}
	}
	
	public void addRoom(final Room reference, final Room room, RelativePos relPos ) {
		Point addPosition = getRoomPosition(reference);
		switch(relPos) {
		case ABOVE:
			addPosition.translate(0, 1);
			break;
		case BELOW:
			addPosition.translate(0, 1);
			break;
		case LEFTOF:
			addPosition.translate(-1, 0);
			break;
		case RIGHTOF:
			addPosition.translate(1, 0);
			break;
		}
		rooms.put(addPosition, room);

		
	}

	/**
	 * Returns the currently "selected" room
	 */
	public Room getCurrentRoom() {
		return rooms.get(currentPosition);
	}

	/**
	 * Moves the room selector upwards
	 */
	public void moveUp() {
		currentPosition.translate(0, 1);
		generateCollisionBounds();
	}
	
	/**
	 * Moves the room selector downwards
	 */
	public void moveDown() {
		currentPosition.translate(0, -1);
		generateCollisionBounds();
	}
	
	/**
	 * Moves the room selector to the left
	 */
	public void moveLeft() {
		currentPosition.translate(-1, 0);
		generateCollisionBounds();
	}
	
	/**
	 * Moves the room selector to the right
	 */
	public void moveRight() {
		currentPosition.translate(1, 0);
		generateCollisionBounds();
	}
	
	public List<Rectangle2D.Float> getCollisionBounds() {
		return collisionBounds;
	}
	
	private void generateCollisionBounds() {
		TiledMap map = null;
		try {
			map = new TiledMap (this.getCurrentRoom().getTiledMapPath());
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < map.getWidth(); i++) {
			for(int j = 0; j < map.getHeight(); j++) {
				int tileID = map.getTileId(i, j, 0);
				String property = map.getTileProperty(tileID, "blocked", "false");
				if(property.equals("true")) {
					collisionBounds.add(new Rectangle2D.Float(i * 32, j * 32, 32, 32));
				}
			}
		}
	}

}
