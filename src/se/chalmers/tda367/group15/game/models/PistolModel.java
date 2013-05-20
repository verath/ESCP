package se.chalmers.tda367.group15.game.models;

/**
 * A pistol to be wielded by the hero!
 * 
 * A pistol deals 25-40 damage and is a ranged weapon.
 * 
 * @author tholene
 * 
 */
public class PistolModel extends AbstractRangedWeaponModel {

	public PistolModel() {
		super("pistol", 25, 40, 400);
	}

}
