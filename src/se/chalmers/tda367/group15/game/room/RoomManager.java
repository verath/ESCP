package se.chalmers.tda367.group15.game.room;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 * A class for abstractly handling rooms.
 * 
 * @author Peter
 * 
 */
public class RoomManager implements Iterable<Room> {

	public enum RelativePosition {
		ABOVE, BELOW, LEFTOF, RIGHTOF;
	}

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
	}

	/**
	 * Attempts to find the position of a room.
	 * 
	 * @param room
	 * @return The position of the room
	 */
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
	 * Method for adding a room at a position relative to a reference room.
	 * 
	 * @param reference
	 * @param room
	 * @param relativePosition
	 */
	public void addRoom(final Room reference, final Room room,
			RelativePosition relativePosition) {
		Point addPosition = getRoomPosition(reference);
		switch (relativePosition) {
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

	@Override
	public Iterator<Room> iterator() {
		return rooms.values().iterator();
	}

}
