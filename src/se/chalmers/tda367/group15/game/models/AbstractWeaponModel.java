package se.chalmers.tda367.group15.game.models;

import java.util.Random;

/**
 * An abstract definition of a weapon in the game.
 * 
 * @author tholene
 * 
 */
public abstract class AbstractWeaponModel {

	private String name;
	private int baseDamage;
	private int maxDamage;
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
	 */
	public AbstractWeaponModel(String name, int baseDamage, int maxDamage,
			int firingSpeed) {
		this.name = name.toLowerCase();
		this.baseDamage = baseDamage;
		this.maxDamage = maxDamage - baseDamage;
		this.setFiringSpeed(firingSpeed);
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
	 * @return int The firing speed of the weapon
	 */
	public int getFiringSpeed() {
		return firingSpeed;
	}

	/**
	 * Set the firing speed.
	 * 
	 * @param firingSpeed
	 *            The firing speed in ms.
	 */
	void setFiringSpeed(int firingSpeed) {
		this.firingSpeed = firingSpeed;
	}
}
