package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Rectangle2D;

import org.junit.Before;
import org.junit.Test;

public class AbstractMovingModelTest {

	private static AbstractMovingModel testedModel;

	@Before
	public void setUpBefore() throws Exception {
		// Make sure we reset between each test
		testedModel = new AbstractMovingModel() {
		};
	}

	@Test
	public final void testGetSetBounds() {
		int x = 10, y = 10, width = 10, height = 10;
		testedModel.setX(x);
		testedModel.setY(y);
		testedModel.setWidth(width);
		testedModel.setHeight(height);
		Rectangle2D.Float b = new Rectangle2D.Float(x, y, width, height);
		assertEquals(b, testedModel.getBounds());
	}

	@Test
	public final void testSetGetHealth() {
		testedModel.setHealth(20);
		assertTrue(testedModel.getHealth() == 20);
	}

	@Test
	public final void testSetGetMoving() {
		testedModel.setMoving(true);
		assertTrue(testedModel.isMoving());
		testedModel.setMoving(false);
		assertFalse(testedModel.isMoving());
	}

	@Test
	public final void testSetGetHeight() {
		testedModel.setHeight(20f);
		assertTrue(testedModel.getHeight() == 20f);
	}

	@Test
	public final void testSetGetOffset() {
		testedModel.setOffset(20);
		assertTrue(testedModel.getOffset() == 20);
	}

	@Test
	public final void testSetGetRotation() {
		testedModel.setRotation(0.1);
		assertTrue(testedModel.getRotation() == 0.1);
	}

	@Test
	public final void testSetGetVelocity() {
		testedModel.setVelocity(0.1f);
		assertTrue(testedModel.getVelocity() == 0.1f);
	}

	@Test
	public final void testSetGetWidth() {
		testedModel.setWidth(0.1f);
		assertTrue(testedModel.getWidth() == 0.1f);
	}

	@Test
	public final void testTakeDamage() {
		testedModel.setHealth(100);
		testedModel.takeDamage(1);
		assertTrue(testedModel.getHealth() == 99);
	}

}
