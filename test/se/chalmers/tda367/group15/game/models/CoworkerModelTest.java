package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CoworkerModelTest {

	@Test
	public final void testCreateCoworkerModel() {
		CoworkerModel model = new CoworkerModel();
		// Basic properties
		assertTrue(model.getHealth() > 0);
		assertTrue(model.isAlive());
		assertTrue(model.getX() >= 0);
		assertTrue(model.getY() >= 0);
		assertTrue(model.getVelocity() > 0);

		// Should have a non-empty collision bound
		assertFalse(model.getBounds().isEmpty());

		// Test the max/min tiles being set
		assertTrue(model.getMaxTileX() >= 0);
		assertTrue(model.getMaxTileY() >= 0);
		assertTrue(model.getMinTileX() >= 0);
		assertTrue(model.getMinTileY() >= 0);

	}

}
