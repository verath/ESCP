package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.views.BulletView;
import se.chalmers.tda367.group15.game.views.View;

public class ProjectileController extends AbstractMovingModelController {

	private List<View> projectileViews = new ArrayList<View>();
	private List<AbstractMovingModel> projectiles = new ArrayList<AbstractMovingModel>();

	protected ProjectileController(GameController gameController) {
		super(gameController);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		if (projectileViews.size() > 0) {
			for (View view : projectileViews) {
				view.render(container, g);
			}
		}
	}

	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds)
			throws SlickException {
		if (projectiles.size() > 0) {
			for (AbstractMovingModel projectile : projectiles) {
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
										.getRotation()))
								* projectile.getVelocity() * delta);
					} else {
						projectile.setAlive(false);
					}
				}
			}
		}

	}

	/**
	 * Method for adding a projectile to the projectile controller.
	 * 
	 * @param projectile
	 *            The projectile to be added
	 * @param projectileView
	 *            The view of the projectile
	 */
	public void addProjectile(AbstractMovingModel projectile) {
		projectiles.add(projectile);
		projectileViews.add(new BulletView(projectile));
	}

}
