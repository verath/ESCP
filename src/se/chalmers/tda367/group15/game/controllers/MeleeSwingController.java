package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.MeleeSwingModel;

public class MeleeSwingController extends AbstractMovingModelController {

	protected MeleeSwingController(GameController gameController,
			AbstractMovingModel swing) {
		super(gameController);
		setModel(swing);

		// A melee swing is "invisible"
		setView(null);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// Ugly rectangle for the bounds
		g.drawRect(getModel().getX(), getModel().getY(), getModel().getWidth(), getModel().getHeight());
	}

	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds)
			throws SlickException {
		MeleeSwingModel swing = (MeleeSwingModel) getModel();
		if (swing.isAlive() && swing.getDistance() <= 10) {
			swing.incDistance();
			if (!isCollision(swing.getX(), swing.getY(), swing.getHeight(),
					swing.getWidth(), staticBounds, dynamicBounds)) {
				
				swing.setX(swing.getX()
						- (float) Math.cos(Math.toRadians(swing.getRotation()))
						* (swing.getVelocity() * delta));
				
				swing.setY(swing.getY()
						- (float) Math.sin(Math.toRadians(swing.getRotation()))
						* swing.getVelocity() * delta);

			} else {
				swing.setAlive(false);
			}

		}

	}

}
