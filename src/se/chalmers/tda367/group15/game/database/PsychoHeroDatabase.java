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

	/*
	 * Score table constants
	 */
	private static final String TABLE_SCORES = "scores";

	private static final String SCORES_COLUMN_NAME = "name";

	private static final String SCORES_COLUMN_TIME_ADDED = "time_added";

	private static final String SCORES_COLUMN_SCORE = "score";

	private static final String SCORES_COLUMN_ID = "id";

	/*
	 * Event table constants
	 */
	private static final String TABLE_EVENTS = "events";

	private static final String EVENTS_COLUMN_TYPE = "type";

	private static final String EVENTS_COLUMN_TIME_ADDED = "time_added";

	private static final String EVENTS_COLUMN_ID = "id";

	/**
	 * The database file used for the database
	 */
	private static final String DB_FILE_NAME = "PsychoHero.db";

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
	 * Creates a new PsychoHeroDatabase and sets up the database structure if
	 * needed.
	 * 
	 * @throws ClassNotFoundException
	 */
	public PsychoHeroDatabase() throws ClassNotFoundException {
		this(false);
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
			stmt.executeUpdate(String.format("CREATE TABLE IF NOT EXISTS %s ("
					+ "%s INTEGER PRIMARY KEY, " + "%s INTEGER NOT NULL, "
					+ "%s STRING NOT NULL, " + "%s DATETIME NOT NULL)",
					TABLE_SCORES, SCORES_COLUMN_ID, SCORES_COLUMN_SCORE,
					SCORES_COLUMN_NAME, SCORES_COLUMN_TIME_ADDED));

			// Set up events table
			stmt.executeUpdate(String.format("CREATE TABLE IF NOT EXISTS %s ("
					+ "%s INTEGER PRIMARY KEY, " + "%s STRING NOT NULL, "
					+ "%s DATETIME NOT NULL)", TABLE_EVENTS, EVENTS_COLUMN_ID,
					EVENTS_COLUMN_TYPE, EVENTS_COLUMN_TIME_ADDED));

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			DbUtils.closeQuietly(stmt);
			closeConnection(conn);
		}
	}

	/**
	 * Adds a single InsertableEvent to the database.
	 * 
	 * @param event
	 *            The event to add to the database
	 */
	public void addEvent(InsertableEvent event) {
		List<InsertableEvent> evt = new ArrayList<InsertableEvent>(1);
		evt.add(event);
		addEvents(evt);
	}

	/**
	 * Adds a list of InsertableEvent to the database.
	 * 
	 * @param events
	 *            A list of events to add to the database
	 */
	public void addEvents(List<InsertableEvent> events) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn
					.prepareStatement(String
							.format("INSERT INTO %s (%s, %s) VALUES (?, datetime('now'))",
									TABLE_EVENTS, EVENTS_COLUMN_TYPE,
									EVENTS_COLUMN_TIME_ADDED));
			stmt.setQueryTimeout(30);

			// Add all queries as a batch and execute all at once instead of
			// executing them one at a time
			for (InsertableEvent evt : events) {
				stmt.setString(1, evt.getEventType());
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
	 * Adds a single InsertableScore object to the database.
	 * 
	 * @param score
	 *            The score object to add to the database.
	 */
	public void addScore(InsertableScore score) {
		List<InsertableScore> s = new ArrayList<InsertableScore>(1);
		s.add(score);
		addScores(s);
	}

	/**
	 * Adds a list of InsertableScores to the database
	 * 
	 * @param scores
	 */
	public void addScores(List<InsertableScore> scores) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn
					.prepareStatement(String
							.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, datetime('now'))",
									TABLE_SCORES, SCORES_COLUMN_SCORE,
									SCORES_COLUMN_NAME,
									SCORES_COLUMN_TIME_ADDED));
			stmt.setQueryTimeout(30);

			// Add all queries as a batch and execute all at once instead of
			// executing them one at a time
			for (InsertableScore s : scores) {
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
				stmt = conn.prepareStatement(String.format(
						"SELECT * FROM %s ORDER BY %s DESC LIMIT %d",
						TABLE_SCORES, SCORES_COLUMN_SCORE, limit));
			} else {
				// If we don't have a limit, return all results
				stmt = conn.prepareStatement(String.format(
						"SELECT * FROM %s ORDER BY %s DESC", TABLE_SCORES,
						SCORES_COLUMN_SCORE));
			}

			stmt.setQueryTimeout(30);

			// Run query and get results
			rs = stmt.executeQuery();
			while (rs.next()) {
				results.add(new DatabaseScore(rs.getString(SCORES_COLUMN_NAME),
						rs.getInt(SCORES_COLUMN_SCORE), rs
								.getString(SCORES_COLUMN_TIME_ADDED), rs
								.getInt(SCORES_COLUMN_ID)));
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

	/**
	 * Returns the number of events in the database with the same type as the
	 * InsertableEvent.
	 * 
	 * @param event
	 *            An event which type will be used to check for events that
	 *            matches.
	 * @return The number of matches, or -1 if an error occurred
	 */
	public int getNumEventsByType(InsertableEvent event) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(String.format(
					"SELECT COUNT(*) FROM %s WHERE %s = ?",
					TABLE_EVENTS, EVENTS_COLUMN_TYPE));
			stmt.setString(1, event.getEventType());
			
			stmt.setQueryTimeout(30);

			// Run query and get results
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println(e.getSQLState());
		} finally {
			DbUtils.closeQuietly(stmt);
			DbUtils.closeQuietly(rs);
			closeConnection(conn);
		}
		return -1;
	}
}
