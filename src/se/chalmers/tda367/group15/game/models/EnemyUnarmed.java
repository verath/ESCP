package se.chalmers.tda367.group15.game.models;


/**
 * The unarmed "weapon". The hero starts with this "weapon".
 * 
 * The hero deals 5-10 damage with his bare hands!
 * 
 * @author tholene
 * 
 */
public class EnemyUnarmed extends AbstractMeleeWeaponModel {

	public EnemyUnarmed() {
		super("Unarmed", 5, 10, 350, true);
	}

}
