package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class HeroTest {
	private static Hero testedModel;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testedModel = new Hero();
	}

	@Test
	public void testSetInvalidLocation() {
		// Allow either gracefully handling of invalid locations, or exception
		// as both methods make sense
		try {
			testedModel.setX(-200);
			testedModel.setY(-200);
			assertTrue(testedModel.getX() >= 0 && testedModel.getY() >= 0);
		} catch (IllegalArgumentException e) {

		}
	}
}
