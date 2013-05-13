package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;

public class WeaponSwingView implements View{

	private AbstractMovingModel weaponOwner;
	private Animation walkAnimation;
	private Animation swingAnimation;
	private boolean swingAnimationActive;
	public WeaponSwingView(AbstractMovingModel weaponOwner, String walkAnimationPath, String swingAnimationPath) {
		this.weaponOwner = weaponOwner;
		walkAnimation = getAnimationFromPath(walkAnimationPath);
		swingAnimation = getAnimationFromPath(swingAnimationPath);
		swingAnimation.setLooping(false);
	}
	
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		if(swingAnimationActive) {
			if(swingAnimation.isStopped()) {
				setAnimation(walkAnimation);
				swingAnimationActive = false;
			}
		}else{
			// We don't want to run the animation if we're not moving
			if (!weaponOwner.isMoving()) {
				animation.stop();
			} else if (animation.isStopped()) {
				animation.start();
			}
		}
	}
	
	public void animateSwing() {
		swingAnimationActive = true;
		setAnimation(swingAnimation);
		swingAnimation.start();
	}

}
