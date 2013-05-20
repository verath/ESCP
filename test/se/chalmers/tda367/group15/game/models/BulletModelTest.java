package se.chalmers.tda367.group15.game.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BulletModelTest {

	/*
	 * Make sure we the hero created has some basic properties such as health
	 */
	@Test
	public final void testCreateBullet() {
		BulletModel bm = new BulletModel();

		// Basic properties
		assertFalse(bm.isAlive());
		assertTrue(bm.getX() >= 0);
		assertTrue(bm.getY() >= 0);
		assertTrue(bm.getVelocity() > 0);

		// Should have a non-empty collision bound
		assertFalse(bm.getBounds().isEmpty());
	}

}
