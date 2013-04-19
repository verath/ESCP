package se.chalmers.tda367.group15.game.controllers.room;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * A class for abstractly handling rooms.
 * 
 * @author Peter, Simon
 * 
 */
public class RoomController {

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
	public RoomController() {
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

	/**
	 * Update the game logic here. No rendering should take place in this method
	 * though it won't do any harm.
	 * 
	 * @param container
	 *            The container holing this game
	 * @param delta
	 *            The amount of time thats passed since last update in
	 *            milliseconds
	 * @throws SlickException
	 *             Throw to indicate an internal error
	 */
	public void update(GameContainer container, int delta) throws SlickException {
	}

	/**
	 * Render the game's screen here.
	 * 
	 * @param container
	 *            The container holing this game
	 * @param g
	 *            The graphics context that can be used to render. However,
	 *            normal rendering routines can also be used.
	 * @throws SlickException
	 *             Throw to indicate a internal error
	 */
	public void render(GameContainer container, Graphics g) throws SlickException {
		getCurrentRoom().getRoomView().render(container, g);
	}

	/**
	 * Initialize the game. This can be used to load static resources. It's
	 * called before the game loop starts
	 * 
	 * @param container
	 *            The container holding the game
	 * @throws SlickException
	 *             Throw to indicate an internal error
	 */
	public void init(GameContainer container) throws SlickException {

	}

}
