package se.chalmers.tda367.group15.game.database;

/**
 * A Score as it is represented in the database.
 * 
 * @author Peter
 * 
 */
public class DatabaseScore extends Score {

	/**
	 * A string representing the date this score was saved to the database.
	 */
	private final String time;

	/**
	 * Creates a new DatabaseScore with a set time. The name can not be null
	 * 
	 * @param name
	 * @param score
	 * @param time
	 */
	DatabaseScore(String name, int score, String time) {
		super(name, score);
		this.time = time;
	}

	public String getTime() {
		return time;
	}

}
