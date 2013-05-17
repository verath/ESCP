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
	 * Variable for storing path to a map.
	 */
	private String mapPath;

	/**
	 * Variable for storing all npc models
	 */
	private List<AbstractMovingModel> npcModels;

	public ParkingLotRoomModel() {
		npcModels = new ArrayList<AbstractMovingModel>();

		CoworkerModel e1 = new CoworkerModel(2*32, 5*32);
		CoworkerModel e2 = new CoworkerModel(29*32, 11*32);

		npcModels.add(e1);
		npcModels.add(e2);

		mapPath = "res/levels/parking_lot.tmx";
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
