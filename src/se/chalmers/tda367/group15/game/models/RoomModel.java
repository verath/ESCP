package se.chalmers.tda367.group15.game.models;

import java.util.List;

public interface RoomModel {
	public List<AbstractMovingModel> getNpcModels();
	public String getMapPath();
}
