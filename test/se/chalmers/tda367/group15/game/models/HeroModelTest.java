package se.chalmers.tda367.group15.game.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HeroModelTest {

	/*
	 * Make sure we the hero created has some basic properties such as health
	 */
	@Test
	public final void testCreateHero() {
		HeroModel hm = new HeroModel();
		// Basic properties
		assertTrue(hm.getX() >= 0);
		assertTrue(hm.getY() >= 0);

		// Should have a non-empty collision bound
		assertFalse(hm.getBounds().isEmpty());
	}

}
