package se.chalmers.tda367.group15.game.models;

public class BulletModel extends AbstractMovingModel{
	
	public BulletModel(float x, float y) {
		setX(x);
		setY(y);
		setVelocity(0.5f);
	}
	 
}
