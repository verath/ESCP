package se.chalmers.tda367.group15.game.util;

import java.awt.geom.Rectangle2D;
import java.util.Collection;

/**
 * A class holding static utility functions for collisions and related events.
 * 
 * @author Peter, Carl
 */
public class CollisionHelper {

	/**
	 * Method for checking if two points are in sight of each other. This is
	 * done by drawing a line between the two points and checking against the
	 * provided obstructionBounds.
	 * 
	 * @param obstructionBounds
	 *            The obstruction that can get in the way
	 * @param point1X
	 *            first point x position
	 * @param point1Y
	 *            first point y position
	 * @param point2X
	 *            second point x position
	 * @param point2Y
	 *            second point y position
	 * @return True if it is possible to "see" from point 1 to point 2.
	 */
	public static boolean isInSight(
			Collection<Rectangle2D.Float> obstructionBounds, float point1X,
			float point1Y, float point2X, float point2Y) {
		for (Rectangle2D.Float rect : obstructionBounds) {
			if (recIntersectLine(rect, point1X, point1Y, point2X, point2Y)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Tests if a line from point 1 to point 2 is intersecting the rectangle
	 * rect.
	 * 
	 * @param rect
	 *            the Rectangle that maybe intersects
	 * @param point1X
	 *            x variable of first point
	 * @param point1Y
	 *            y variable of first point
	 * @param point2X
	 *            x variable of second point
	 * @param point2Y
	 *            y variable of second point
	 * @return true if line is intersected by rect
	 */
	public static boolean recIntersectLine(Rectangle2D.Float rect,
			float point1X, float point1Y, float point2X, float point2Y) {
		return (linesIntersect(point1X, point1Y, point2X, point2Y, rect.getX(),
				rect.getY(), rect.getX() + rect.getWidth(),
				rect.getY() + rect.getHeight()) || linesIntersect(point1X,
				point1Y, point2X, point2Y, rect.getX(),
				rect.getY() + rect.getHeight(), rect.getX() + rect.getWidth(),
				rect.getY()));
	}

	/**
	 * 
	 * The fastest way to test if 2 line segments intersect. Tests if the line
	 * segment from (x1, y1) to (x2, y2) intersects the line segment from (x3,
	 * y3) to (x4, y4). My tests showed that this method was about 25% faster
	 * than java.awt.geom.Line2D.linesIntersect(x1, y1, x2, y2, x3, y3, x4, y4).
	 * 
	 * NOTE: This method is taken from java-gaming.org where it was posted by
	 * user JGO Knight. For some reason Java default intersect method as
	 * declared in the interface Shape doesn't work.
	 * 
	 * @param x1
	 *            Point 1 X
	 * @param y1
	 *            Point 1 Y
	 * @param x2
	 *            Point 2 X
	 * @param y2
	 *            Point 2 Y
	 * @param x3
	 *            Point 3 X
	 * @param y3
	 *            Point 3 Y
	 * @param x4
	 *            Point 4 X
	 * @param y4
	 *            Point 4 Y
	 * @return true if intersect
	 */
	private static boolean linesIntersect(double x1, double y1, double x2,
			double y2, double x3, double y3, double x4, double y4) {
		// Return false if either of the lines have zero length
		if (x1 == x2 && y1 == y2 || x3 == x4 && y3 == y4) {
			return false;
		}
		// Fastest method, based on Franklin Antonio's
		// "Faster Line Segment Intersection" topic "in Graphics Gems III" book
		// (http://www.graphicsgems.org/)
		double ax = x2 - x1;
		double ay = y2 - y1;
		double bx = x3 - x4;
		double by = y3 - y4;
		double cx = x1 - x3;
		double cy = y1 - y3;

		double alphaNumerator = by * cx - bx * cy;
		double commonDenominator = ay * bx - ax * by;
		if (commonDenominator > 0) {
			if (alphaNumerator < 0 || alphaNumerator > commonDenominator) {
				return false;
			}
		} else if (commonDenominator < 0) {
			if (alphaNumerator > 0 || alphaNumerator < commonDenominator) {
				return false;
			}
		}
		double betaNumerator = ax * cy - ay * cx;
		if (commonDenominator > 0) {
			if (betaNumerator < 0 || betaNumerator > commonDenominator) {
				return false;
			}
		} else if (commonDenominator < 0) {
			if (betaNumerator > 0 || betaNumerator < commonDenominator) {
				return false;
			}
		}
		if (commonDenominator == 0) {
			// This code wasn't in Franklin Antonio's method. It was added by
			// Keith Woodward.
			// The lines are parallel.
			// Check if they're collinear.
			double y3LessY1 = y3 - y1;
			double collinearityTestForP3 = x1 * (y2 - y3) + x2 * (y3LessY1)
					+ x3 * (y1 - y2); // see
			// http://mathworld.wolfram.com/Collinear.html
			// If p3 is collinear with p1 and p2 then p4 will also be collinear,
			// since p1-p2 is parallel with p3-p4
			if (collinearityTestForP3 == 0) {
				// The lines are collinear. Now check if they overlap.
				if (x1 >= x3 && x1 <= x4 || x1 <= x3 && x1 >= x4 || x2 >= x3
						&& x2 <= x4 || x2 <= x3 && x2 >= x4 || x3 >= x1
						&& x3 <= x2 || x3 <= x1 && x3 >= x2) {
					if (y1 >= y3 && y1 <= y4 || y1 <= y3 && y1 >= y4
							|| y2 >= y3 && y2 <= y4 || y2 <= y3 && y2 >= y4
							|| y3 >= y1 && y3 <= y2 || y3 <= y1 && y3 >= y2) {
						return true;
					}
				}
			}
			return false;
		}
		return true;
	}

}
