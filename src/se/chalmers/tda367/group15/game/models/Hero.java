package se.chalmers.tda367.group15.game.models;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

/**
 * Class representing the model of a hero.
 * 
 * @author simon
 * 
 */
public class Hero extends Player implements Model {
	
	public enum Facing {
		Up, Left, Right, Down;
	}
	
	private Facing facing = Facing.Right;
	
	/**
	 * Create a new Hero.
	 */
	public Hero() {

		// TODO fix hardcoded values..
		setX(34f);
		setY(34f);
		setVelocity(0.5f);

	}

	@Override
	public void update(GameContainer cont, int delta) {
		Input input = cont.getInput();
		if (input.isKeyDown(Input.KEY_UP)) {
			facing = Facing.Up;
			this.setY(this.getY() - delta * this.getVelocity());
		} else if (input.isKeyDown(Input.KEY_DOWN)) {
			facing = Facing.Down;
			this.setY(this.getY() + delta * this.getVelocity());
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			facing = Facing.Left;
			this.setX(this.getX() - delta * this.getVelocity());
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			facing = Facing.Right;
			this.setX(this.getX() + delta * this.getVelocity());
		}
	}

	public Facing getCurrentFacing() {
		return this.facing;
	}
}
