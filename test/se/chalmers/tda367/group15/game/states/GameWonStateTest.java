package se.chalmers.tda367.group15.game.states;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameWonStateTest {
	@Test
	public void testGetID() throws Exception {
		GameWonState gameWonState = new GameWonState(20);
		assertTrue(gameWonState.getID() == 20);
	}
}
