package se.chalmers.tda367.group15.game.models;

import java.util.Random;

/**
 * Simple class for representing a coworker.
 * 
 * @author simon
 * 
 */
public class CoworkerModel extends AbstractNpcModel {

    /**
	 * Creates a new dummy enemy.
	 */
	public CoworkerModel() {
		this(64f, 128f);
	}

	public CoworkerModel(float x, float y) {
		this(x, y, 360 * Math.random());

	}

	public CoworkerModel(float x, float y, double rot) {
		this(x, y, rot, 0, 32, 0, 24);
	}

	public CoworkerModel(float x, float y, int x1, int x2, int y1, int y2) {
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
	public CoworkerModel(float x, float y, double rot, int x1, int x2, int y1,
			int y2) {
		super(x1, x2, y1, y2);
        Random randgen = new Random();
		setX(x);
		setY(y);
		setVelocity(0.1f);
		setWidth(42);
		setHeight(42);
		setRotation(rot);
		setOffset(11);
		setAlive(true);
		setHealth(100);
		addWeapon(new UnarmedModel());
		setCurrentWeapon(getWeapons().get(0));
		int random = randgen.nextInt(2) + 1;
		setAnimationPath("coworker/" + random);
	}
}
