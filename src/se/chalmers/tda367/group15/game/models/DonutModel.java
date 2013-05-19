package se.chalmers.tda367.group15.game.models;

public class DonutModel extends AbstractProjectileModel {

	public DonutModel() {
		setX(0);
		setY(0);
		setVelocity(0.4f);
		setAlive(false);
		setWidth(10);
		setHeight(10);
	}

	@Override
	public String getImagePath() {
		return "res/images/donut.png";
	}

}