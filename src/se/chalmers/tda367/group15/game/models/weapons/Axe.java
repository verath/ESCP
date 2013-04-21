package se.chalmers.tda367.group15.game.models.weapons;

<<<<<<< HEAD
import org.newdawn.slick.Animation;
=======
import org.newdawn.slick.Image;
>>>>>>> 42fec40af04fdae02b0966a814da01ceea2b2a6e

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
	public Animation getAnimation() {
		return new Animation(sortImages("axe"), 80, false);
	}

}
