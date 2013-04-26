package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.HeroModel;
import se.chalmers.tda367.group15.game.views.HeroView;

public class HeroController extends AbstractMovingModelController {

	private boolean goingUp;
	private boolean goingDown;
	private boolean goingLeft;
	private boolean goingRight;

	/**
	 * Create a new controller for the hero.
	 * 
	 * @param roomController
	 */
	public HeroController() {
		setModel(new HeroModel());
		setView(new HeroView(getModel()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds, Map<AbstractMovingModel, Float> dynamicBounds) {
		AbstractMovingModel model = getModel();
		Input input = container.getInput();
		float mouseX = input.getMouseX();
		float mouseY = input.getMouseY();

		// Calculate facing depending on where the mouse is relative
		// to the center of the hero
		((HeroModel) getModel()).setRotation(Math.toDegrees(Math.atan2(
				(model.getHeight() / 2 + model.getY() - mouseY),
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

		float newX = model.getX() - (delta * speedX);
		float newY = model.getY() - (delta * speedY);

		if (!isCollision(newX, model.getY(), staticBounds, dynamicBounds)) {
			model.setX(newX);
		}

		if (!isCollision(model.getX(), newY, staticBounds, dynamicBounds)) {
			model.setY(newY);
		}

		model.setMoving(speedY != 0 || speedX != 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		getView().render(container, g);
	}
}
