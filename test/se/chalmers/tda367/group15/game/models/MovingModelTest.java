package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Rectangle2D;

import org.junit.BeforeClass;
import org.junit.Test;

public class MovingModelTest {

	private static AbstractMovingModel testedModel;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testedModel = new AbstractMovingModel() {
		};
	}

	@Test
	public final void testGetBounds() {
		int x = 10, y = 10, width = 10, height = 10;
		testedModel.setX(x);
		testedModel.setY(y);
		testedModel.setWidth(width);
		testedModel.setHeight(height);
		Rectangle2D.Float b = new Rectangle2D.Float(x, y, width, height);
		assertEquals(b, testedModel.getBounds());
	}

	@Test
	public final void testTakeDamage() {
		testedModel.setHealth(100);
		testedModel.takeDamage(1);
		assertTrue(testedModel.getHealth() == 99);
	}
	
	@Test
	public final void testDieFromDamage() {
		testedModel.setHealth(1);
		testedModel.setAlive(true);
		testedModel.takeDamage(1);
		assertFalse(testedModel.isAlive());
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

	@Test
	public void testSetInvalidSize() {
		// Allow either gracefully handling of invalid locations, or exception
		// as both methods make sense
		try {
			testedModel.setHeight(-200);
			testedModel.setWidth(-200);
			assertTrue(testedModel.getX() >= 0 && testedModel.getY() >= 0);
		} catch (IllegalArgumentException e) {

		}
	}

}
