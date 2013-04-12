package se.chalmers.tda367.group15.game.models.room;

import java.awt.Point;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;

import se.chalmers.tda367.group15.game.models.Room;

/**
 * A class for abstractly handling rooms.
 * 
 * @author Peter
 * 
 */
public class RoomManager {
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
	 * RoomManager constructor
	 */
	public RoomManager() {
		rooms = new HashMap<Point, Room>();
	}

	/**
	 * Checks whether a room is attached to this RoomManager
	 * 
	 * @return True if the room is attached, else False.
	 */
	private boolean roomExist(final Room room) {
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
	
	/**
	 * Adds a room above another room in the game.
	 * 
	 * @param reference
	 * @param room
	 */
	public void addRoomAbove(final Room reference, final Room room) {
		Point addPosition = getRoomPosition(reference);
		addPosition.translate(0, 1);
		rooms.put(addPosition, room);
	}
	
	/**
	 * Adds a room below another room in the game.
	 * 
	 * @param reference
	 * @param room
	 */
	public void addRoomBelow(final Room reference, final Room room) {
		Point addPosition = getRoomPosition(reference);
		addPosition.translate(0, -1);
		rooms.put(addPosition, room);
	}
	
	/**
	 * Adds a room to the left of another room in the game.
	 * 
	 * @param reference
	 * @param room
	 */
	public void addRoomLeftOf(final Room reference, final Room room) {
		Point addPosition = getRoomPosition(reference);
		addPosition.translate(-1, 0);
		rooms.put(addPosition, room);
	}
	
	/**
	 * Adds a room to the right of another room in the game.
	 * 
	 * @param reference
	 * @param room
	 */
	public void addRoomRightOf(final Room reference, final Room room) {
		Point addPosition = getRoomPosition(reference);
		addPosition.translate(1, 0);
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
	}
	
	/**
	 * Moves the room selector downwards
	 */
	public void moveDown() {
		currentPosition.translate(0, -1);
	}
	
	/**
	 * Moves the room selector to the left
	 */
	public void moveLeft() {
		currentPosition.translate(-1, 0);
	}
	
	/**
	 * Moves the room selector to the right
	 */
	public void moveRight() {
		currentPosition.translate(1, 0);
	}

}
