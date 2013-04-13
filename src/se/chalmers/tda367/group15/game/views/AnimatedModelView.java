package se.chalmers.tda367.group15.game.views;

import se.chalmers.tda367.group15.game.models.AnimatedModel;

/**
 * Interface for representing a model's view.
 * @author simon
 *
 */
public interface AnimatedModelView {

	/**
	 * Method for getting the animated model object in a view.
	 * @return the model object
	 */
	public AnimatedModel getAnimatedModel();
}
