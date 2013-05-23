package se.chalmers.tda367.group15.game.models.rooms;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.CoworkerModel;

/**
 * An office with toilets.
 * 
 * @author simon
 * 
 */
public class WCRoomModel implements RoomModel {

	/**
	 * Variable for storing path to map with unlocked doors.
	 */
	private final String unlockedMapPath;

	/**
	 * Variable for storing path to map with locked doors.
	 */
	private final String lockedMapPath;

	/**
	 * Variable for storing all npc models
	 */
	private final List<AbstractMovingModel> npcModels;

	/**
	 * Create a wc room model.
	 */
	public WCRoomModel() {
		npcModels = new ArrayList<AbstractMovingModel>();

		CoworkerModel e1 = new CoworkerModel(800, 500, 0, 10, 0, 10);
		CoworkerModel e2 = new CoworkerModel(400, 200, 12, 32, 0, 9);
		CoworkerModel e3 = new CoworkerModel(450, 600, 0, 18, 16, 24);
		CoworkerModel e4 = new CoworkerModel(940, 600, 18, 32, 10, 24);
		CoworkerModel e5 = new CoworkerModel(300, 400);

		npcModels.add(e1);
		npcModels.add(e2);
		npcModels.add(e3);
		npcModels.add(e4);
		npcModels.add(e5);

		unlockedMapPath = "res/levels/right_room.tmx";
		lockedMapPath = "res/levels/right_room_locked.tmx";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AbstractMovingModel> getNpcModels() {
		return npcModels;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUnlockedMapPath() {
		return unlockedMapPath;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLockedMapPath() {
		return lockedMapPath;
	}
}
