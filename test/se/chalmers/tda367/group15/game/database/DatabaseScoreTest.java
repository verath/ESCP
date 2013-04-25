package se.chalmers.tda367.group15.game.database;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class DatabaseScoreTest {

	@Test
	public void testCreateDatabaseScore() {
		DatabaseScore ds = new DatabaseScore("a", 20, "1414", 1);
		assertTrue(ds.getTime().equals("1414") && ds.getId() == 1);
	}
	
	@Test
	public void testDatabaseScoreEquals() {
		DatabaseScore s1 = new DatabaseScore("abc", 123, "2", 1);
		DatabaseScore s2 = new DatabaseScore("abc", 123, "2", 1);
		DatabaseScore s3 = new DatabaseScore("abc", 123, "2", 3);

		assertTrue(s1.equals(s2) && s2.equals(s1));
		assertFalse(s3.equals(s1) || s1.equals(s3));
	}
	
	@Test
	public void testScoreHashCode () {
		DatabaseScore s1 = new DatabaseScore("abc", 123, "2", 1);
		DatabaseScore s2 = new DatabaseScore("abc", 123, "2", 1);
		DatabaseScore s3 = new DatabaseScore("abc", 123, "2", 3);

		assertTrue(s1.hashCode() == s2.hashCode());
		assertFalse(s3.hashCode() == s1.hashCode());
	}

}
