package se.chalmers.tda367.group15.game.models.rooms;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.CoworkerModel;

/**
 * An office room.
 * 
 * @author simon
 * 
 */
public class LeftWingRoomModel implements RoomModel {

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

	public LeftWingRoomModel() {
		npcModels = new ArrayList<AbstractMovingModel>();

		CoworkerModel e1 = new CoworkerModel(3 * 32, 6 * 32);
		CoworkerModel e2 = new CoworkerModel(3 * 32, 10 * 32);
		CoworkerModel e3 = new CoworkerModel(3 * 32, 14 * 32);
		CoworkerModel e4 = new CoworkerModel(3 * 32, 18 * 32);

		CoworkerModel e5 = new CoworkerModel(8 * 32, 6 * 32);
		CoworkerModel e6 = new CoworkerModel(8 * 32, 10 * 32);
		CoworkerModel e7 = new CoworkerModel(8 * 32, 14 * 32);
		CoworkerModel e8 = new CoworkerModel(8 * 32, 18 * 32);

		CoworkerModel e9 = new CoworkerModel(13 * 32, 6 * 32);
		CoworkerModel e10 = new CoworkerModel(13 * 32, 10 * 32);
		CoworkerModel e11 = new CoworkerModel(13 * 32, 14 * 32);
		CoworkerModel e12 = new CoworkerModel(13 * 32, 18 * 32);

		CoworkerModel e13 = new CoworkerModel(18 * 32, 6 * 32);
		CoworkerModel e14 = new CoworkerModel(18 * 32, 10 * 32);
		CoworkerModel e15 = new CoworkerModel(18 * 32, 14 * 32);
		CoworkerModel e16 = new CoworkerModel(18 * 32, 18 * 32);

		npcModels.add(e1);
		npcModels.add(e2);
		npcModels.add(e3);
		npcModels.add(e4);
		npcModels.add(e5);
		npcModels.add(e6);
		npcModels.add(e7);
		npcModels.add(e8);
		npcModels.add(e9);
		npcModels.add(e10);
		npcModels.add(e11);
		npcModels.add(e12);
		npcModels.add(e13);
		npcModels.add(e14);
		npcModels.add(e15);
		npcModels.add(e16);

		unlockedMapPath = "res/levels/left_room.tmx";
		lockedMapPath = "res/levels/left_room_locked.tmx";
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
