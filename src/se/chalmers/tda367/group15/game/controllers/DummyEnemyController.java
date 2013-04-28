package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.DummyEnemyModel;
import se.chalmers.tda367.group15.game.navigation.NpcNavigation;
import se.chalmers.tda367.group15.game.navigation.RandomNavigation;
import se.chalmers.tda367.group15.game.views.DummyEnemyView;

public class DummyEnemyController extends AbstractMovingModelController {

	/**
	 * Create a new dummy enemy controller
	 * 
	 * @param model
	 *            the DummyEnemy model
	 */
	public DummyEnemyController(DummyEnemyModel model,
			GameController gameController) {
		this(model, new RandomNavigation(), gameController);
	}

	public DummyEnemyController(DummyEnemyModel model, NpcNavigation navigator,
			GameController gameController) {
		super(gameController);
		setModel(model);
		setView(new DummyEnemyView(getModel()));
		setNavigator(navigator);
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
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds)
			throws SlickException {

		AbstractMovingModel model = getModel();

		double preRotation = model.getRotation();
		float preX = model.getX();
		float preY = model.getY();

		float tmpNewX = getNavigator().getNewX(preX, preRotation, delta,
				model.getVelocity());
		float tmpNewY = getNavigator().getNewY(preY, preRotation, delta,
				model.getVelocity());
		boolean collision = isCollision(tmpNewX, tmpNewY, staticBounds,
				dynamicBounds);
		model.setRotation(getNavigator()
				.getNewDirection(preRotation, collision));
		if (!collision) {
			model.setX(tmpNewX);
			model.setY(tmpNewY);
		}
	}
}
