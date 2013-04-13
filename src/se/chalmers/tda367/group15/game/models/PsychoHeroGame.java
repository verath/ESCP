package se.chalmers.tda367.group15.game.models;

import java.util.List;

import se.chalmers.tda367.group15.game.views.Renderable;

/**
 * Interface for a PsychoHeroGame. Used to allow for the possibility of
 * different implementations of the game.
 * 
 * @author Peter
 * 
 */
public interface PsychoHeroGame {

	/**
	 * Method for getting all the renderable object in the game.
	 * @return renderable objects
	 */
	public List<Renderable> getRenderables();

	/**
	 * Method for getting all the animated models in the game.
	 * @return animated models
	 */
	public List<AnimatedModel> getAnimatedModels();
}
