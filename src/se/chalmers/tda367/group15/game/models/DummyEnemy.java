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
		setX(64f);
		setY(128f);
		setVelocity(0.1f);
		setWidth(64);
		setHeight(64);
		setRotation(180);
	}

}
