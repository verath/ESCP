package se.chalmers.tda367.group15.game.models;

public class MeleeSwingModel extends AbstractProjectileModel{

	public MeleeSwingModel() {
		setX(0);
		setY(0);
		setVelocity(0.1f);
		setAlive(false);
		setWidth(2);
		setHeight(2);
	}
}
