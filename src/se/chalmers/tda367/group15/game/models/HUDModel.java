package se.chalmers.tda367.group15.game.models;

public class HUDModel {
	private int health;
	
	public HUDModel(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
