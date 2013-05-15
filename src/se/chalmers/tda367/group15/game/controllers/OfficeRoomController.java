package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.DummyEnemyModel;

public class OfficeRoomController extends AbstractRoomController {

	protected OfficeRoomController(GameController gameController) {
		super(gameController);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		setMap("res/levels/right_room.tmx");

		// create enemy models
		DummyEnemyModel e1 = new DummyEnemyModel();
		DummyEnemyModel e2 = new DummyEnemyModel(400, 200);
		DummyEnemyModel e3 = new DummyEnemyModel(100, 600);
		DummyEnemyModel e4 = new DummyEnemyModel(940, 600);
		DummyEnemyModel e5 = new DummyEnemyModel(200, 270);

		// add moving models to the room
		addMovingModel(e1, 0, 10, 0, 10);
		addMovingModel(e2, 12, 32, 0, 9);
		addMovingModel(e3, 0, 18, 16, 24);
		addMovingModel(e4, 18, 32, 10, 24);
		addMovingModel(e5);


	}

}
