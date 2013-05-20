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

	MeleeSwingController(GameController gameController,
                         AbstractMovingModel swing) {
		super(gameController);
		setModel(swing);

		// A melee swing is "invisible"
		setView(null);
	}

	@Override
	public void render(GameContainer container, Graphics g) {
	}

	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds) {
		AbstractMovingModel swing = getModel();
		if (swing.isAlive()) {

			float oldX = swing.getX();
			float oldY = swing.getY();

			float newX = swing.getX()
					- (float) Math.cos(Math.toRadians(swing.getRotation()))
					* (swing.getVelocity() * delta);
			float newY = swing.getY()
					- (float) Math.sin(Math.toRadians(swing.getRotation()))
					* (swing.getVelocity() * delta);
			if ((float) Math.hypot((oldX - newX), (oldY - newY)) >= swing
					.getVelocity() * 5f / 4f)
				swing.setAlive(false);
			if (!isCollision(swing.getX(), swing.getY(), swing.getHeight(),
					swing.getWidth(), staticBounds, dynamicBounds)) {
				swing.setX(newX);
				swing.setY(newY);
			}

		}

	}
}
