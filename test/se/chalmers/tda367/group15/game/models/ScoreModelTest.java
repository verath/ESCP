package se.chalmers.tda367.group15.game.models;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ScoreModelTest {

	/*
	 * This model is such a simple model that it can basically be tested all at
	 * once
	 */
	// TODO: I guess we should break this up into more tests. maybe.
	@Test
	public void testScore() {
		ScoreModel sm = new ScoreModel(0);
		assertTrue(sm.getScore() == 0);

		sm.setScore(2);
		assertTrue(sm.getScore() == 2);

		sm.increaseScore();
		assertTrue(sm.getScore() == 3);

		sm.increaseScore(2);
		assertTrue(sm.getScore() == 5);

		sm.decreaseScore();
		assertTrue(sm.getScore() == 4);

		sm.decreaseScore(2);
		assertTrue(sm.getScore() == 2);
	}

}
