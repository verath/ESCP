package se.chalmers.tda367.group15.game.models.weapons;

/**
 * A weapon that can only be used in melee range. A melee weapon also gives the
 * hero 1/5th of the damage dealt back as health.
 * 
 * @author tholene
 * 
 */
public abstract class AbstractMeleeWeaponModel extends AbstractWeaponModel {

	/**
	 * Create a new melee weapon.
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
	public AbstractMeleeWeaponModel(String name, int baseDamage, int maxDamage,
			int firingSpeed) {
		super(name, baseDamage, maxDamage, firingSpeed);

	}
}
