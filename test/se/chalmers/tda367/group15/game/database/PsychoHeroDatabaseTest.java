package se.chalmers.tda367.group15.game.database;

import java.util.ArrayList;
import java.util.List;

public class PsychoHeroDatabaseTest {
	
	@Test
	public void testConnectToDatabase() throws ClassNotFoundException {
		PsychoHeroDatabase psh = new PsychoHeroDatabase();
		assertNotNull(psh);
	}

	@Test
	public void testAddScoreToDatabase() throws ClassNotFoundException {
		PsychoHeroDatabase psh = new PsychoHeroDatabase(true);

		Score score = new Score("Test", 200);
		psh.addScore(score);

		DatabaseScore dbResult = psh.getHighscores(1).get(0);

		// Make sure it now has a time and an id above -1
		assertFalse(dbResult.getTime().isEmpty());
		assertFalse(dbResult.getId() < 0);

		// We have to "up-cast" it to a Score, as the object returned
		// by the database class is a DatabaseScore, holding more information.
		Score result = new Score(dbResult);

		assertEquals(score, result);
	}

	@Test
	public void testAddMultipleScoresToDatabase() throws ClassNotFoundException {
		PsychoHeroDatabase psh = new PsychoHeroDatabase(true);

		// Add some dummy scores
		List<Score> scores = new ArrayList<Score>();
		scores.add(new Score("Test1", 2));
		scores.add(new Score("Test2", 4));
		scores.add(new Score("Test3", 3));
		scores.add(new Score("Test4", 1));
		psh.addScore(scores);

		List<DatabaseScore> resultScores = psh.getHighscores();

		// Make sure we have as many scores as we inserted
		assertTrue(resultScores.size() == scores.size());

		// And make sure every inserted score is also returned
		for (DatabaseScore res : resultScores) {
			boolean exist = false;
			for (Score s : scores) {
				// Have to cast res to Score, see testAddScoreToDatabase.
				if (s.equals(new Score(res))) {
					exist = true;
					break;
				}
			}
			assertTrue(exist);
		}
	}

	@Test
	public void testHighscoreSorted() throws ClassNotFoundException {
		PsychoHeroDatabase psh = new PsychoHeroDatabase(true);

		// Add some dummy scores
		List<Score> scores = new ArrayList<Score>();
		scores.add(new Score("Test1", 2));
		scores.add(new Score("Test2", 4));
		scores.add(new Score("Test3", 3));
		scores.add(new Score("Test4", 1));
		psh.addScore(scores);

		// Get 1 from the highscore list
		Score result = psh.getHighscores(1).get(0);

		// Make sure the highest score is returned
		assertTrue(result.getScore() == 4 && result.getName().equals("Test2"));
	}

	@Test
	public void testGetEmptyHighscore() throws ClassNotFoundException {
		PsychoHeroDatabase psh = new PsychoHeroDatabase(true);

		// Get 1 from the highscore list
		List<DatabaseScore> result = psh.getHighscores();

		// Make sure the highest score is returned
		assertTrue(result.isEmpty());
	}
}
