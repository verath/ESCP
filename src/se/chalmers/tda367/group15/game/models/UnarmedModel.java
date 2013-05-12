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
public class UnarmedModel extends AbstractMeleeWeaponModel{
	 
	private static Animation animation;
	
	private static Animation swingAnimation;
	
	public UnarmedModel() {
		super("Unarmed", 5, 10, 350, true);
	}

	@Override
	public Animation getAnimation() {
		if (animation == null)
			initAnimation();
		return animation;
	}

	@Override
	protected void initAnimation() {
		Image[] image = sortImages("heroMovement/unarmed");
		animation = new Animation(image, 40, true);
		
		image = sortImages("heroAttack/unarmed");
		swingAnimation = new Animation(image, 25, true);
	}
	
	@Override
	public Animation getSwingAnimation(){
		if(swingAnimation == null) 
			initAnimation();
		return swingAnimation.copy();
	}

}
