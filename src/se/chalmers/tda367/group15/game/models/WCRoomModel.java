package se.chalmers.tda367.group15.game.models;

import java.util.ArrayList;
import java.util.List;

/**
 * An office room.
 * 
 * @author simon
 * 
 */
public class WCRoomModel implements RoomModel {
	/**
	 * Variable for storing path to a map.
	 */
	private String mapPath;

	/**
	 * Variable for storing all npc models
	 */
	private List<AbstractMovingModel> npcModels;

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

		mapPath = "res/levels/right_room.tmx";
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
