package se.chalmers.tda367.group15.game.models;

/**
 * Model for keeping track of the score in the game.
 * 
 * @author Peter
 * 
 */
public class ScoreModel {
	/**
	 * The current score.
	 */
	private int score;

	/**
	 * Create a new Score model with the provided starting score.
	 * 
	 * @param score
	 */
	public ScoreModel(int score) {
		setScore(score);
	}

	/**
	 * Sets the score to the value.
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Returns the current score.
	 * 
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Decreases the score by one.
	 */
	public void decreaseScore() {
		score--;
	}

	/**
	 * Decreases the score by amount.
	 * 
	 * @param amount
	 */
	public void decreaseScore(int amount) {
		score -= amount;
	}

	/**
	 * Increases the score by one.
	 */
	public void increaseScore() {
		score++;
	}

	/**
	 * Increases the score by amount.
	 * 
	 * @param amount
	 */
	public void increaseScore(int amount) {
		score += amount;
	}
}
