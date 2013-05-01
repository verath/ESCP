package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.HUDModel;

public class HUDView implements View{
	
	private final HUDModel hudModel;
	
	public HUDView(HUDModel  hudModel) {
		this.hudModel = hudModel;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.drawString("Health: " + hudModel.getHealth(), 20, 100);
	}

}
