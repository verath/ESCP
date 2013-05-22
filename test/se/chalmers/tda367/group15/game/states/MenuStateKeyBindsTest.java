package se.chalmers.tda367.group15.game.states;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MenuStateKeyBindsTest {
	@Test
	public void testGetID() throws Exception {
		MenuStateKeyBinds menuStateKeyBinds = new MenuStateKeyBinds(20);
		assertTrue(menuStateKeyBinds.getID() == 20);
	}
}
