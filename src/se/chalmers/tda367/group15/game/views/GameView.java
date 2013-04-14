package se.chalmers.tda367.group15.game.views;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * A container view for all views associated with the game.
 * 
 * @author Peter
 * 
 */
public class GameView implements View {
	private List<View> views = new ArrayList<View>();

	public GameView() {
	}

	public void render(GameContainer container, Graphics g) throws SlickException {
		for (View v : views) {
			v.render(container, g);
		}
	}

	public void init(GameContainer container) throws SlickException {
		for (View v : views) {
			v.init(container);
		}
	}
	
	public boolean addView(View v) {
		return views.add(v);
	}

	public boolean removeView(View v) {
		return views.remove(v);
	}
}
