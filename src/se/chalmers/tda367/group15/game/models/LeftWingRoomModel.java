package se.chalmers.tda367.group15.game.models;

import java.util.ArrayList;
import java.util.List;

/**
 * A lobby room.
 * 
 * @author simon
 * 
 */
public class LeftWingRoomModel implements RoomModel {

	/**
	 * Variable for storing path to a map.
	 */
	private String mapPath;

	/**
	 * Variable for storing all npc models
	 */
	private List<AbstractMovingModel> npcModels;

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

		mapPath = "res/levels/left_room.tmx";
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
