package se.chalmers.tda367.group15.game.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * Class representing the model of a hero.
 * @author simon
 *
 */
public class Hero extends Player implements AnimatedModel {
	private Image[] movementUp = new Image[1];
	private Image[] movementDown = new Image[1];
	private Image[] movementLeft = new Image[1];
	private Image[] movementRight = new Image[1];
	private Animation sprite, up, down, left, right;
	
	/**
	 * Create a new Hero.
	 */
	public Hero() {
		
		//TODO fix hardcoded values..
		setX(34f);
		setY(34f);
		setVelocity(0.5f);
		try {
			movementUp[0] = new Image("res/animation/hero/up/g.png");
			movementDown[0] = new Image("res/animation/hero/down/g.png");
			movementLeft[0] = new Image("res/animation/hero/left/g.png");
			movementRight[0] = new Image("res/animation/hero/right/g.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		up = new Animation(movementUp, 300, false);
		down = new Animation(movementDown, 300, false);
		left = new Animation(movementLeft, 300, false);
		right = new Animation(movementRight, 300, false);
		
		sprite = right;

	}

	@Override
	public void update(GameContainer cont, int delta) {
		Input input = cont.getInput();
		if(input.isKeyDown(Input.KEY_UP)) {
			sprite = up;
			this.setY(this.getY()-delta * this.getVelocity());
		}else if(input.isKeyDown(Input.KEY_DOWN)) {
			sprite = down;
			this.setY(this.getY()+delta * this.getVelocity());
		}else if(input.isKeyDown(Input.KEY_LEFT)) {
			sprite = left;
			this.setX(this.getX()-delta * this.getVelocity());
		}else if(input.isKeyDown(Input.KEY_RIGHT)) {
			sprite = right;
			this.setX(this.getX()+delta * this.getVelocity());
		}
		sprite.update(delta);
	}
	
	public Animation getCurrentSprite() {
		return sprite;
	}
}
