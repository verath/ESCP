package se.chalmers.tda367.group15.game.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PsychoHeroDatabaseTest {

	@Test
	public void testConnectToDatabase() throws ClassNotFoundException {
		GameDatabase psh = new GameDatabase();
		assertNotNull(psh);
	}

	@Test
	public void testAddScoreToDatabase() throws ClassNotFoundException {
		GameDatabase psh = new GameDatabase(true);

		InsertableScore score = new InsertableScore("Test", 200);
		psh.addScore(score);

		DatabaseScore dbResult = psh.getHighscores(1).get(0);

		// Make sure it now has a time and an id above -1
		assertFalse(dbResult.getTime().isEmpty());
		assertFalse(dbResult.getId() < 0);

		// We have to "up-cast" it to a Score, as the object returned
		// by the database class is a DatabaseScore, holding more information.
		InsertableScore result = new InsertableScore(dbResult);

		assertEquals(score, result);
	}

	@Test
	public void testAddEventToDatabase() throws ClassNotFoundException {
		GameDatabase psh = new GameDatabase(true);

		InsertableEvent event = new InsertableEvent("Test");
		psh.addEvent(event);

		assertTrue(psh.getNumEventsByType(event) == 1);
		assertTrue(psh.getNumEventsByType(new InsertableEvent("TestNo")) == 0);
	}

	@Test
	public void testAddMultipleScoresToDatabase() throws ClassNotFoundException {
		GameDatabase psh = new GameDatabase(true);

		// Add some dummy scores
		List<InsertableScore> scores = new ArrayList<InsertableScore>();
		scores.add(new InsertableScore("Test1", 2));
		scores.add(new InsertableScore("Test2", 4));
		scores.add(new InsertableScore("Test3", 3));
		scores.add(new InsertableScore("Test4", 1));
		psh.addScores(scores);

		List<DatabaseScore> resultScores = psh.getHighscores();

		// Make sure we have as many scores as we inserted
		assertTrue(resultScores.size() == scores.size());

		// And make sure every inserted score is also returned
		for (DatabaseScore res : resultScores) {
			boolean exist = false;
			for (InsertableScore s : scores) {
				// Have to cast res to Score, see testAddScoreToDatabase.
				if (s.equals(new InsertableScore(res))) {
					exist = true;
					break;
				}
			}
			assertTrue(exist);
		}
	}

	@Test
	public void testAddMultipleEventToDatabase() throws ClassNotFoundException {
		GameDatabase psh = new GameDatabase(true);

		// Add some dummy events
		List<InsertableEvent> events = new ArrayList<InsertableEvent>();
		events.add(new InsertableEvent("Test"));
		events.add(new InsertableEvent("Test"));
		events.add(new InsertableEvent("Test"));
		events.add(new InsertableEvent("Test2"));
		events.add(new InsertableEvent("Test2"));

		psh.addEvents(events);

		assertTrue(psh.getNumEventsByType(new InsertableEvent("Test")) == 3);
		assertTrue(psh.getNumEventsByType(new InsertableEvent("Test2")) == 2);
		assertTrue(psh.getNumEventsByType(new InsertableEvent("TestNo")) == 0);

	}

	@Test
	public void testHighscoreSorted() throws ClassNotFoundException {
		GameDatabase psh = new GameDatabase(true);

		// Add some dummy scores
		List<InsertableScore> scores = new ArrayList<InsertableScore>();
		scores.add(new InsertableScore("Test1", 2));
		scores.add(new InsertableScore("Test2", 4));
		scores.add(new InsertableScore("Test3", 3));
		scores.add(new InsertableScore("Test4", 1));
		psh.addScores(scores);

		// Get 1 from the highscore list
		InsertableScore result = psh.getHighscores(1).get(0);

		// Make sure the highest score is returned
		assertTrue(result.getScore() == 4 && result.getName().equals("Test2"));
	}

	@Test
	public void testGetEmptyHighscore() throws ClassNotFoundException {
		GameDatabase psh = new GameDatabase(true);

		// Get 1 from the highscore list
		List<DatabaseScore> result = psh.getHighscores();

		// Make sure the highest score is returned
		assertTrue(result.isEmpty());
	}
}
