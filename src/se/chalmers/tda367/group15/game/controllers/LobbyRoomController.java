package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.DummyEnemyModel;

public class LobbyRoomController extends AbstractRoomController {

	protected LobbyRoomController(GameController gameController) {
		super(gameController);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(GameContainer container) throws SlickException {

		setMap("res/levels/lobby.tmx");

		// create enemy models
		DummyEnemyModel e1 = new DummyEnemyModel(200, 200);
		DummyEnemyModel e2 = new DummyEnemyModel(650, 364);
		DummyEnemyModel e3 = new DummyEnemyModel(564, 300);
		DummyEnemyModel e4 = new DummyEnemyModel(500, 460);
		DummyEnemyModel e5 = new DummyEnemyModel(500, 200);

		// add moving models to the room
		addMovingModel(e1);
		addMovingModel(e2);
		addMovingModel(e3);
		addMovingModel(e4);
		addMovingModel(e5);

	}

}
