package se.chalmers.tda367.group15.game.models;

public class MeleeSwingModel extends AbstractProjectileModel{
	
	private int distance = 0;
	
	public MeleeSwingModel() {
		setX(0);
		setY(0);
		setVelocity(1f);
		setAlive(false);
		setWidth(21);
		setHeight(21);
	}

	
	public int getDistance() {
		return distance;	
	}
	
	public void incDistance() {
		distance ++;
	}
}
