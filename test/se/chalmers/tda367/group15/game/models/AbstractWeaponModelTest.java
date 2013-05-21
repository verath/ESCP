package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AbstractWeaponModelTest {
	private static AbstractWeaponModel testedModel;
	private static final int MIN_DMG = 1;
	private static final int MAX_DMG = 10;

	@Before
	public void setUpBefore() throws Exception {
		testedModel = new AbstractWeaponModel("test", MIN_DMG, MAX_DMG, 1) {
		};
	}

	@Test
	public final void testGetDamage() {
		for (int i = 0; i < 50; i++) {
			int damage = testedModel.getDamage();
			assertTrue(damage >= MIN_DMG && damage <= MAX_DMG);
		}
	}

	@Test
	public final void getFiringSpeed() {
		assertTrue(testedModel.getFiringSpeed() == 1);
	}

	@Test
	public final void getName() {
		assertEquals(testedModel.getName(), "test");
	}

}
