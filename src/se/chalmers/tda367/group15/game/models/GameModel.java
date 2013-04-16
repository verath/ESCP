package se.chalmers.tda367.group15.game.models;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.room.RoomManager;

/**
 * A container class for all the models associated with the game.
 * 
 * @author Peter
 * 
 */
public class GameModel implements Model {

	/**
	 * List of models that should be forwarded the update.
	 */
	private List<Model> models = new ArrayList<Model>();

	/**
	 * Creates a new GameModel
	 */
	public GameModel() {
	}

	@Override
	public void update(GameContainer container, int delta,
			RoomManager roomManager)
			throws SlickException {
		for (Model m : models) {
			m.update(container, delta, roomManager);
		}
	}

	/**
	 * Adds a model to the GameModel. This will register the model for receiving
	 * update calls.
	 * 
	 * @param model
	 */
	public void addModel(Model model) {
		models.add(model);
	}

	/**
	 * Removes a model from the GameModel. This will unregister the model for
	 * receiving update calls.
	 * 
	 * @param model
	 */
	public void removeModel(Model model) {
		models.remove(model);
	}
}
