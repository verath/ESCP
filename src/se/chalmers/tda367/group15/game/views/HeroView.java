package se.chalmers.tda367.group15.game.views;

import org.lwjgl.Sys;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.Hero;

/**
 * Class representing the model of a hero.
 * 
 * @author ?????, Carl, tholene
 * 
 */
public class HeroView implements View {

	/**
	 * The hero model this view is representing
	 */
	private final Hero hero;

	/**
	 * Time when last render occurred. Have to keep track of this for animations
	 */
	private long lastUpdate = 0;

	/**
	 * The move animation
	 */
	private Animation heroMove;

	/**
	 * Create a new hero view.
	 * 
	 * @param heroModel
	 */
	public HeroView(final Hero hero) {
		this.hero = hero;
	}

	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		long now = getTime();
		long delta = now - lastUpdate;

		float rotation = (float) hero.getRotation();

		// We don't want to run the move animation if we're not moving
		if (!hero.isMoving()) {
			heroMove.stop();
		} else if (heroMove.isStopped()) {
			heroMove.start();
		}

		heroMove.update(delta);
		Image frame = heroMove.getCurrentFrame();
		frame.setRotation(rotation);
		frame.draw(hero.getX(), hero.getY());

		lastUpdate = getTime();
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// Set up the move animation
		Image[] movement = { new Image("res/animation/hero/unarmed/1.png"),
				new Image("res/animation/hero/unarmed/2.png"),
				new Image("res/animation/hero/unarmed/3.png"),
				new Image("res/animation/hero/unarmed/4.png"),
				new Image("res/animation/hero/unarmed/5.png"),
				new Image("res/animation/hero/unarmed/6.png"),
				new Image("res/animation/hero/unarmed/7.png"),
				new Image("res/animation/hero/unarmed/8.png"),
				new Image("res/animation/hero/unarmed/9.png"),
				new Image("res/animation/hero/unarmed/10.png"),
				new Image("res/animation/hero/unarmed/11.png"),
				new Image("res/animation/hero/unarmed/12.png") };
		heroMove = new Animation(movement, 80, false);
	}

}
