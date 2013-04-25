package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.MovingModel;

/**
 * Class representing the view of a hero.
 * 
 * @author Simon, Carl, tholene
 * 
 */
public class HeroView implements View {

	private Animation unarmed;
	
	/**
	 * The hero model this view is representing
	 */
	private final MovingModel hero;
	
	/**
	 * The move animation
	 */
	private Animation heroMove;

	/**
	 * Create a new hero view.
	 * 
	 * @param heroModel
	 */
	public HeroView(final MovingModel hero) {
		this.hero = hero;
		heroMove = hero.getCurrentWeapon().getAnimation();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		float rotation = (float) hero.getRotation();

		// We don't want to run the animation if we're not moving
		if (!hero.isMoving()) {
			System.out.println("NOT MOVING");
			heroMove.stop();
		} else if (heroMove.isStopped()) {
			heroMove.start();
		}
		// rotates the current frame
		g.rotate(hero.getX() + heroMove.getWidth() / 2,
				hero.getY() + heroMove.getHeight() / 2, rotation);
		heroMove.draw(hero.getX(), hero.getY());
		g.resetTransform();

	}

}
