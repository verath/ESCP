package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.DummyEnemy;
import se.chalmers.tda367.group15.game.models.MovingModel;
import se.chalmers.tda367.group15.game.views.DummyEnemyView;

public class DummyEnemyController extends MovingModelController {

	/**
	 * Create a new dummy enemy controller
	 * @param model the DummyEnemy model
	 */
	public DummyEnemyController(DummyEnemy model) {
		setModel(model);
		setView(new DummyEnemyView(getModel()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		getView().render(container, g);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds, Map<MovingModel, Float> dynamicBounds)
			throws SlickException {
		MovingModel model = getModel();

		float newX;
		if (model.getRotation() == 180) {
			newX = model.getX() + (delta * model.getVelocity());
		} else {
			newX = model.getX() - (delta * model.getVelocity());
		}

		if (isCollision(newX, model.getY(), staticBounds, dynamicBounds)) {
			model.setRotation((model.getRotation() + 180) % 360);
		}
		model.setX(newX);

	}

}
