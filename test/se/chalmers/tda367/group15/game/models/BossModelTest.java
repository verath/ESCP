package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BossModelTest {

	@Test
	public void testCreateBossModel() {

		// Test more specific constructor
		BossModel bossModel = new BossModel(1, 1, 1);
		assertTrue(bossModel.getX() == 1);
		assertTrue(bossModel.getY() == 1);
		assertTrue(bossModel.getRotation() == 1);

		assertTrue(bossModel.getX() >= 0);
		assertTrue(bossModel.getY() >= 0);
		assertTrue(bossModel.getWidth() >= 0);
		assertTrue(bossModel.getHeight() >= 0);
		assertTrue(bossModel.getRotation() >= 0);
		assertTrue(bossModel.getHealth() >= 0);
		assertTrue(bossModel.isAlive());
	}
}
