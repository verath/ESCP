package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.Hero;

public class HeroView implements View {

	private final Hero hero;

	private Animation up, down, left, right;

	/**
	 * Create a new hero view.
	 * @param heroModel 
	 */
	public HeroView(final Hero hero) {
		this.hero = hero;
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		Animation a = null;
		switch(hero.getCurrentFacing()) {
		case Down:
			a = down;
			break;
		case Up:
			a = up;
			break;
		case Left:
			a = left;
			break;
		case Right:
			a = right;
			break;
		}
		
		a.draw(hero.getX(), hero.getY());
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		Image[] movementUp = new Image[1];
		Image[] movementDown = new Image[1];
		Image[] movementLeft = new Image[1];
		Image[] movementRight = new Image[1];
		movementUp[0] = new Image("res/animation/hero/up/g.png");
		movementDown[0] = new Image("res/animation/hero/down/g.png");
		movementLeft[0] = new Image("res/animation/hero/left/g.png");
		movementRight[0] = new Image("res/animation/hero/right/g.png");
		
		up = new Animation(movementUp, 300, false);
		down = new Animation(movementDown, 300, false);
		left = new Animation(movementLeft, 300, false);
		right = new Animation(movementRight, 300, false);
	}

}
