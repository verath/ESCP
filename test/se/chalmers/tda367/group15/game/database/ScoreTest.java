package se.chalmers.tda367.group15.game.database;


public class ScoreTest {

	@Test
	public void testCreateScore() {
		Score s = new Score("abc", 200);
		assertTrue(s.getName().equals("abc") && s.getScore() == 200);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testCreateScoreNullName() {
		new Score(null, 200);
	}

	@Test
	public void testCreateScoreFromScore() {
		Score s = new Score("abc", 20);
		Score sCopy = new Score(s);

		assertTrue(sCopy.getName().equals(s.getName())
				&& sCopy.getScore() == s.getScore());
	}

	@Test
	public void testScoreEquals() {
		Score s1 = new Score("abc", 123);
		Score s2 = new Score("abc", 123);
		Score s3 = new Score("abcd", 123);

		assertTrue(s1.equals(s2) && s2.equals(s1));
		assertFalse(s3.equals(s1) || s1.equals(s3));
	}
	
	@Test
	public void testScoreHashCode () {
		Score s1 = new Score("abc", 123);
		Score s2 = new Score("abc", 123);
		Score s3 = new Score("abcd", 123);

		assertTrue(s1.hashCode() == s2.hashCode());
		assertFalse(s3.hashCode() == s1.hashCode());
	}

}
