package se.chalmers.tda367.group15.game.models.rooms;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.CoworkerModel;

/**
 * A parking lot room.
 * 
 * @author simon
 * 
 */
public class ParkingLotRoomModel implements RoomModel {

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

	public ParkingLotRoomModel() {
		npcModels = new ArrayList<AbstractMovingModel>();

		CoworkerModel e1 = new CoworkerModel(2 * 32, 5 * 32);
		CoworkerModel e2 = new CoworkerModel(29 * 32, 11 * 32);

		npcModels.add(e1);
		npcModels.add(e2);

		unlockedMapPath = "res/levels/parking_lot.tmx";
		lockedMapPath = "res/levels/parking_lot_locked.tmx";
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
