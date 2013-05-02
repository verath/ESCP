package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HeroModelTest {

	/*
	 * Make sure we the hero created has some basic properties such as health
	 */
	@Test
	public final void testCreateHero() {
		HeroModel hm = new HeroModel();
		// Make sure it starts with a health
		assertTrue(hm.getHealth() > 0);
		// Make sure we have coords 
		assertTrue(hm.getX() >= 0);
		assertTrue(hm.getY() >= 0);
		// Have some kind of weapon
		assertTrue(hm.getCurrentWeapon() != null);
		// Should have a non-empty collision bound
		assertFalse(hm.getBounds().isEmpty());
	}

}
