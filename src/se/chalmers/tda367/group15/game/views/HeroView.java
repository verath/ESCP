package se.chalmers.tda367.group15.game.views;

import java.io.File;
import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.Hero;
import se.chalmers.tda367.group15.game.utils.FileNameSorter;

/**
 * Class representing the model of a hero.
 * 
 * @author ?????, Carl, tholene
 * 
 */
public class HeroView implements View {

	private Image[] unarmed;
	/**
	 * The hero model this view is representing
	 */
	private final Hero hero;

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

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		float rotation = (float) hero.getRotation();

		// We don't want to run the animation if we're not moving
		if (!hero.isMoving()) {
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

	@Override
	public void init(GameContainer container) throws SlickException {
		// Set up the move animation
		File folder = new File("res/animation/hero/unarmed");
		File[] files = folder.listFiles();
		Arrays.sort(files, new FileNameSorter());
		unarmed = new Image[files.length];
		
		for(int i = 0; i < files.length; i++) {
			System.out.println(files[i].getPath());
			unarmed[i] = new Image(files[i].getPath());
		}
		heroMove = new Animation(unarmed, 80, true);
	}

}
