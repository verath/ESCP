package se.chalmers.tda367.group15.game.models.weapons;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

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
	
	private static Animation animation;
	
	public Pistol() {
		super("Pistol", 10, 20, 500, false);
	}
	
	@Override
	public Animation getAnimation() {
		if (animation == null)
			initAnimation();
		return animation;
	}

	@Override
	protected void initAnimation() {
		Image[] image = sortImages("pistol");
		animation = new Animation(image, 80, true);
	}


}
