package se.chalmers.tda367.group15.game.models;

/**
 * Simple class for representing a dummy enemy. This enemy is only intended for
 * testing.
 * 
 * @author simon
 * 
 */
public class DummyEnemyModel extends AbstractMovingModel {

	/**
	 * Creates a new dummy enemy.
	 */
	public DummyEnemyModel() {
		this(64f, 128f);
	}
	
	/**
	 * Creates a new dummy enemy.
	 * @param x x postiton of DummyEnemyModel
	 * @param y y position of DummyEnemyModel
	 */
	public DummyEnemyModel(float x, float y) {
		setX(x);
		setY(y);
		setVelocity(0.1f);
		setWidth(42);
		setHeight(42);
		setRotation(180);
		setOffset(11);
	}
}
