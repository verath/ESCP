package se.chalmers.tda367.group15.game.models;

/**
 * Class representing a bullet model.
 * 
 * @author simon
 * 
 */
public class BulletModel extends AbstractProjectileModel {

	/**
	 * Create a new bullet model.
	 */
	public BulletModel() {
		setX(0);
		setY(0);
		setVelocity(0.4f);
		setAlive(false);
		setWidth(6);
		setHeight(6);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getImagePath() {
		return "res/images/bullet.png";
	}

}
