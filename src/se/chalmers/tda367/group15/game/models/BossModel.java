package se.chalmers.tda367.group15.game.models;


/**
 * Simple class for representing a dummy enemy. This enemy is only intended for
 * testing.
 * 
 * @author simon
 * 
 */
public class BossModel extends AbstractNpcModel {
		
	/**
	 * Creates a new dummy enemy.
	 */
	public BossModel() {
		this(64f, 128f);
	}

	public BossModel(float x, float y) {
		this(x, y, 360 * Math.random());

	}

	public BossModel(float x, float y, double rot) {
		this(x, y, rot, 0, 32, 0, 24);
	}

	public BossModel(float x, float y, int x1, int x2, int y1, int y2) {
		this(x, y, 0, x1, x2, y1, y2);
	}

	/**
	 * Creates a new dummy enemy.
	 * 
	 * @param x
	 *            x position of DummyEnemyModel
	 * @param y
	 *            y position of DummyEnemyModel
	 * @param rot
	 *            angle to face in beginning
	 */
	public BossModel(float x, float y, double rot, int x1, int x2, int y1,
			int y2) {
		super(x1, x2, y1, y2);
		setX(x);
		setY(y);
		setVelocity(0.1f);
		setWidth(64);
		setHeight(64);
		setRotation(rot);
		setOffset(8);
		setAlive(true);
		setHealth(100);
		addWeapon(new UnarmedModel());
		setCurrentWeapon(getWeapons().get(0));
		setAnimationPath("boss/");
	}
}
