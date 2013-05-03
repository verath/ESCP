package se.chalmers.tda367.group15.game.models;

public abstract class AbstractProjectileModel extends AbstractMovingModel {

	/**
	 * Damage that this projectile should deal.
	 */
	private int damage;

	/**
	 * Set the amount of damage this projectile can deal.
	 * 
	 * @param damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Get the amount of damage this projectile can deal.
	 * 
	 * @return damage
	 */
	public int getDamage() {
		return damage;
	}
}
