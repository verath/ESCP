package se.chalmers.tda367.group15.game.models;

import java.io.File;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Hero extends Player implements AnimatedObject {
	private List<Image> movementUp;
	private List<Image> movementDown;
	private List<Image> movementLeft;
	private List<Image> movementRight;
	private Animation sprite, up, down, left, right;
	
	public Hero() {
//		setX(34f);
//		setY(34f);
//		setVel(0.1f);
//		try {
//			movementUp.add(new Image("res/animation/hero/up/g.png"));
//			movementDown.add(new Image("res/animation/hero/down/g.png"));
//			movementLeft.add(new Image("res/animation/hero/left/g.png"));
//			movementRight.add(new Image("res/animation/hero/right/g.png"));
//		} catch (SlickException e) {
//			e.printStackTrace();
//		}
//		up = new Animation((Image[])movementUp.toArray(), 300, false);
//		down = new Animation((Image[])movementDown.toArray(), 300, false);
//		left = new Animation((Image[])movementLeft.toArray(), 300, false);
//		right = new Animation((Image[])movementRight.toArray(), 300, false);
//		
//		sprite = right;

	}

	@Override
	public void update(GameContainer cont, int delta) {
		Input input = cont.getInput();
		if(input.isKeyDown(Input.KEY_UP)) {
			
		}
	}
	
	public Animation getSprite() {
		return sprite;
	}
}
