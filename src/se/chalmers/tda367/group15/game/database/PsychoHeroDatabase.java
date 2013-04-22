package se.chalmers.tda367.group15.game.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

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
	 * A flag indicating whether we are using an in-memory database or not. This
	 * is currently only used for testing
	 */
	private final boolean ramDatabase;

	/**
	 * The connection to the database.
	 */
	private Connection connection;

	/**
	 * Creates a new PsychoHeroDatabase and sets up the database structure if
	 * needed.
	 * 
	 * @param tempDatabase
	 *            If set to true, the database used by this instance will be
	 *            stored in the ram (and will therefore not be persistent)
	 * @throws ClassNotFoundException
	 */
	public PsychoHeroDatabase(boolean tempDatabase)
			throws ClassNotFoundException {

		this.ramDatabase = tempDatabase;

		// Load the database class
		Class.forName("org.sqlite.JDBC");

		// Setup the database
		setupDatabase();
	}

	/**
	 * Creates a Connection to the database if there is no current one.
	 * 
	 * @return
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			if (ramDatabase) {
				connection = DriverManager
						.getConnection("jdbc:sqlite::memory:");
			} else {
				connection = DriverManager.getConnection("jdbc:sqlite:"
						+ DB_FILE_NAME);
			}
		}
		return connection;
	}

	/**
	 * Closes a connection to the database. If the database is ram-based, this
	 * method does nothing.
	 * 
	 * @param conn
	 */
	private void closeConnection(Connection conn) {
		if (!ramDatabase) {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * Creates and set up the database if it doesn't alredy exist
	 */
	private void setupDatabase() {

		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.setQueryTimeout(30);

			// Set up scores table
			stmt.executeUpdate("CREATE TABLE IF NOT EXISTS scores ("
					+ "id integer PRIMARY KEY AUTOINCREMENT, "
					+ "score integer NOT NULL, " + "name string NOT NULL,"
					+ "time_added datetime NOT NULL)");

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DbUtils.closeQuietly(stmt);
			closeConnection(conn);
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
		addScore(s);
	}

	/**
	 * Adds a list of scores to the database
	 * 
	 * @param scores
	 */
	public void addScore(List<Score> scores) {

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("INSERT INTO scores "
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
			DbUtils.closeQuietly(stmt);
			closeConnection(conn);
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
	public List<DatabaseScore> getHighscores(int limit) {

		List<DatabaseScore> results = null;

		// If we have a set limit, we know we will not get more than that amount
		// of results
		if (limit > 0) {
			results = new ArrayList<DatabaseScore>(limit);
		} else {
			results = new ArrayList<DatabaseScore>();
		}

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();

			if (limit >= 0) {
				stmt = conn.prepareStatement("SELECT * FROM scores "
						+ "ORDER BY score DESC LIMIT ?");
				stmt.setInt(1, limit);
			} else {
				// If we don't have a limit, return all results
				stmt = conn.prepareStatement("SELECT * FROM scores "
						+ "ORDER BY score DESC");
			}

			stmt.setQueryTimeout(30);

			// Run query and get results
			rs = stmt.executeQuery();
			while (rs.next()) {
				results.add(new DatabaseScore(rs.getString("name"), rs
						.getInt("score"), rs.getString("time_added"), rs
						.getInt("id")));
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getSQLState());
		} finally {
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(rs);
			closeConnection(conn);
		}

		return results;
	}

	/**
	 * Returns all scores, sorted by highest to lowest.
	 * 
	 * @return A list of max length Score objects, or null if no results could
	 *         be found.
	 */
	public List<DatabaseScore> getHighscores() {
		return getHighscores(-1);
	}
}
