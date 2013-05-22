package se.chalmers.tda367.group15.game.states;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MenuStateHighScoreTest {
	@Test
	public void testGetID() throws Exception {
		MenuStateHighScore menuStateHighScore = new MenuStateHighScore(20);
		assertTrue(menuStateHighScore.getID() == 20);
	}
}
