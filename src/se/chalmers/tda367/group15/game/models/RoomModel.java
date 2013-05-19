package se.chalmers.tda367.group15.game.models;

import java.util.List;

/**
 * Interface for representing a room model.
 * 
 * @author simon
 * 
 */
public interface RoomModel {
	/**
	 * Method for getting all the room's npc models.
	 * 
	 * @return A list of npc models.
	 */
	public List<AbstractMovingModel> getNpcModels();

	/**
	 * Get the path to the tmx file to be used for creating an unlocked Tiled
	 * Map.
	 * 
	 * @return The path to a tmx file.
	 */
	public String getUnlockedMapPath();

	/**
	 * Get the path to the tmx file to be used for creating an locked Tiled Map.
	 * 
	 * @return The path to a tmx file.
	 */
	public String getLockedMapPath();

}
