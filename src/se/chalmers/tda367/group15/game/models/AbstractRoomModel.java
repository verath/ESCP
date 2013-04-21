package se.chalmers.tda367.group15.game.models;

import java.util.List;


/**
 * Abstract class for representing a room model.
 * 
 * @author simon
 * 
 */
public abstract class AbstractRoomModel {

	/**
	 * Method for getting the moving models (normally the enemies) of a room.
	 * @return a list of moving models
	 */
	public abstract List<MovingModel> getMovingModels();
}
