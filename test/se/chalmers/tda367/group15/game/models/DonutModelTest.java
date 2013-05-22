package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DonutModelTest {

	/*
	 * Make sure we the hero created has some basic properties such as health
	 */
	@Test
	public final void testCreateDonut() {
		DonutModel dm = new DonutModel();

		// Basic properties
		assertFalse(dm.isAlive());
		assertTrue(dm.getX() >= 0);
		assertTrue(dm.getY() >= 0);
		assertTrue(dm.getVelocity() > 0);

		// Should have a non-empty collision bound
		assertFalse(dm.getBounds().isEmpty());

	}

	@Test
	public final void testImagePathNotNull() {
		DonutModel dm = new DonutModel();
		assertNotNull(dm.getImagePath());
	}

}
