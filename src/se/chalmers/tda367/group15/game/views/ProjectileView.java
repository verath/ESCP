package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.AbstractProjectileModel;

public class ProjectileView implements View {

	private AbstractMovingModel model;
	private Image image;

	public ProjectileView(AbstractProjectileModel model) {
		this.model = model;
		try {
			image = new Image(model.getImagePath());
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		if (model.isAlive()) {
			double rotation = model.getRotation();
			g.rotate(model.getX() + model.getWidth() / 2,
					model.getY() + model.getHeight() / 2, (float) rotation - 90);
			image.draw(model.getX(), model.getY());
			g.resetTransform();
		}
	}

}
