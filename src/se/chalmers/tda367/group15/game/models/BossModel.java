package se.chalmers.tda367.group15.game.models;

import se.chalmers.tda367.group15.game.models.weapons.UnarmedModel;

/**
 * Simple class for representing a Boss enemy.
 * 
 * @author simon
 * 
 */
public class BossModel extends AbstractNpcModel {

	/**
	 * Creates a new BossModel enemy at position (x, y) facing a random
	 * direction
	 * 
	 * @param x
	 *            pixels from left wall of window
	 * @param y
	 *            pixels from top of window
	 */
	private BossModel(float x, float y) {
		this(x, y, 360 * Math.random());
	}

	/**
	 * Creates a new BossModel enemy at position (x, y) facing direction rot
	 * 
	 * @param x
	 *            x position of DummyEnemyModel
	 * @param y
	 *            y position of DummyEnemyModel
	 * @param rot
	 *            angle to face in beginning
	 */
	public BossModel(float x, float y, double rot) {
		super(0, 0, 0, 0); // Boss is heavily scripted and doesn't use tiles in
							// the same way as other NPCs.
		setX(x);
		setY(y);
		setVelocity(0.1f);
		setWidth(64);
		setHeight(64);
		setRotation(rot);
		setOffset(8);
		setAlive(true);
		setHealth(500);
		addWeapon(new UnarmedModel());
		setCurrentWeapon(getWeapons().get(0));
		setAnimationPath("boss/");
	}
}
