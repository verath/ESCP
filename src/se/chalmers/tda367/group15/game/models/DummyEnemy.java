package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class DummyEnemy extends MovingModel {

	/**
	 * A saved reference to the latest calculated collisionBounds for the Hero.
	 * This is to allow us to filter out our own collision bounds in the collide
	 * method.
	 */
	private List<Rectangle2D.Float> collisionBounds = new ArrayList<Rectangle2D.Float>();
	
	private float oldX, oldY;
	private int width, height;
	

	public DummyEnemy() {
		setX(32f);
		setY(128f);
		setVelocity(0.1f);
		this.width = 32;
		this.height = 32;
	}
	@Override
	public void collide(List<Float> collisionBounds) {
		if (isCollision(collisionBounds)) {
			setX(oldX);
			setY(oldY);
		}

	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
	}

	@Override
	public List<Float> getCollisionBounds() {
		return collisionBounds;
	}

	/**
	 * Calculates the collision bounds for hero
	 */
	private void calculateCollisionBounds() {
		collisionBounds.clear();
		collisionBounds.add(new Rectangle2D.Float(getX(), getY(), width, height));
	}

}
