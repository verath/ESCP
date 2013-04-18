package se.chalmers.tda367.group15.game.models.weapons;

/**
 * Placeholder class for a pistol. We might not use pistols (probably bigger
 * guns!) in the future, but this is just for show.
 * 
 * A pistol deals 10-20 damage and is a ranged weapon.
 * 
 * @author tholene
 * 
 */
public class Pistol extends RangedWeapon {

	public Pistol() {
		super("Pistol", 10, 20, 500, false);
	}

}
