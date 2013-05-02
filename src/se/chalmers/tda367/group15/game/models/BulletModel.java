package se.chalmers.tda367.group15.game.models;

public class BulletModel extends AbstractMovingModel{
	
	public BulletModel() {
		setX(0);
		setY(0);
		setVelocity(0.5f);
		setAlive(false);
		setWidth(6);
		setHeight(6);
	}
	 
}
