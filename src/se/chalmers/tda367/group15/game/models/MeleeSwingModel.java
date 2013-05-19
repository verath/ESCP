package se.chalmers.tda367.group15.game.models;

public class MeleeSwingModel extends AbstractProjectileModel {

	public MeleeSwingModel() {
		setX(0);
		setY(0);
		setVelocity(0.1f);
		setAlive(false);
		setWidth(10);
		setHeight(10);
	}

	@Override
	public String getImagePath() {
		return null;
	}
}
