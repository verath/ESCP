package se.chalmers.tda367.group15.game.states;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MenuStateMainTest {
	@Test
	public void testGetID() throws Exception {
		MenuStateMain state = new MenuStateMain(20);
		assertTrue(state.getID() == 20);
	}
}
