package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.AbstractProjectileModel;
import se.chalmers.tda367.group15.game.views.ProjectileView;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

/**
 * Class representing a projectile controller.
 * 
 * @author simon
 * 
 */
public class ProjectileController extends AbstractMovingModelController {

	ProjectileController(GameController gameController,
			AbstractProjectileModel projectile) {
		super(gameController);
		setModel(projectile);
		setView(new ProjectileView(projectile));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g) {
		getView().render(container, g);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds) {
		AbstractMovingModel projectile = getModel();
		if (projectile.isAlive()) {

			if (!isCollision(projectile.getX(), projectile.getY(),
					projectile.getHeight(), projectile.getWidth(),
					staticBounds, dynamicBounds)) {
				projectile.setX(projectile.getX()
						- (float) Math.cos(Math.toRadians(projectile
								.getRotation()))
						* (projectile.getVelocity() * delta));
				projectile.setY(projectile.getY()
						- (float) Math.sin(Math.toRadians(projectile
								.getRotation())) * projectile.getVelocity()
						* delta);
			}

		}

	}

}
