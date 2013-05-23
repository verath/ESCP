package se.chalmers.tda367.group15.game.controllers;

import java.awt.Point;
import java.awt.geom.Rectangle2D.Float;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.chalmers.tda367.group15.game.controllers.SoundController.GameMusic;
import se.chalmers.tda367.group15.game.controllers.SoundController.SoundEffect;
import se.chalmers.tda367.group15.game.exceptions.RoomAlreadyExistAtPositionException;
import se.chalmers.tda367.group15.game.exceptions.RoomDoesNotExistException;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.rooms.BossRoomModel;
import se.chalmers.tda367.group15.game.models.rooms.LobbyRoomModel;
import se.chalmers.tda367.group15.game.models.rooms.RoomModel;

/**
 * A class for abstractly handling rooms.
 * 
 * @author Peter, Simon
 */
public class RoomsController {

	private SoundController soundController;

	/**
	 * Enum for representing a relative position in the game map.
	 * 
	 * @author simon
	 */
	public enum RelativePosition {
		ABOVE, BELOW, LEFTOF, RIGHTOF
	}

	/**
	 * Variable used to check if boss room is unlocked.
	 */
	private boolean bossRoomUnlocked;

	/**
	 * The starting point of the first room.
	 */
	private static final Point STARTING_POINT = new Point(0, 0);

	/**
	 * A 2-dimensional array of rooms, describing the layout of the rooms in the
	 * game.
	 */
	private final Map<Point, RoomController> rooms;

	/**
	 * The current "position", used as key to query for the room from the map.
	 */
	private final Point currentPosition = STARTING_POINT;

	/**
	 * RoomManager constructor
	 */
	public RoomsController() {
		rooms = new HashMap<Point, RoomController>();
		soundController = soundController.instance();
	}

	/**
	 * Checks whether a room is attached to this RoomManager
	 * 
	 * @return True if the room is attached, else False.
	 */
	public boolean roomExist(final RoomController room) {
		// If room is null, we can discard it at once
		return room != null && rooms.containsValue(room);

	}

	/**
	 * Adds a room to the starting position and sets it as the currently active
	 * room.
	 * 
	 * @param room
	 *            The room that should be set as the starting room. Must not be
	 *            null.
	 */
	public void addStartingRoom(final RoomController room) {
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
	 *            The room to find.
	 * @return The position of the room
	 */
	private Point getRoomPosition(RoomController room) {
		Point position = null;

		for (Entry<Point, RoomController> entry : rooms.entrySet()) {
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
	 *            The reference room. This room must have already been added.
	 * @param room
	 *            The new room to add.
	 * @param relativePosition
	 *            The position relative to the reference room that the new room
	 *            should be added at.
	 */
	public void addRoom(final RoomController reference,
			final RoomController room, RelativePosition relativePosition) {
		Point addPosition = getRoomPosition(reference);
		switch (relativePosition) {
		case ABOVE:
			addPosition.translate(0, 1);
			break;
		case BELOW:
			addPosition.translate(0, -1);
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
	public RoomController getCurrentRoom() {
		return rooms.get(currentPosition);

	}

	/**
	 * Moves the room selector upwards
	 */
	public void moveUp() {
		removeBullets();
		currentPosition.translate(0, 1);
	}

	/**
	 * Moves the room selector downwards
	 */
	public void moveDown() {
		removeBullets();
		currentPosition.translate(0, -1);
	}

	/**
	 * Moves the room selector to the left
	 */
	public void moveLeft() {
		removeBullets();
		currentPosition.translate(-1, 0);
	}

	/**
	 * Moves the room selector to the right
	 */
	public void moveRight() {
		removeBullets();
		currentPosition.translate(1, 0);
	}

	/**
	 * Update the game logic here. No rendering should take place in this method
	 * though it won't do any harm.
	 * 
	 * @param container
	 *            The container holing this game
	 * @param delta
	 *            The amount of time that's passed since last update in
	 *            milliseconds
	 * @param dynamicBounds
	 *            A map of dynamic collision bounds.
	 * @param staticBounds
	 *            A list of static collision bounds.
	 */
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds) {
		getCurrentRoom().update(container, delta, staticBounds, dynamicBounds);

		RoomController currentRoom = getCurrentRoom();

		if (allEnemiesDefeated() && !bossRoomUnlocked) {
			for (RoomController controller : rooms.values()) {
				RoomModel model = controller.getRoomModel();
				if (model instanceof LobbyRoomModel) {
					controller.setMap("res/levels/lobby.tmx");
				}
			}
			bossRoomUnlocked = true;
			currentRoom.unlockRoom();
			soundController.playGameMusic(GameMusic.BOSS_MUSIC);
			soundController.playSound(SoundEffect.NARRATOR_BOSS);

		} else if (currentRoom.allDead() && !currentRoom.isUnlocked()
				&& !bossRoomUnlocked) {
			currentRoom.unlockRoom();
			SoundController soundController = SoundController
					.instance();
			soundController.playSound(SoundEffect.NARRATOR_NEXT);

		}
	}

	/**
	 * Method for telling the room view of the current room to render it's tiled
	 * map
	 * 
	 * @param container
	 *            The container holding this game
	 * @param g
	 *            The graphics context that can be used to render. However,
	 *            normal rendering routines can also be used.
	 */
	public void render(GameContainer container, Graphics g) {
		getCurrentRoom().render(container, g);
	}

	/**
	 * Method for sending init call to all the rooms.
	 * 
	 * @param container
	 *            The container holding this game.
	 */
	public void init(GameContainer container) {
		for (RoomController room : rooms.values()) {
			room.init(container);
		}
	}

	/**
	 * Method for checking if all enemies in all existing rooms is defeated
	 * (except for the boss room). This method is used to determine if to unlock
	 * door to the boss room.
	 * 
	 * @return true if all enemies are dead, false otherwise.
	 */
	boolean allEnemiesDefeated() {
		boolean allDefeated = true;
		for (RoomController controller : rooms.values()) {
			if (!(controller.getRoomModel() instanceof BossRoomModel)) {
				if (!controller.allDead()) {
					allDefeated = false;
				}
			}
		}
		return allDefeated;
	}

	void removeBullets() {
		RoomController currentRoom = getCurrentRoom();
		currentRoom.removeBullets();

	}

}
