package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;

public class BulletView implements View{

	private AbstractMovingModel model;
	private Image image;
	public BulletView(AbstractMovingModel model) {
		this.model = model;
		try {
			image = new Image("res/images/bullet.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		if(model.isAlive()) {
			image.draw(model.getX(), model.getY());
		}
	}

}
