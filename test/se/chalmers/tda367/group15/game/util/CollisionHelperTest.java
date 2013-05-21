package se.chalmers.tda367.group15.game.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import org.junit.Test;

/*
 Test for the collisionHelper class
 */
public class CollisionHelperTest {

	/*
	 * Tests the testIsInSight by blocking
	 */
	@Test
	public void testIsInSight() throws Exception {

		// See from one point to another without anything blocking
		assertTrue(CollisionHelper.isInSight(
				new ArrayList<Rectangle2D.Float>(), 1, 1, 2, 2));

		// Block LoS in X
		final Rectangle2D.Float blockerX = new Rectangle2D.Float(5, 0, 1, 1);
		ArrayList<Rectangle2D.Float> blockers = new ArrayList<Rectangle2D.Float>();
		blockers.add(blockerX);

		// Should not be able to see in x (A to B)
		assertFalse(CollisionHelper.isInSight(blockers, 0, 0, 10, 0));
		// But should be able to see in Y (A to C)
		assertTrue(CollisionHelper.isInSight(blockers, 0, 0, 0, 10));
		// And should be able to see diagonally (A to D)
		assertTrue(CollisionHelper.isInSight(blockers, 0, 0, 10, 10));

		// Add block for LoS in Y
		final Rectangle2D.Float blockerY = new Rectangle2D.Float(0, 5, 1, 1);
		blockers.add(blockerY);

		// Should not be able to see in x (A to B)
		assertFalse(CollisionHelper.isInSight(blockers, 0, 0, 10, 0));
		// And should not be able to see in Y (A to C)
		assertFalse(CollisionHelper.isInSight(blockers, 0, 0, 0, 10));
		// But should be able to see diagonally (A to D)
		assertTrue(CollisionHelper.isInSight(blockers, 0, 0, 10, 10));

		// Add block for LoS diagonally
		final Rectangle2D.Float blockerDiag = new Rectangle2D.Float(5, 5, 1, 1);
		blockers.add(blockerDiag);

		// Should not be able to see in x (A to B)
		assertFalse(CollisionHelper.isInSight(blockers, 0, 0, 10, 0));
		// And should not be able to see in Y (A to C)
		assertFalse(CollisionHelper.isInSight(blockers, 0, 0, 0, 10));
		// And should not be able to see diagonally (A to D)
		assertFalse(CollisionHelper.isInSight(blockers, 0, 0, 10, 10));
	}

	@Test
	public void testZeroLengthLine() {
		assertFalse(CollisionHelper.recIntersectLine(new Rectangle2D.Float(0,
				0, 1, 1), 0, 0, 0, 0));
	}
}
