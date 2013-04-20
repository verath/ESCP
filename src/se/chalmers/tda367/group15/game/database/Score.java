package se.chalmers.tda367.group15.game.database;

/**
 * A class representing a score for a name.
 * 
 * @author Peter
 * 
 */
public class Score {
	/**
	 * The name of the player
	 */
	private final String name;

	/**
	 * The score
	 */
	private final int score;

	/**
	 * A string representing the date this score was saved to the database.
	 */
	private final String time;

	/**
	 * Creates a new score. The name can not be null
	 * 
	 * @param name
	 * @param score
	 */
	public Score(String name, int score) {
		if (name == null) {
			throw new IllegalArgumentException("name may not be null");
		}

		this.name = name;
		this.score = score;
		this.time = null;
	}

	/**
	 * Creates a new Score with a set time. The name can not be null
	 * 
	 * @param name
	 * @param score
	 * @param time
	 */
	public Score(String name, int score, String time) {
		if (name == null) {
			throw new IllegalArgumentException("name may not be null");
		}

		this.name = name;
		this.score = score;
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public String getTime() {
		return time;
	}
}
