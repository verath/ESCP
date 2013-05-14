package se.chalmers.tda367.group15.game.models;

import org.newdawn.slick.Animation;

/**
 * The unarmed "weapon". The hero starts with this "weapon".
 * 
 * The hero deals 5-10 damage with his bare hands!
 * 
 * @author tholene
 * 
 */
public class EnemyUnarmed extends AbstractMeleeWeaponModel{
	 
	private static Animation animation;
	
	private static Animation swingAnimation;
	
	public EnemyUnarmed() {
		super("Unarmed", 5, 10, 350, true);
	}

}
