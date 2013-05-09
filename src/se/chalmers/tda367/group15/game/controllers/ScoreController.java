package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;

import se.chalmers.tda367.group15.game.models.ScoreModel;

/**
 * Class for handling the score in the game.
 * 
 * @author Peter
 * 
 */
public class ScoreController {

	/**
	 * The amount of milliseconds between each decrease in score.
	 */
	private final static int SCORE_DECREASE_INTERVAL = 1000;

	/**
	 * The ScoreModel, holding the current score in the game. decrease interval
	 */
	private final ScoreModel scoreModel;

	/**
	 * Time since last update of the score (milliseconds).
	 */
	private int lastChange;

	/**
	 * Creates a new ScoreController, using the provided ScoreModel.
	 * 
	 * @param scoreModel
	 */
	public ScoreController(ScoreModel scoreModel) {
		this.scoreModel = scoreModel;
	}

	/**
	 * Update the game logic here. No rendering should take place in this method
	 * though it won't do any harm.
	 * 
	 * @param container
	 *            The container holing this game
	 * @param delta
	 *            The amount of time thats passed since last update in
	 *            milliseconds
	 */
	public void update(GameContainer container, int delta) {
		lastChange += delta;

		if (lastChange > SCORE_DECREASE_INTERVAL) {
			lastChange = 0;
			scoreModel.decreaseScore();
		}
	}
}
