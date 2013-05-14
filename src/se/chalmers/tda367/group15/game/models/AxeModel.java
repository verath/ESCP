package se.chalmers.tda367.group15.game.models;

import org.newdawn.slick.Animation;

/**
 * Placeholder class for an Axe. We might not use axes in the future, but this
 * is just for show.
 * 
 * An axe deals 20-30 damage and is a melee weapon.
 * 
 * @author tholene
 * 
 */
public class AxeModel extends AbstractMeleeWeaponModel {

	private static Animation animation;
	private static Animation swingAnimation;

	public AxeModel() {
		super("axe", 20, 30, 350, false);
	}

}
