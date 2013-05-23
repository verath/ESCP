package se.chalmers.tda367.group15.game.models.weapons;

/**
 * A weapon that can be fired from a distance.
 * 
 * @author tholene
 * 
 */
public abstract class AbstractRangedWeaponModel extends AbstractWeaponModel {
	/**
	 * Create a new ranged weapon.
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
	public AbstractRangedWeaponModel(String name, int baseDamage,
			int maxDamage, int firingSpeed) {
		super(name, baseDamage, maxDamage, firingSpeed);

	}

}
