package se.chalmers.tda367.group15.game.models;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

/**
 * Placeholder class for an Axe. We might not use axes in the future, but this
 * is just for show.
 * 
 * An axe deals 20-30 damage and is a melee weapon.
 * 
 * @author tholene
 * 
 */
public class AxeModel extends AbstractMeleeWeaponModel {

	private static Animation animation;
	private static Animation swingAnimation;

	public AxeModel() {
		super("Axe", 20, 30, 350, false);
	}

	@Override
	public Animation getAnimation() {
		if (animation == null)
			initAnimation();
		return animation;
	}

	@Override
	protected void initAnimation() {
		Image[] image = sortImages("heroMovement/axe");
		animation = new Animation(image, 40, true);
		
		image = sortImages("heroAttack/axe");
		swingAnimation = new Animation(image, 15, true);
		
	}
	
	@Override
	public Animation getSwingAnimation() {
		if(swingAnimation == null) 
			initAnimation();
		return swingAnimation.copy();
	}

}
