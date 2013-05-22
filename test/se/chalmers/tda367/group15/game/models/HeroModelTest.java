package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertEquals;
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
		// Basic properties
		assertTrue(hm.getX() >= 0);
		assertTrue(hm.getY() >= 0);

		// Should have a non-empty collision bound
		assertFalse(hm.getBounds().isEmpty());
	}

	@Test
	public final void changeEquippedWeapon() {
		HeroModel hm = new HeroModel();
		hm.addWeapon(new UnarmedModel());
		assertTrue(hm.getWeapons().size() == 1);

		AbstractWeaponModel w = new UnarmedModel();
		hm.setCurrentWeapon(w);
		assertEquals(hm.getCurrentWeapon(), w);
	}

	@Test
	public final void changeModelPath() {
		HeroModel hm = new HeroModel();
		hm.setAnimationPath("abc");
		assertEquals(hm.getAnimationPath(), "abc");
	}

}
