package se.chalmers.tda367.group15.game.models;

import java.util.ArrayList;
import java.util.List;

/**
 * A lobby room.
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

		SpecialModel e1 = new SpecialModel(9 * 32, 12 * 32);
		SpecialModel e2 = new SpecialModel(12 * 32, 7 * 32);
		SpecialModel e3 = new SpecialModel(25 * 32, 16 * 32);

		npcModels.add(e1);
		npcModels.add(e2);
		npcModels.add(e3);

		unlockedMapPath = "res/levels/top_room.tmx";
		lockedMapPath = "res/levels/top_room_locked.tmx";
	}

	@Override
	public List<AbstractMovingModel> getNpcModels() {
		return npcModels;
	}

	@Override
	public String getUnlockedMapPath() {
		return unlockedMapPath;
	}

	@Override
	public String getLockedMapPath() {
		return lockedMapPath;
	}
}
