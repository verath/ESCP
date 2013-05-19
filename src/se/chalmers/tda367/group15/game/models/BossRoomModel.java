package se.chalmers.tda367.group15.game.models;

import java.util.ArrayList;
import java.util.List;

/**
 * A boss room.
 * 
 * @author simon
 * 
 */
public class BossRoomModel implements RoomModel {

	/**
	 * Variable for storing path to map with unlocked doors.
	 */
	private String unlockedMapPath;

	/**
	 * Variable for storing path to map with locked doors.
	 */
	private String lockedMapPath;

	/**
	 * Variable for storing all npc models
	 */
	private List<AbstractMovingModel> npcModels;

	public BossRoomModel() {
		npcModels = new ArrayList<AbstractMovingModel>();

		SoldierModel e1 = new SoldierModel(9 * 32, 12 * 32, 0, 11, 7, 13);
		SoldierModel e2 = new SoldierModel(12 * 32, 7 * 32);
		SoldierModel e3 = new SoldierModel(25 * 32, 16 * 32, 18, 30, 7, 16);
		BossModel b1 = new BossModel(15*32, 1*32);
		npcModels.add(e1);
		npcModels.add(e2);
		npcModels.add(e3);
		npcModels.add(b1);

		unlockedMapPath = "res/levels/top_room.tmx";
		lockedMapPath = "res/levels/top_room_locked.tmx";
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
