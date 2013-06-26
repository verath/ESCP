package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.AbstractPickupModel;

public class PickupView implements View{
	
	private final AbstractPickupModel model;
	
	private Image image = null;
	
	public PickupView(AbstractPickupModel model) {
		this.model = model;
		try {
			this.image = new Image("res/images/pickups/" + model.getAnimationPath() + "sprite.png");
		} catch (SlickException e) {
			System.out.println("The image for " + model.getAnimationPath() + " can not be found!");
			e.printStackTrace();
		}
	}
	
	@Override
	public void render(GameContainer container, Graphics g) {
		if(model.isAlive()) {
			if(image!= null) {
				image.draw(model.getX(), model.getY());
			}
		}
	}
	
}
