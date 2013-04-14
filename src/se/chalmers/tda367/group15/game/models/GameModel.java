package se.chalmers.tda367.group15.game.models;

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
	List<Model> models;

	public GameModel() {
		models = new ArrayList<Model>();
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		for (Model m : models) {
			m.update(container, delta);
		}
	}
	
	public boolean addModel(Model m) {
		return models.add(m);
	}

	public boolean removeModel(Model m) {
		return models.remove(m);
	}
}
