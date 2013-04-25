package se.chalmers.tda367.group15.game.models.weapons;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.newdawn.slick.Animation;

public class WeaponTest {
	private static Weapon testedModel;
	private static final int MIN_DMG = 1;
	private static final int MAX_DMG = 10;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testedModel = new Weapon("test", MIN_DMG, MAX_DMG, 1, false) {
			@Override
			protected void initAnimation() {
			}

			@Override
			public Animation getAnimation() {
				return null;
			}

			@Override
			public void fire() {
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
