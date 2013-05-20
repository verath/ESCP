package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.AbstractProjectileModel;

/**
 * Class representing a view for a projectile.
 * 
 * @author simon
 * 
 */
public class ProjectileView implements View {

	/**
	 * The projectile model.
	 */
	private final AbstractMovingModel model;

	/**
	 * The image for the projectile model.
	 */
	private Image image;

	/**
	 * Create a new projectile view.
	 * 
	 * @param model
	 *            The model that this view should render.
	 */
	public ProjectileView(AbstractProjectileModel model) {
		this.model = model;
		try {
			image = new Image(model.getImagePath());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g) {
		if (model.isAlive()) {
			double rotation = model.getRotation();
			g.rotate(model.getX() + model.getWidth() / 2,
					model.getY() + model.getHeight() / 2, (float) rotation - 90);
			image.draw(model.getX(), model.getY());
			g.resetTransform();
		}
	}

}
