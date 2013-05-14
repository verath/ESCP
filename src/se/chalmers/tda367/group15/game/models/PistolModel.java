package se.chalmers.tda367.group15.game.models;


import org.newdawn.slick.Animation;

/**
 * Placeholder class for a pistol. We might not use pistols (probably bigger
 * guns!) in the future, but this is just for show.
 * 
 * A pistol deals 25-40 damage and is a ranged weapon.
 * 
 * @author tholene
 * 
 */
public class PistolModel extends AbstractRangedWeaponModel {
	
	private static Animation animation;
	
	public PistolModel() {
		super("pistol", 25, 40, 400, false);
	}

}
