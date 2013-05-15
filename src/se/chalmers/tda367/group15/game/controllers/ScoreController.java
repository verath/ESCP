package se.chalmers.tda367.group15.game.controllers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.newdawn.slick.GameContainer;

import se.chalmers.tda367.group15.game.database.InsertableScore;
import se.chalmers.tda367.group15.game.database.PsychoHeroDatabase;
import se.chalmers.tda367.group15.game.models.ScoreModel;
import se.chalmers.tda367.group15.game.settings.Constants;

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
	public final static int SCORE_DECREASE_INTERVAL = 1000;

	/**
	 * A list of names used if no name was provided when saving to the database.
	 */
	private final static List<String> DEFAULT_HIGH_SCORE_NAMES = Arrays.asList(
			"TheMaster", "XxMEGAMANxX", "1337Skillz", "YOUR MOM", "H4xx0rMega",
			"MoreMega", "SuperDragon", "TinyGiant", "GiganticDwarf", "Red Car",
			"ILikeYou", "Random", "LoL", "AnotherOne", "NoNamez");

	/**
	 * The ScoreModel, holding the current score in the game. decrease interval
	 */
	private final ScoreModel scoreModel;

	/**
	 * Time since last update of the score (milliseconds).
	 */
	private int lastChange;

	/**
	 * The database instance to use when saving score.
	 */
	private PsychoHeroDatabase db;

	/**
	 * The game controller for the game
	 */
	private final GameController gameController;

	/**
	 * Creates a new ScoreController, using the provided ScoreModel.
	 * 
	 * @param scoreModel
	 */
	public ScoreController(GameController gameController, ScoreModel scoreModel) {
		this.gameController = gameController;
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

		if (scoreModel.getScore() <= 0) {
			gameController.gameOver(false);
		}
	}

	/**
	 * Saves the current score to the database. This should probably only be
	 * called when the player has defeated the game.
	 * 
	 * If a name is provided it will be used, otherwise a default name will be
	 * used.
	 * 
	 * @param name
	 */
	public void saveScore(String name) {
		if (name == null) {
			Collections.shuffle(DEFAULT_HIGH_SCORE_NAMES);
			name = DEFAULT_HIGH_SCORE_NAMES.get(0);
		}

		if (db == null) {
			try {
				db = new PsychoHeroDatabase();
			} catch (ClassNotFoundException e) {
				System.err
						.println("Could not connect to the database. Make sure you have the org.sqlite.JDBC library.");
				return;
			}
		}

		InsertableScore s = new InsertableScore(name, scoreModel.getScore());
		db.addScore(s);

		if (Constants.DEBUG) {
			System.out.println("Succesfully inserted " + s
					+ " into the database!");
		}
	}

	/**
	 * Sets the database used. Should most likely only be used for testing.
	 * 
	 * @param db
	 */
	protected void setDatabase(PsychoHeroDatabase db) {
		this.db = db;
	}
}
