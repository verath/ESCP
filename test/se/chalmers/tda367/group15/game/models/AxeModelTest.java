package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import se.chalmers.tda367.group15.game.models.weapons.AxeModel;

public class AxeModelTest {
	@Test
	public void testCreateAxeModel() {
		AxeModel axeModel = new AxeModel();

		assertFalse(axeModel.getName().isEmpty());
		assertTrue(axeModel.getDamage() > 0);
		assertTrue(axeModel.getFiringSpeed() > 0);
	}
}
