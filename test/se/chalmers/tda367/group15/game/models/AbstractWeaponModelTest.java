package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.newdawn.slick.Animation;

import se.chalmers.tda367.group15.game.models.AbstractWeaponModel;


public class AbstractWeaponModelTest {
	private static AbstractWeaponModel testedModel;
	private static final int MIN_DMG = 1;
	private static final int MAX_DMG = 10;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testedModel = new AbstractWeaponModel("test", MIN_DMG, MAX_DMG, 1, false) {
			@Override
			protected void initAnimation() {
			}

			@Override
			public Animation getAnimation() {
				return null;
			}
		};
	}

	@Test
	public final void testGetDamage() {
		for (int i = 0; i < 50; i++) {
			int damage = testedModel.getDamage();
			assertTrue(damage >= MIN_DMG && damage <= MAX_DMG);
		}
	}

}
