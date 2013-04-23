package se.chalmers.tda367.group15.game.models;

/**
 * Simple class for representing a dummy enemy. This enemy is only intended for
 * testing.
 * 
 * @author simon
 * 
 */
public class DummyEnemy extends MovingModel {

	/**
	 * Creates a new dummy enemy.
	 */
	public DummyEnemy() {
		setX(32f);
		setY(128f);
		setVelocity(0.1f);
		setWidth(32);
		setHeight(32);
		setRotation(180);
	}

}
