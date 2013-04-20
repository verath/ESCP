package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.controllers.room.RoomController;
import se.chalmers.tda367.group15.game.models.DummyEnemy;
import se.chalmers.tda367.group15.game.views.DummyEnemyView;

public class EnemyController implements MovingModelController {

	private DummyEnemyView view;
	private DummyEnemy model;

	private RoomController roomController;
	
	public EnemyController(RoomController roomController) {
		this.model = new DummyEnemy();
		this.view = new DummyEnemyView(model);
		

	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		view.render(container, g);
	}

}
