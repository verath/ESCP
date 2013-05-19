package se.chalmers.tda367.group15.game.models;

/**
 * Class representing a soldier model.
 * 
 * @author simon
 * 
 */
public class SpecialModel extends AbstractNpcModel {

	/**
	 * Creates a new soldier.
	 */
	public SpecialModel() {
		this(64f, 128f);
	}

	/**
	 * Creates a new soldier.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 */
	public SpecialModel(float x, float y) {
		this(x, y, 360 * Math.random());
	}

	/**
	 * Creates a new soldier.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @param rot
	 *            The rotation angle.
	 */
	public SpecialModel(float x, float y, double rot) {
		this(x, y, rot, 0, 32, 0, 24);
	}

	/**
	 * 
	 * Creates a new soldier.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @param x1
	 *            The minimum tile x coordinate
	 * @param x2
	 *            The maximum tile x coordinate
	 * @param y1
	 *            The minimum tile y coordinate
	 * @param y2
	 *            The maximum tile y coordinate
	 */
	public SpecialModel(float x, float y, int x1, int x2, int y1, int y2) {
		this(x, y, 0, x1, x2, y1, y2);
	}

	/**
	 * Creates a new soldier.
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
	public SpecialModel(float x, float y, double rot, int x1, int x2, int y1,
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
