package se.chalmers.tda367.group15.game.models;

public class BulletModel extends AbstractProjectileModel {

	public BulletModel() {
		setX(0);
		setY(0);
		setVelocity(0.4f);
		setAlive(false);
		setWidth(6);
		setHeight(6);
	}

	@Override
	public String getImagePath() {
		return "res/images/bullet.png";
	}

}
