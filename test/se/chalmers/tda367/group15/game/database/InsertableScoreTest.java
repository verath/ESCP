package se.chalmers.tda367.group15.game.database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InsertableScoreTest {

	@Test
	public void testCreateScore() {
		InsertableScore s = new InsertableScore("abc", 200);
		assertTrue(s.getName().equals("abc") && s.getScore() == 200);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateScoreNullName() {
		new InsertableScore(null, 200);
	}

	@Test
	public void testCreateScoreFromScore() {
		InsertableScore s = new InsertableScore("abc", 20);
		InsertableScore sCopy = new InsertableScore(s);

		assertTrue(sCopy.getName().equals(s.getName())
				&& sCopy.getScore() == s.getScore());
	}

	@Test
	public void testScoreEquals() {
		InsertableScore s1 = new InsertableScore("abc", 123);
		InsertableScore s2 = new InsertableScore("abc", 123);
		InsertableScore s3 = new InsertableScore("abcd", 123);

		assertTrue(s1.equals(s2) && s2.equals(s1));
		assertFalse(s3.equals(s1) || s1.equals(s3));
	}

	@Test
	public void testScoreHashCode() {
		InsertableScore s1 = new InsertableScore("abc", 123);
		InsertableScore s2 = new InsertableScore("abc", 123);
		InsertableScore s3 = new InsertableScore("abcd", 123);

		assertTrue(s1.hashCode() == s2.hashCode());
		assertFalse(s3.hashCode() == s1.hashCode());
	}

}
