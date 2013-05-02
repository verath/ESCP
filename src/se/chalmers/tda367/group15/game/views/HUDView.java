package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.HeroModel;

public class HUDView implements View {

	private final HeroModel heroModel;

	public HUDView(HeroModel heroModel) {
		this.heroModel = heroModel;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		Font f = g.getFont();
		
		g.drawString("HP: " + heroModel.getHealth(), heroModel.getX(),
				heroModel.getY() - f.getLineHeight());
	}

}
