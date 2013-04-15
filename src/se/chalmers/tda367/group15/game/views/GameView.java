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
	/**
	 * List of views that should be forwarded the render and init.
	 */
	private List<View> views = new ArrayList<View>();

	/**
	 * Creates a new GameView
	 */
	public GameView() {
	}
	
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		for (View v : views) {
			v.render(container, g);
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		for (View v : views) {
			v.init(container);
		}
	}

	/**
	 * Adds a view to the GameView, this will make it receiving render and init.
	 * 
	 * @param view
	 */
	public void addView(View view) {
		views.add(view);
	}

	/**
	 * Removes a view from the GameView, this will stop it from receiving render
	 * and init.
	 * 
	 * @param view
	 */
	public void removeView(View view) {
		views.remove(view);
	}
}
