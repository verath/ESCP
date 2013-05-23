package se.chalmers.tda367.group15.game.models.weapons;

/**
 * The unarmed "weapon". The hero starts with this "weapon".
 * 
 * The hero deals 5-10 damage with his bare hands!
 * 
 * @author tholene
 * 
 */
public class UnarmedModel extends AbstractMeleeWeaponModel {

	/**
	 * Create a new unarmed model.
	 */
	public UnarmedModel() {
		super("unarmed", 5, 10, 350);
	}
}
