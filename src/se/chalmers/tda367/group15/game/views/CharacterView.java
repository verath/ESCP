package se.chalmers.tda367.group15.game.views;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.models.AbstractCharacterModel;
import se.chalmers.tda367.group15.game.models.AbstractMeleeWeaponModel;
import se.chalmers.tda367.group15.game.models.AbstractWeaponModel;

public class CharacterView implements View {
	
	private AbstractCharacterModel model;
	private Map<AbstractWeaponModel, Animation> walkAnimations;
	private Map<AbstractWeaponModel, Animation> attackAnimations;
	private Animation deathAnimation;

	private Animation activeAnimation;
	private boolean attackActive;

	public CharacterView(AbstractCharacterModel model) {
		this.model = model;
		walkAnimations = new HashMap<AbstractWeaponModel, Animation>();
		attackAnimations = new HashMap<AbstractWeaponModel, Animation>();

		// store walking and attack animations for each weapon equipped by the
		// moving model
		for (AbstractWeaponModel weapon : model.getWeapons()) {
			String path = model.getAnimationPath() + "/movement/"
					+ weapon.getName() + "/";
			Animation walkAnimation = getAnimationFromPath(path);
			walkAnimations.put(weapon, walkAnimation);

			if (weapon instanceof AbstractMeleeWeaponModel) {
				path = model.getAnimationPath() + "/attack/" + weapon.getName()
						+ "/";
				Animation attackAnimation = getAnimationFromPath(path);
				attackAnimation.setLooping(false);
				attackAnimations.put(weapon, attackAnimation);
			}
		}

		// store death animation
		String path = model.getAnimationPath() + "/death/";
		deathAnimation = getAnimationFromPath(path);
		deathAnimation.setLooping(false);

		activeAnimation = walkAnimations.get(model.getCurrentWeapon());
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		float rotation = (float) model.getRotation();

		if (model.isAlive()) {
			if (attackActive) {
				if (activeAnimation.isStopped()) {
					attackActive = false;
				}

			} else {
				activeAnimation = walkAnimations.get(model.getCurrentWeapon());
				// We don't want to run the movement animation if we're not
				// moving
				if (!model.isMoving()) {
					activeAnimation.stop();
				} else if (activeAnimation.isStopped()) {
					activeAnimation.start();
				}
			}
		} else {
			activeAnimation = deathAnimation;
		}

		// rotates the current frame
		g.rotate(model.getX() + model.getWidth() / 2,
				model.getY() + model.getHeight() / 2, rotation);
		activeAnimation.draw(model.getX() - model.getOffset(), model.getY()
				- model.getOffset());
		g.resetTransform();
		if (Constants.SHOW_BOUNDS) {
			g.setColor(Color.yellow);
			Rectangle2D.Float e = model.getBounds();
			g.drawRect((int) e.getX(), (int) e.getY(), (int) e.getWidth(),
					(int) e.getHeight());
		}

		// Draw the current HP string
		g.setColor(Color.yellow);
		Font f = g.getFont();
		g.drawString("HP: " + model.getHealth(), model.getX(),
				model.getY() - f.getLineHeight());
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

						if (Constants.DEBUG) {
							System.out.println(animationImages[i]);
						}
					} catch (SlickException e) {
						e.printStackTrace();
					} catch (NullPointerException e) {
						System.out.println("res/animation/" + animationPath);
						e.printStackTrace();
					}
				}
				return new Animation(animationImages, 40, true);
			}
		}
		return null;
	}

	public void runAttackAnimation() {
		attackActive = true;
		activeAnimation = attackAnimations.get(model.getCurrentWeapon()).copy();
		activeAnimation.start();
	}

}
