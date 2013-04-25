package se.chalmers.tda367.group15.game.views;

import java.io.File;
import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.MovingModel;
import se.chalmers.tda367.group15.game.utils.FileNameSorter;

/**
 * Class representing the view of a Dummy Enemy
 * @author simon
 *
 */
public class DummyEnemyView implements View{

	private final MovingModel model;
	private final Animation moveAnimation;
	
	/**
	 * Creates a new view for the Dummy Enemy.
	 * @param movingModel
	 */
	public DummyEnemyView(final MovingModel movingModel) {
		this.model = movingModel;
		File folder = new File("res/animation/enemy/coworker/1");
		File[] files = folder.listFiles();
		Arrays.sort(files, new FileNameSorter());
		Image[] tmp = new Image[files.length];

		for (int i = 0; i < files.length; i++) {
			try {
				tmp[i] = new Image(files[i].getPath());
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		moveAnimation = new Animation(tmp, 80, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		float rotation = (float) model.getRotation();

		// rotates the current frame
		g.rotate(model.getX() + model.getWidth() / 2,
				model.getY() + model.getHeight() / 2, rotation);
		moveAnimation.draw(model.getX(), model.getY());
		g.resetTransform();
	}

}
