package se.chalmers.tda367.group15.game.database;

/**
 * A Score that can be inserted into the database.
 *
 * @author Peter
 */
public class InsertableScore {
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
     * @param name  The name of the player that scored the score.
     * @param score The score the player scored.
     */
    public InsertableScore(String name, int score) {
        if (name == null) {
            throw new IllegalArgumentException("name may not be null");
        }

        this.name = name;
        this.score = score;
    }

    /**
     * Creates a Score from another Score object.
     *
     * @param score The InsertableScore to clone.
     */
    public InsertableScore(InsertableScore score) {
        this(score.name, score.score);
    }

    /**
     * Gets the name of the player associated with this Score object.
     *
     * @return Name of the player that scored this score.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the score associated with this Score object.
     *
     * @return Score that was scored.
     */
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
        if (getClass() != obj.getClass())
            return false;
        InsertableScore other = (InsertableScore) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return score == other.score;
    }

    @Override
    public String toString() {
        return "Score [name=" + name + ", score=" + score + "]";
    }

}
