package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.AbstractProjectileModel;
import se.chalmers.tda367.group15.game.views.ProjectileView;

public class ProjectileController extends AbstractMovingModelController {

	protected ProjectileController(GameController gameController,
			AbstractProjectileModel projectile) {
		super(gameController);
		setModel(projectile);
		setView(new ProjectileView(projectile));
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		getView().render(container, g);
	}

	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds)
			throws SlickException {
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
