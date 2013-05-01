package se.chalmers.tda367.group15.game.event;

public class HeroDamagedEvent extends Event {
	private final int damage;
	private final int currentHealth;
	
	public HeroDamagedEvent(int damage, int currentHealth) {
		this.damage = damage;
		this.currentHealth = currentHealth;
	}

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getDamage() {
		return damage;
	}
}
