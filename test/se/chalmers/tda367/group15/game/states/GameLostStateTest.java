package se.chalmers.tda367.group15.game.states;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameLostStateTest {
	@Test
	public void testGetID() throws Exception {
		GameLostState gameLostState = new GameLostState(20);
		assertTrue(gameLostState.getID() == 20);
	}
}
