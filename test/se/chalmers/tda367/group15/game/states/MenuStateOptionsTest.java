package se.chalmers.tda367.group15.game.states;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MenuStateOptionsTest {
	@Test
	public void testGetID() throws Exception {
		MenuStateOptions state = new MenuStateOptions(20);
		assertTrue(state.getID() == 20);
	}
}
