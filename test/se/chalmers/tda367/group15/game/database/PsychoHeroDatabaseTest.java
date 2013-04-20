package se.chalmers.tda367.group15.game.database;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PsychoHeroDatabaseTest {

	@Test
	public void testAddScoreToDatabase() throws ClassNotFoundException {
		PsychoHeroDatabase psh = new PsychoHeroDatabase(true);

		Score score = new Score("Test", 200);
		psh.addScore(score);

		Score result = psh.getHighscores(1).get(0);

		assertEquals(result, score);
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

		List<Score> resultScores = psh.getHighscores();

		// Make sure we have as many scores as we inserted
		assertTrue(resultScores.size() == scores.size());

		// And make sure every inserted score is also returned
		for (Score res : resultScores) {
			boolean exist = false;
			for (Score s : scores) {
				if (s.equals(res)) {
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
		assertTrue(result.getScore() == 4
				&& result.getName().equals("Test2"));
	}
}
