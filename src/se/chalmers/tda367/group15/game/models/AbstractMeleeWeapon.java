package se.chalmers.tda367.group15.game.models;

/**
 * A weapon that can only be used in melee range.
 * 
 * @author tholene
 * 
 */
public abstract class AbstractMeleeWeapon extends AbstractWeaponModel {

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
	 *            The firingspeed of the weapon. 0 is really fast while 100 is
	 *            really slow.
	 * @param isEquipped
	 *            Is the weapon equipped?
	 */
	public AbstractMeleeWeapon(String name, int baseDamage, int maxDamage,
			int firingSpeed, boolean isEquipped) {
		super(name, baseDamage, maxDamage, firingSpeed, isEquipped);
	}

	@Override
	public void fire() {
		// TODO: implement firing. should probably trigger some
		// "swinging animation".
	}

}
