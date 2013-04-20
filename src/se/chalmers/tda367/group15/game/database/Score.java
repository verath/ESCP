package se.chalmers.tda367.group15.game.database;

/**
 * A Score that can be inserted into the database.
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
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Score))
			return false;
		Score other = (Score) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score != other.score)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Score [name=" + name + ", score=" + score + "]";
	}


}
