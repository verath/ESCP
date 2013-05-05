package se.chalmers.tda367.group15.game.models;

import org.newdawn.slick.Animation;
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
public class AxeModel extends AbstractMeleeWeapon {

	private static Animation animation;
	private static Animation swingAnimation;

	public AxeModel() {
		super("Axe", 10, 15, 350, false);
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
	}
	
	@Override
	public Animation getSwingAnimation() {
		if(swingAnimation == null) {
			Image[] image = sortImages("heroAttack/axe");
			swingAnimation = new Animation(image, 15, true);
		}
		return swingAnimation.copy();
	}

}
