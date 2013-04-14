package se.chalmers.tda367.group15.game.views;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.Hero;

/**
 * Class representing the model of a hero.
 * 
 * @author ?????, Carl
 * 
 */
public class HeroView implements View {

	private final Hero hero;

	private Animation heroMove;
	private AffineTransformOp imgTransform;
	
	/**
	 * Create a new hero view.
	 * @param heroModel 
	 */
	public HeroView(final Hero hero) {
		this.hero = hero;
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		
		
		float rotation = (float) hero.getDirection();
		
		if(heroMove != null) {
			heroMove.getCurrentFrame().setRotation( rotation );
			heroMove.draw(hero.getX(), hero.getY());
			
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		
		Image[] movement = new Image[1];
		movement[0] = new Image("res/animation/hero/left/g.png");
		heroMove = new Animation(movement, 300, false);
	}

}
