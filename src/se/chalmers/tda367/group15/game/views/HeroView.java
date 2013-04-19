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
	private Image[] axe;
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
	
		unarmed = getImages("res/animation/hero/unarmed");
		axe = getImages("res/animation/hero/axe");
		
		heroMove = new Animation(unarmed, 80, true);

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
	
	private Image[] getImages(String path) {
		File folder = new File(path);
		File[] files = folder.listFiles();
		Arrays.sort(files, new FileNameSorter());
		Image[] image = new Image[files.length];

		for (int i = 0; i < files.length; i++) {
			try {
				image[i] = new Image(files[i].getPath());
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return image;
	}

}
