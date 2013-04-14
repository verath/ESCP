package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface View {
	public void render(GameContainer container, Graphics g) throws SlickException;

	public void init(GameContainer container) throws SlickException;
}
