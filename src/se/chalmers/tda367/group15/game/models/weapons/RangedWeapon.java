package se.chalmers.tda367.group15.game.models.weapons;

/**
 * A weapon that can be fired from a distance.
 * 
 * @author tholene
 * 
 */
public class RangedWeapon extends Weapon {

	private int ammo;

	/**
	 * Create a new ranged weapon.
	 * 
	 * @param name
	 *            The name of the weapon.
	 * @param baseDamage
	 *            The base damage of the weapon.
	 * @param maxDamage
	 *            The maximum damage of the weapon.
	 * @param isEquipped
	 *            Is the weapon equipped?
	 */
	public RangedWeapon(String name, int baseDamage, int maxDamage,
			int firingSpeed, boolean isEquipped) {
		super(name, baseDamage, maxDamage, firingSpeed, isEquipped);
		// TODO: placeholder for ammo. will perhaps implement magazines and
		// reloading later.
		this.ammo = 100;
	}

	@Override
	public void fire() {
		// TODO: implement firing. will probably have to make bullets appear
		// somehow aswell. firingspeed is important here!
	}

}
