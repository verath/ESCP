package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.Hero;
import se.chalmers.tda367.group15.game.models.MovingModel;
import se.chalmers.tda367.group15.game.views.HeroView;

public class HeroController implements MovingModelController {
	private HeroView view;
	private Hero model;

	private boolean goingUp;
	private boolean goingDown;
	private boolean goingLeft;
	private boolean goingRight;
	private float oldX, oldY;

	/**
	 * Create a new controller for the hero.
	 * 
	 * @param roomController
	 */
	public HeroController() {
		model = new Hero();
		view = new HeroView(model);
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
			speedY = (float) (model.getVelocity() * Math.sin(direction));
			speedX = (float) (model.getVelocity() * Math.cos(direction));
		}

		oldX = model.getX();
		oldY = model.getY();

		model.setX(oldX - (delta * speedX));
		model.setY(oldY - (delta * speedY));
		
		boolean moving = true;
		model.setMoving(moving);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		view.render(container, g);
	}

	@Override
	public MovingModel getModel() {
		return model;
	}

	@Override
	public void collisionDetected() {
		model.setX(oldX);
		model.setY(oldY);
	}
}
