package se.chalmers.tda367.group15.game.models.weapons;

import org.newdawn.slick.Animation;

/**
 * The unarmed "weapon". The hero starts with this "weapon".
 * 
 * The hero deals 5-10 damage with his bare hands!
 * 
 * @author tholene
 * 
 */
public class Unarmed extends MeleeWeapon{
	 
	public Unarmed() {
		super("Unarmed", 5, 10, 500, true);
	}

	@Override
	public Animation getAnimation() {
		return new Animation(sortImages("unarmed"), 80, true);
	}

}
