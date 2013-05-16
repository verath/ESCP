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
	 * Variable for storing path to a map.
	 */
	private String mapPath;

	/**
	 * Variable for storing all npc models
	 */
	private List<AbstractMovingModel> npcModels;

	public LobbyRoomModel() {
		npcModels = new ArrayList<AbstractMovingModel>();

		CoworkerModel e1 = new CoworkerModel(9 * 32, 12 * 32);
		CoworkerModel e2 = new CoworkerModel(12 * 32, 7 * 32);
		CoworkerModel e3 = new CoworkerModel(25 * 32, 16 * 32);

		npcModels.add(e1);
		npcModels.add(e2);
		npcModels.add(e3);

		mapPath = "res/levels/lobby.tmx";
	}

	@Override
	public List<AbstractMovingModel> getNpcModels() {
		return npcModels;
	}

	@Override
	public String getMapPath() {
		return mapPath;
	}
}
