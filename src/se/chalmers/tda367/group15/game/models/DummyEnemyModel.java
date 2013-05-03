package se.chalmers.tda367.group15.game.models;


/**
 * Simple class for representing a dummy enemy. This enemy is only intended for
 * testing.
 * 
 * @author simon
 * 
 */
public class DummyEnemyModel extends AbstractCharacterModel {

	/**
	 * Creates a new dummy enemy.
	 */
	public DummyEnemyModel() {
		setX(64f);
		setY(128f);
		setVelocity(0.1f);
		setWidth(42);
		setHeight(42);
		setRotation(180);
		setOffset(11);
		setAlive(true);
		setHealth(100);
	}
}
