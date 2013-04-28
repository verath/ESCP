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

	public AxeModel() {
		super("Axe", 10, 15, 500, false);
	}

	@Override
	public Animation getAnimation() {
		if (animation == null)
			initAnimation();
		return animation;
	}

	@Override
	protected void initAnimation() {
		Image[] image = sortImages("axe");
		animation = new Animation(image, 40, true);
	}

}