package se.chalmers.tda367.group15.game.models;

import java.util.Random;

/**
 * An abstract definition of a weapon in the game. Every weapon should be able
 * to fire.
 * 
 * @author tholene
 * 
 */
public abstract class AbstractWeaponModel {

	private final String name;
	private boolean isEquipped;
	private final int baseDamage;
	private final int maxDamage;
	private int firingSpeed;

	private final Random randomGenerator = new Random();

	/**
	 * Create a new weapon.
	 * 
	 * @param name
	 *            The name of the weapon.
	 * @param baseDamage
	 *            The base damage of the weapon.
	 * @param maxDamage
	 *            The maximum damage of the weapon.
	 * @param firingSpeed
	 *            The amount of time (in ms) that will pass between every shot
	 *            while firing.
	 * @param isEquipped
	 *            Is the weapon equipped or not?
	 */
	public AbstractWeaponModel(String name, int baseDamage, int maxDamage,
			int firingSpeed, boolean isEquipped) {
		this.name = name.toLowerCase();
		this.baseDamage = baseDamage;
		this.maxDamage = maxDamage - baseDamage;
		this.setFiringSpeed(firingSpeed);
		this.isEquipped = isEquipped;
	}

	/**
	 * Equip the weapon.
	 */
	public void equip() {
		isEquipped = true;
	}

	/**
	 * Unequip the weapon.
	 */
	public void unequip() {
		isEquipped = false;
	}

	/**
	 * Get the name of the weapon.
	 * 
	 * @return the name of the weapon.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Check whether the weapon is equipped or not.
	 * 
	 * @return true if the weapon is currently equipped, else false.
	 */
	public boolean isEquipped() {
		return isEquipped;
	}

	/**
	 * Get the amount of damage that the weapon deals upon firing.
	 * 
	 * @return the damage that the weapon will deal.
	 */
	public int getDamage() {
		return baseDamage + randomGenerator.nextInt(maxDamage);
	}

	/**
	 * Get the firing speed.
	 * 
	 * @return int representing the firing speed.
	 */
	public int getFiringSpeed() {
		return firingSpeed;
	}

	/**
	 * Set the firing speed.
	 * 
	 * @param firingSpeed
	 *            An int representing the firing speed.
	 */
    void setFiringSpeed(int firingSpeed) {
		this.firingSpeed = firingSpeed;
	}
}
