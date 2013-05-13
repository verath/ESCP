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
import se.chalmers.tda367.group15.game.models.AbstractCharacterModel;
import se.chalmers.tda367.group15.game.models.DummyEnemyModel;

/**
 * Class representing the view of a Dummy Enemy
 * 
 * @author simon
 * 
 */
public class DummyEnemyView implements View {

	private final AbstractCharacterModel model;

	private Animation animation;
	private Animation deathAnimation;
	private Animation swingAnimation;

	private boolean deathAnimationRunning = false;
	private boolean deathAnimationInitiated = false;
	private boolean swingAnimationRunning = false;

	/**
	 * Creates a new view for the Dummy Enemy.
	 * 
	 * @param movingModel
	 */
	public DummyEnemyView(final AbstractCharacterModel movingModel) {
		this.model = movingModel;
		File folder = new File("res/animation/enemy/coworker/1");
		File[] files = folder.listFiles();
		Arrays.sort(files);
		Image[] tmp = new Image[files.length];

		for (int i = 0; i < files.length; i++) {
			try {
				tmp[i] = new Image(files[i].getPath());
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		animation = new Animation(tmp, 40, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		if (model.isAlive()) {
			float rotation = (float) model.getRotation();

			if (!swingAnimationRunning) {
				animation = model.getCurrentWeapon().getAnimation();

				// We don't want to run the animation if we're not moving

				if (!model.isMoving()) {
					animation.stop();
				} else if (animation.isStopped()) {
					animation.start();
				}

			} else {
				animation = swingAnimation;
				if (swingAnimation.isStopped()) {
					swingAnimationRunning = false;
				}
			}

			// rotates the current frame
			g.rotate(model.getX() + model.getWidth() / 2,
					model.getY() + model.getHeight() / 2, rotation);
			animation.draw(model.getX() - model.getOffset(), model.getY()
					- model.getOffset());
			g.resetTransform();
			if (Constants.SHOW_BOUNDS) {
				g.setColor(Color.yellow);
				Rectangle2D.Float e = model.getBounds();
				g.drawRect((int) e.getX(), (int) e.getY(), (int) e.getWidth(),
						(int) e.getHeight());
			}

			// Draw the current HP string
			Font f = g.getFont();
			g.setColor(Color.yellow);
			g.drawString("HP: " + model.getHealth(), model.getX(), model.getY()
					- f.getLineHeight());
		} else if (!model.isAlive()) {
			if (!deathAnimationInitiated) {
				DummyEnemyModel tmp = (DummyEnemyModel) model;
				runDeathAnimation(tmp.getDeathAnimation());
				deathAnimationInitiated = true;
			}
			float rotation = (float) model.getRotation();
			g.rotate(model.getX() + model.getWidth() / 2,
					model.getY() + model.getHeight() / 2, rotation);

			if (deathAnimationRunning) {
				animation.draw(model.getX() - model.getOffset(), model.getY()
						- model.getOffset());
				g.resetTransform();

				deathAnimationRunning = !(deathAnimation.isStopped());

			} else {

				Image dead = deathAnimation.getImage(deathAnimation
						.getFrameCount() - 1);
				dead.draw(model.getX() - model.getOffset(), model.getY()
						- model.getOffset());

				g.resetTransform();
			}
		}

	}
	
	/**
	 * Call this method when you want to run the animation for a dying enemy 
	 * @param animation the animation to be run
	 */
	public void runDeathAnimation(Animation animation) {
		if (!model.isAlive()) {
			this.deathAnimationRunning = true;
			this.deathAnimation = animation;
			this.deathAnimation.setLooping(false);
			this.animation = deathAnimation;
		}
	}
	

	/**
	 * Calling this method runs the swinging animation  
	 * @param animation the animation to be run
	 */
	public void runSwingAnimation(Animation animation) {
		if (model.isAlive()) {
			swingAnimationRunning = true;
			this.swingAnimation = animation;
			this.swingAnimation.setLooping(false);
		}

	}

}
