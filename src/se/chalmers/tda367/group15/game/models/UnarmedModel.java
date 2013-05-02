package se.chalmers.tda367.group15.game.models;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

/**
 * The unarmed "weapon". The hero starts with this "weapon".
 * 
 * The hero deals 5-10 damage with his bare hands!
 * 
 * @author tholene
 * 
 */
public class UnarmedModel extends AbstractMeleeWeapon{
	 
	private static Animation animation;
	
	public UnarmedModel() {
		super("Unarmed", 5, 10, 250, true);
	}

	@Override
	public Animation getAnimation() {
		if (animation == null)
			initAnimation();
		return animation;
	}

	@Override
	protected void initAnimation() {
		Image[] image = sortImages("unarmed");
		animation = new Animation(image, 40, true);
	}

}
