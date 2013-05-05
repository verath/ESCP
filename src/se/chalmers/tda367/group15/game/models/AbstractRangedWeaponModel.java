package se.chalmers.tda367.group15.game.models;

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
	 *            The firingspeed of the weapon. 0 is really fast while 100 is
	 *            really slow.
	 * @param isEquipped
	 *            Is the weapon equipped?
	 */
	public AbstractRangedWeaponModel(String name, int baseDamage,
			int maxDamage, int firingSpeed, boolean isEquipped) {
		super(name, baseDamage, maxDamage, firingSpeed, isEquipped);
	}

}
