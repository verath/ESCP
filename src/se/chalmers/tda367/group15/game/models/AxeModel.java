package se.chalmers.tda367.group15.game.models;

/**
 * An axe to be wielded by the hero!
 * 
 * An axe deals 50-110 damage and is a melee weapon.
 * 
 * @author tholene
 * 
 */
public class AxeModel extends AbstractMeleeWeaponModel {

	public AxeModel() {
		super("axe", 50, 110, 350);
	}

}
