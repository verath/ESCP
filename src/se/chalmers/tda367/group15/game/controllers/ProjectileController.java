package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.BulletModel;
import se.chalmers.tda367.group15.game.views.BulletView;
import se.chalmers.tda367.group15.game.views.View;

public class ProjectileController extends AbstractMovingModelController {

	protected ProjectileController(GameController gameController) {
		super(gameController);
		AbstractMovingModel bullet = new BulletModel();
		View bulletview = new BulletView(bullet);
		setModel(bullet);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds)
			throws SlickException {
		// TODO Auto-generated method stub

	}

}
