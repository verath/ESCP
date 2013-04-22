package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.controllers.room.RoomController;
import se.chalmers.tda367.group15.game.models.Hero;
import se.chalmers.tda367.group15.game.views.HeroView;

public class HeroController implements MovingModelController {
	private HeroView view;
	private Hero model;

	/**
	 * A room controller set by the constructor of the class. The room
	 * controller is used to get collision bounds from the current room.
	 */
	private RoomController roomController;

	/**
	 * Create a new controller for the hero.
	 * 
	 * @param roomController
	 */
	public HeroController(RoomController roomController) {
		model = new Hero();
		view = new HeroView(model);
		this.roomController = roomController;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, int delta) {
		Input input = container.getInput();
		float mouseX = input.getMouseX();
		float mouseY = input.getMouseY();

		// Calculate facing depending on where the mouse is relative
		// to the center of the hero
		((Hero) model).setRotation(Math.toDegrees(Math.atan2((model.getHeight()
				/ 2 + model.getY() - mouseY),
				(model.getWidth() / 2 + model.getX() - mouseX))));

		boolean goingUp = input.isKeyDown(Input.KEY_W)
				|| input.isKeyDown(Input.KEY_UP);
		boolean goingDown = input.isKeyDown(Input.KEY_S)
				|| input.isKeyDown(Input.KEY_DOWN);
		boolean goingRight = input.isKeyDown(Input.KEY_D)
				|| input.isKeyDown(Input.KEY_RIGHT);
		boolean goingLeft = input.isKeyDown(Input.KEY_A)
				|| input.isKeyDown(Input.KEY_LEFT);

		// Calculate move direction and move
		float speedY = (goingUp ? 1 : 0) - (goingDown ? 1 : 0);
		float speedX = (goingLeft ? 1 : 0) - (goingRight ? 1 : 0);

		if (speedY != 0 || speedX != 0) {
			double direction = Math.atan2(speedY, speedX);
			speedY = (float) (model.getVelocity() * Math.sin(direction));
			speedX = (float) (model.getVelocity() * Math.cos(direction));
		}

		float newX = model.getX() - (delta * speedX);
		float newY = model.getY() - (delta * speedY);

		if (!isCollision(newX, model.getY()))
			model.setX(newX);
		if (!isCollision(model.getX(), newY))
			model.setY(newY);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		view.render(container, g);
	}

	public boolean isCollision(float newX, float newY) {
		List<Rectangle2D.Float> staticBounds = roomController.getCurrentRoom()
				.getCollisionBounds();
		// TODO collisionbounds for the hero is 2px smaller than it should. Just
		// as a quickfix for moving through doors..
		Rectangle2D.Float hero = new Rectangle2D.Float(newX + 2, newY + 2,
				model.getWidth() - 2, model.getHeight() - 2);
		for (Rectangle2D.Float bound : staticBounds) {
			if (hero.intersects(bound)) {
				return true;
			}
		}

		return false;
	}
}
