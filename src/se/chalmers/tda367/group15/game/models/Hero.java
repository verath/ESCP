package se.chalmers.tda367.group15.game.models;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Class representing the model of a hero.
 * 
 * @author simon, Carl, tholene
 * 
 */
public class Hero extends Player implements Model {

	private double rotation;
	private boolean goingUp;
	private boolean goingDown;
	private boolean goingRight;
	private boolean goingLeft;
	private int width;
	private int height;

	/**
	 * Create a new Hero.
	 */
	public Hero() {

		// TODO fix hardcoded values..
		setX(34f);
		setY(34f);
		setVelocity(0.15f);
		this.width = 64;
		this.height = 64;

	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		Input input = cont.getInput();

		float mouseX = input.getMouseX();
		float mouseY = input.getMouseY();

		// Calculate facing depedning on where the mouse is relative
		// to the center of the hero
		rotation = Math.toDegrees(Math.atan2((height / 2 + getY() - mouseY),
				(width / 2 + getX() - mouseX)));

		goingUp = input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP);
		goingDown = input.isKeyDown(Input.KEY_S)
				|| input.isKeyDown(Input.KEY_DOWN);
		goingRight = input.isKeyDown(Input.KEY_D)
				|| input.isKeyDown(Input.KEY_RIGHT);
		goingLeft = input.isKeyDown(Input.KEY_A)
				|| input.isKeyDown(Input.KEY_LEFT);

		// Calculate move direction and move
		float speedY = (goingUp ? 1 : 0) - (goingDown ? 1 : 0);
		float speedX = (goingLeft ? 1 : 0) - (goingRight ? 1 : 0);

		if (speedY != 0 || speedX != 0) {
			double direction = Math.atan2(speedY, speedX);
			speedY = (float) (this.getVelocity() * Math.sin(direction));
			speedX = (float) (this.getVelocity() * Math.cos(direction));
		}

		this.setY(this.getY() - (delta * speedY));
		this.setX(this.getX() - (delta * speedX));

	}

	/**
	 * Returns the angle the hero is currently facing in degrees. The angle
	 * starts from the left of the hero.
	 * 
	 * @return
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * Returns whether the hero is moving or not.
	 * 
	 * @return True if hero is moving.
	 */
	public boolean isMoving() {
		int speedY = (goingUp ? 1 : 0) - (goingDown ? 1 : 0);
		int speedX = (goingLeft ? 1 : 0) - (goingRight ? 1 : 0);

		return (speedY != 0 || speedX != 0);
	}
}
