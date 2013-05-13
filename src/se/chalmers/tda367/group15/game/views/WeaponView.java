package se.chalmers.tda367.group15.game.views;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;

public class WeaponView implements View {

	private AbstractMovingModel weaponOwner;
	private Animation animation;
	
	public WeaponView(AbstractMovingModel weaponOwner, String walkAnimationPath) {
		this.weaponOwner = weaponOwner;
		this.animation = getAnimationFromPath(walkAnimationPath);
	}
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		float rotation = (float) weaponOwner.getRotation();
		
		// We don't want to run the animation if we're not moving
		if (!weaponOwner.isMoving()) {
			animation.stop();
		} else if (animation.isStopped()) {
			animation.start();
		}

		// rotates the current frame
		g.rotate(weaponOwner.getX() + weaponOwner.getWidth() / 2,
				weaponOwner.getY() + weaponOwner.getHeight() / 2, rotation);
		animation.draw(weaponOwner.getX() - weaponOwner.getOffset(), weaponOwner.getY()
				- weaponOwner.getOffset());
		g.resetTransform();
		if (Constants.SHOW_BOUNDS) {
			g.setColor(Color.yellow);
			Rectangle2D.Float e = weaponOwner.getBounds();
			g.drawRect((int) e.getX(), (int) e.getY(), (int) e.getWidth(),
					(int) e.getHeight());
		}

		// Draw the current HP string
		g.setColor(Color.yellow);
		Font f = g.getFont();
		g.drawString("HP: " + weaponOwner.getHealth(), weaponOwner.getX(), weaponOwner.getY()
				- f.getLineHeight());

	}
	
	public Animation getAnimationFromPath(String animationPath) {
		File folder = new File("res/animation/" + animationPath);
		if (folder != null) {
			File[] files = folder.listFiles();
			if (files != null) {
				Arrays.sort(files);
				Image[] animationImages = new Image[files.length];
				for (int i = 0; i < files.length; i++) {
					try {
						animationImages[i] = new Image(files[i].getPath());
						
						if(Constants.DEBUG) {
							System.out.println(animationImages[i]);
						}
					} catch (SlickException e) {
						e.printStackTrace();
					}
				}
				return new Animation(animationImages, 40, true);
			}
		}
		return null;
	}
	
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	
	public Animation getAnimation() {
		return this.animation;
	}

}
