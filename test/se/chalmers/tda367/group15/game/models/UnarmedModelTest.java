package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import se.chalmers.tda367.group15.game.models.weapons.UnarmedModel;

public class UnarmedModelTest {
	@Test
	public void testCreateUnarmedModel() {
		UnarmedModel unarmedModel = new UnarmedModel();

		assertFalse(unarmedModel.getName().isEmpty());
		assertTrue(unarmedModel.getDamage() > 0);
		assertTrue(unarmedModel.getFiringSpeed() > 0);
	}
}
