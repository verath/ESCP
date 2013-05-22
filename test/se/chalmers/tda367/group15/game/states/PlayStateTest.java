package se.chalmers.tda367.group15.game.states;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayStateTest {
	@Test
	public void testGetID() throws Exception {
		PlayState state = new PlayState(20);
		assertTrue(state.getID() == 20);
	}
}
