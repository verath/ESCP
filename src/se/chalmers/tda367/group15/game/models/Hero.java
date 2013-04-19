package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Class representing the model of a hero.
 * 
 * @author simon, Carl, tholene
 * 
 */

public class Hero extends MovingModel {

	private double rotation;
	private boolean goingUp;
	private boolean goingDown;
	private boolean goingRight;
	private boolean goingLeft;

	/**
	 * A saved reference to the latest calculated collisionBounds for the Hero.
	 * This is to allow us to filter out our own collision bounds in the collide
	 * method.
	 */
	private List<Rectangle2D.Float> collisionBounds = new ArrayList<Rectangle2D.Float>();

	/**
	 * Create a new Hero.
	 */
	public Hero() {

		// TODO fix hardcoded values..
		setX(44f);
		setY(44f);
		setVelocity(0.15f);
		setWidth(64);
		setHeight(64);

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
	
	public void setRotation(double rotation) {
		this.rotation = rotation;
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
