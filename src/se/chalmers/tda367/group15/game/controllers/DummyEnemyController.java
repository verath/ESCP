package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.DummyEnemy;
import se.chalmers.tda367.group15.game.models.MovingModel;
import se.chalmers.tda367.group15.game.views.DummyEnemyView;

public class DummyEnemyController implements MovingModelController{

	private DummyEnemy model;
	private DummyEnemyView view;
	
	public DummyEnemyController(DummyEnemy model) {
		this.model = model;
		this.view = new DummyEnemyView(this.model);
	}
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		view.render(container, g);
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		
	}
	@Override
	public MovingModel getModel() {
		return model;
	}
	@Override
	public void collisionDetected() {
			System.out.println("FiendeKrock!");
	}

	
}
