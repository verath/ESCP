package se.chalmers.tda367.group15.game.models;

import se.chalmers.tda367.group15.game.models.weapons.PistolModel;

/**
 * Class representing a soldier model.
 * 
 * @author simon
 * 
 */
public class SoldierModel extends AbstractNpcModel {

	/**
	 * Creates a new SoldierModel enemy at position (64, 128) facing left
	 */
	public SoldierModel() {
		this(64f, 128f);
	}

	/**
	 * Creates a new SoldierModel enemy at position (x, y) facing a random
	 * direction
	 * 
	 * @param x
	 *            pixels from left wall of window
	 * @param y
	 *            pixels from top of window
	 */
	public SoldierModel(float x, float y) {
		this(x, y, 360 * Math.random());
	}

	/**
	 * Creates a new SoldierModel enemy at position (x, y) and facing direction
	 * rot
	 * 
	 * @param x
	 *            pixels from left wall of window
	 * @param y
	 *            pixels from top of window
	 * @param rot
	 *            Clockwise rotation in degrees starting facing left of screen.
	 */
	private SoldierModel(float x, float y, double rot) {
		this(x, y, rot, 0, 32, 0, 24);
	}

	/**
	 * Creates a new SoldierModel enemy at position (x, y) and facing a random
	 * direction. random movement is restricted by (x1, y1) and (x2, y2) which
	 * is tile coordinates.
	 * 
	 * @param x
	 *            pixels from left wall of window
	 * @param y
	 *            pixels from top of window
	 * @param x1
	 *            The minimum tile x coordinate
	 * @param x2
	 *            The maximum tile x coordinate
	 * @param y1
	 *            The minimum tile y coordinate
	 * @param y2
	 *            The maximum tile y coordinate
	 */
	public SoldierModel(float x, float y, int x1, int x2, int y1, int y2) {
		this(x, y, Math.random(), x1, x2, y1, y2);
	}

	/**
	 * Creates a new SoldierModel enemy at position (x, y) and facing a random
	 * direction. random movement is restricted by (x1, y1) and (x2, y2) which
	 * is tile coordinates.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @param rot
	 *            The rotation angle.
	 * @param x1
	 *            The minimum tile x coordinate
	 * @param x2
	 *            The maximum tile x coordinate
	 * @param y1
	 *            The minimum tile y coordinate
	 * @param y2
	 *            The maximum tile y coordinate
	 */
	private SoldierModel(float x, float y, double rot, int x1, int x2, int y1,
			int y2) {
		super(x1, x2, y1, y2);
		setX(x);
		setY(y);
		setVelocity(0.1f);
		setWidth(42);
		setHeight(42);
		setRotation(rot);
		setOffset(11);
		setAlive(true);
		setHealth(100);
		addWeapon(new PistolModel());
		setCurrentWeapon(getWeapons().get(0));
		setAnimationPath("special/");
	}
}
