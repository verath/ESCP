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
import se.chalmers.tda367.group15.game.models.DummyEnemyModel;

/**
 * Class representing the view of a Dummy Enemy
 * 
 * @author simon
 * 
 */
public class DummyEnemyView implements View {

	private final AbstractMovingModel model;
	private Animation animation;
	private Animation deathAnimation;
	private boolean deathAnimationRunning = false;
	private boolean deathAnimationInitiated = false;

	/**
	 * Creates a new view for the Dummy Enemy.
	 * 
	 * @param movingModel
	 */
	public DummyEnemyView(final AbstractMovingModel movingModel) {
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

			if (!model.isMoving()) {
				animation.stop();
			} else if (animation.isStopped()) {
				animation.start();
			}

			float rotation = (float) model.getRotation();

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

	public void runDeathAnimation(Animation animation) {
		if (!model.isAlive()) {
			this.deathAnimationRunning = true;
			this.deathAnimation = animation;
			this.deathAnimation.setLooping(false);
			this.animation = deathAnimation;
		}
	}

}
