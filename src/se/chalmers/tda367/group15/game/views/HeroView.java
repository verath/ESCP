package se.chalmers.tda367.group15.game.views;

import java.awt.geom.Rectangle2D;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.models.AbstractCharacterModel;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;

/**
 * Class representing the view of a hero.
 * 
 * @author Simon, Carl, tholene
 * 
 */
public class HeroView implements View {

	private boolean swingAnimationRunning = false;

	/**
	 * The hero model this view is representing
	 */
	private final AbstractCharacterModel model;

	/**
	 * The move animation
	 */
	private Animation animation;
	private Animation swingAnimation;

	/**
	 * Create a new hero view.
	 * 
	 * @param heroModel
	 */
	public HeroView(final AbstractMovingModel hero) {
		this.model = (AbstractCharacterModel) hero;
		animation = this.model.getCurrentWeapon().getAnimation();
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
				if(swingAnimation.isStopped()){
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

			Font f = g.getFont();
			g.drawString("HP: " + model.getHealth(), model.getX(), model.getY()
					- f.getLineHeight());

		}
	}

	public void runAnimation(Animation animation) {
		if(model.isAlive()) {
			swingAnimationRunning = true;
			this.swingAnimation = animation;
			this.swingAnimation.setLooping(false);
		}
		
	}

}
