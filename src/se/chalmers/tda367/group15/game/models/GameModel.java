package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

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

	private List<CollidingModel> collidingModel = new ArrayList<CollidingModel>();

	private List<CollidableModel> collidableModels = new ArrayList<CollidableModel>();

	/**
	 * Creates a new GameModel
	 */
	public GameModel() {
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		for (Model m : models) {
			m.update(container, delta);
		}
		
		// Handle collisions
		List<Rectangle2D.Float> collisionBounds = new ArrayList<Rectangle2D.Float>(
				collidableModels.size());

		for (CollidableModel m : collidableModels) {
			collisionBounds.addAll(m.getCollisionBounds());
		}

		for (CollidingModel m : collidingModel) {
			m.collide(collisionBounds);
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
		if (model instanceof CollidingModel) {
			collidingModel.add((CollidingModel) model);
		}
		if (model instanceof CollidableModel) {
			collidableModels.add((CollidableModel) model);
		}
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
