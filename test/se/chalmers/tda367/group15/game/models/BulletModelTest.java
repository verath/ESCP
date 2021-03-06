package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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

	@Test
	public final void changeDamage() {
		BulletModel bm = new BulletModel();
		bm.setDamage(5);
		assertEquals(bm.getDamage(), 5);
	}

	@Test
	public final void testImagePathNotNull() {
		DonutModel dm = new DonutModel();
		assertNotNull(dm.getImagePath());
	}

}
