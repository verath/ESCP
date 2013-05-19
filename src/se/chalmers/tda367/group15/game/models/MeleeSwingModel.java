package se.chalmers.tda367.group15.game.models;

/**
 * Class representing a melee swing model.
 * 
 * @author simon
 * 
 */
public class MeleeSwingModel extends AbstractProjectileModel {

	/**
	 * Create a melee swing model.
	 */
	public MeleeSwingModel() {
		setX(0);
		setY(0);
		setVelocity(0.1f);
		setAlive(false);
		setWidth(10);
		setHeight(10);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getImagePath() {
		return null;
	}
}
