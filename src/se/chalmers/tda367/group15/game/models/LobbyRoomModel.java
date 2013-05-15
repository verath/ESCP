package se.chalmers.tda367.group15.game.models;

import java.util.ArrayList;
import java.util.List;

public class LobbyRoomModel implements RoomModel {
	private String mapPath;
	private List<AbstractMovingModel> movingModels;
	
	public LobbyRoomModel() {
		movingModels = new ArrayList<AbstractMovingModel>();
		
		CoworkerModel e1 = new CoworkerModel(200, 200);
		CoworkerModel e2 = new CoworkerModel(650, 364);
		CoworkerModel e3 = new CoworkerModel(564, 300);
		CoworkerModel e4 = new CoworkerModel(500, 460);
		CoworkerModel e5 = new CoworkerModel(500, 200);
		
		movingModels.add(e1);
		movingModels.add(e2);
		movingModels.add(e3);
		movingModels.add(e4);
		movingModels.add(e5);
		
		mapPath = "res/levels/lobby.tmx";
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
