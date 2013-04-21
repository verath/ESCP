package se.chalmers.tda367.group15.game.models.weapons;

import org.newdawn.slick.Image;

/**
 * Placeholder class for an Axe. We might not use axes in the future, but this
 * is just for show.
 * 
 * An axe deals 10-15 damage and is a melee weapon.
 * 
 * @author tholene
 * 
 */
public class Axe extends MeleeWeapon {

	public Axe() {
		super("Axe", 10, 15, 500, false);
	}

	@Override
	public Image[] getImages() {
		return sortImages("axe");
	}

}
