package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.*;
import se.chalmers.tda367.group15.game.models.AbstractCharacterModel;
import se.chalmers.tda367.group15.game.models.AbstractMeleeWeaponModel;
import se.chalmers.tda367.group15.game.models.AbstractWeaponModel;
import se.chalmers.tda367.group15.game.settings.Constants;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a view for a character model.
 * 
 * @author simon
 * 
 */
public class CharacterView implements View {

	/**
	 * The character model that this view is rendering.
	 */
	private final AbstractCharacterModel model;

	/**
	 * Map of weapons and it's corresponding walk animations.
	 */
	private final Map<AbstractWeaponModel, Animation> walkAnimations;

	/**
	 * Map of weapons and it's corresponding attack animations.
	 */
	private final Map<AbstractWeaponModel, Animation> attackAnimations;

	/**
	 * The death animation.
	 */
	private final Animation deathAnimation;

	/**
	 * Variable for holding the active animation running.
	 */
	private Animation activeAnimation;

	/**
	 * Variable for checking whether an attack animation is running or not.
	 */
	private boolean attackActive;

	/**
	 * Creates a new character view for the specified model. Creates and stores
	 * animations for all the model's weapons.
	 * 
	 * @param model
	 *            The character model this view is rendering.
	 */
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
				attackAnimation.setSpeed(2.5f);
				attackAnimation.setLooping(false);
				attackAnimations.put(weapon, attackAnimation);
			}
		}

		// store death animation
		String path = model.getAnimationPath() + "/death/";
		deathAnimation = getAnimationFromPath(path);
		// The enemies will bleed slowly when they die
		if (path.equals("coworker/1/death/")
				|| path.equals("coworker/2/death/")) {
			deathAnimation.setDuration(7, 2000);
			deathAnimation.setDuration(8, 2000);
			deathAnimation.setDuration(9, 2000);
			deathAnimation.setDuration(10, 2000);
			deathAnimation.setDuration(11, 2000);
		}
		deathAnimation.setLooping(false);

		// set initial animation
		activeAnimation = walkAnimations.get(model.getCurrentWeapon());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g) {

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
		if (model.isAlive()) {
			g.setColor(Color.yellow);
			Font f = g.getFont();
			g.drawString("HP: " + model.getHealth(), model.getX(), model.getY()
					- f.getLineHeight());
		}
	}

	/**
	 * Method for getting an Animation object from a specified path in the
	 * filesystem. The method assumes that the root folder to the animation
	 * files is 'res/animation/specifiedanimationpath'.
	 * 
	 * @param animationPath
	 *            The path to the animation files
	 * @return An animation object
	 */
	Animation getAnimationFromPath(String animationPath) {
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

	/**
	 * Method to call if attack animation for the current weapon should be run.
	 */
	public void runAttackAnimation() {
		attackActive = true;
		activeAnimation = attackAnimations.get(model.getCurrentWeapon()).copy();
		activeAnimation.start();
	}

}
