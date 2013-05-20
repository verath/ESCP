package se.chalmers.tda367.group15.game.models;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DummyEnemyModelTest {

	@Test
	public final void testCreateDummyEnemy() {
		CoworkerModel dem = new CoworkerModel();
		// Basic properties
		assertTrue(dem.getHealth() > 0);
		assertTrue(dem.isAlive());
		assertTrue(dem.getX() >= 0);
		assertTrue(dem.getY() >= 0);
		assertTrue(dem.getVelocity() > 0);

		// Should have a non-empty collision bound
		assertFalse(dem.getBounds().isEmpty());
	}

}
