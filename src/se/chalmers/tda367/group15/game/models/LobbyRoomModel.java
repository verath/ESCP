package se.chalmers.tda367.group15.game.models;

import java.util.ArrayList;
import java.util.List;


/**
 * A lobby room.
 * 
 * @author simon
 * 
 */
public class LobbyRoomModel implements RoomModel {

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

	public LobbyRoomModel() {
		npcModels = new ArrayList<AbstractMovingModel>();

		CoworkerModel e1 = new CoworkerModel(9 * 32, 12 * 32);
		CoworkerModel e2 = new CoworkerModel(12 * 32, 7 * 32);
		CoworkerModel e3 = new CoworkerModel(25 * 32, 16 * 32);

		npcModels.add(e1);
		npcModels.add(e2);
		npcModels.add(e3);

		lockedMapPath = "res/levels/lobby_all_locked.tmx";
		unlockedMapPath = "res/levels/lobby_boss_locked.tmx";
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
