package se.chalmers.tda367.group15.game.models;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotRoomModel implements RoomModel {
	private String mapPath;
	private List<AbstractMovingModel> movingModels;
	
	public ParkingLotRoomModel() {
		movingModels = new ArrayList<AbstractMovingModel>();
		
		CoworkerModel e1 = new CoworkerModel(800, 500);
		CoworkerModel e2 = new CoworkerModel(400, 200);
		CoworkerModel e3 = new CoworkerModel(450, 600);
		CoworkerModel e4 = new CoworkerModel(940, 600);
		CoworkerModel e5 = new CoworkerModel(300, 400);
		
		movingModels.add(e1);
		movingModels.add(e2);
		movingModels.add(e3);
		movingModels.add(e4);
		movingModels.add(e5);
		
		mapPath = "res/levels/parking_lot.tmx";
	}

	@Override
	public List<AbstractMovingModel> getNpcModels() {
		return movingModels;
	}

	@Override
	public String getMapPath() {
		return mapPath;
	}
}
