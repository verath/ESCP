package se.chalmers.tda367.group15.game.database;

/**
 * A Score as it is represented in the database.
 * 
 * @author Peter
 */
public class DatabaseScore extends InsertableScore {

	/**
	 * A string representing the date this score was saved to the database.
	 */
	private final String time;

	/**
	 * The database id for this object.
	 */
	private final int id;

	/**
	 * Creates a new DatabaseScore with a set time and id from the database. The
	 * name can not be null. This constructor should only be called by the class
	 * fetching information from the database.
	 * 
	 * @param name
	 *            The name of the user scoring the score.
	 * @param score
	 *            The score the user scored
	 * @param time
	 *            The time when this score was inserted.
	 */
	DatabaseScore(String name, int score, String time, int id) {
		super(name, score);
		this.time = time;
		this.id = id;
	}

	/**
	 * Getter for the time this object was inserted into the database.
	 * 
	 * @return The time when the object was inserted into the database.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Getter for the unique id associated with this object in the database.
	 * 
	 * @return The unique id.
	 */
	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatabaseScore other = (DatabaseScore) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "DatabaseScore [name=" + getName() + ", score=" + getScore()
				+ ", time=" + time + ", id=" + id + "]";
	}

}
