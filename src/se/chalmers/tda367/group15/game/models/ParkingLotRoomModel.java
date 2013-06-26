package se.chalmers.tda367.group15.game.models;

import java.util.ArrayList;
import java.util.List;

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
		
		HealthPickupModel hp1 = new HealthPickupModel(26 * 32, 3 * 32);

		npcModels.add(e1);
		npcModels.add(e2);
		
		npcModels.add(hp1);

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
