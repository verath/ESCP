package se.chalmers.tda367.group15.game.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * A database for holding statistics about the game, such as (high)scores.
 * 
 * @author Peter
 * 
 */
public class PsychoHeroDatabase {
	/**
	 * The database file used for the database
	 */
	public static final String DB_FILE_NAME = "PsychoHero.db";

	/**
	 * Creates a new PsychoHeroDatabase and sets up the database structure if
	 * needed.
	 * 
	 * @throws ClassNotFoundException
	 */
	public PsychoHeroDatabase() throws ClassNotFoundException {

		// Load the database class
		Class.forName("org.sqlite.JDBC");

		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE_NAME);

			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(30);

			// Set up scores table
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS scores ("
					+ "id integer PRIMARY KEY AUTOINCREMENT, "
					+ "score integer NOT NULL, " + "name string NOT NULL,"
					+ "time_added datetime NOT NULL)");

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	/**
	 * Adds a Score object to the database. If adding more than one Score
	 * object, use the addScores method instead.
	 * 
	 * @param score
	 *            The score object to add to the database.
	 */
	public void addScore(Score score) {
		List<Score> s = new ArrayList<Score>(1);
		s.add(score);
		addScores(s);
	}

	/**
	 * Adds a list of scores to the database
	 * 
	 * @param scores
	 */
	public void addScores(List<Score> scores) {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE_NAME);

			PreparedStatement stmt = con.prepareStatement("INSERT INTO scores "
					+ "(score, name, time_added) VALUES "
					+ "(?, ?, datetime('now'))");
			stmt.setQueryTimeout(30);

			// Add all queries as a batch and execute all at once instead of
			// executing them one at a time
			for (Score s : scores) {
				stmt.setInt(1, s.getScore());
				stmt.setString(2, s.getName());
				stmt.addBatch();
			}

			stmt.executeBatch();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	/**
	 * Returns a limit number of scores sorted by highest to lowest.
	 * 
	 * @param limit
	 *            The max number of results, if set to less than 0 all results
	 *            will be returned
	 * @return A list of max length Score objects, or null if no results could
	 *         be found.
	 */
	public List<Score> getHighscores(int limit) {
		List<Score> results = null;

		if (limit == 0) {
			return results;
		} else if (limit > 0) {
			results = new ArrayList<Score>(limit);
		} else {
			results = new ArrayList<Score>();
		}

		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:sqlite:" + DB_FILE_NAME);

			PreparedStatement stmt;
			if (limit > 0) {
				stmt = con.prepareStatement("SELECT * FROM scores LIMIT ? "
						+ "ORDER BY score DESC");
				stmt.setInt(1, limit);
			} else {
				stmt = con.prepareStatement("SELECT * FROM scores "
						+ "ORDER BY score DESC");
			}

			stmt.setQueryTimeout(30);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				results.add(new Score(rs.getString("name"), rs.getInt("score"),
						rs.getString("time_added")));
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
			}
		}

		return results;

	}
}
