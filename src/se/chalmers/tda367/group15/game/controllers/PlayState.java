package se.chalmers.tda367.group15.game.controllers;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.controllers.room.BasicRoom;
import se.chalmers.tda367.group15.game.controllers.room.Room;
import se.chalmers.tda367.group15.game.controllers.room.RoomController;
import se.chalmers.tda367.group15.game.models.weapons.WeaponLoader;

/**
 * The main controller for the slick2d implementation of PsychoHero.
 * 
 * @author Carl Jansson, Peter, Simon Persson
 * 
 */
public class PlayState extends BasicGameState {

	private final int ID;

	private boolean pendingEscpAction;
	private RoomController roomController;
	private List<MovingModelController> moveControllers = new ArrayList<MovingModelController>();

	/**
	 * Creates a new GameController
	 * 
	 * @param ID
	 *            The int used to identify the state.
	 */
	public PlayState(int ID) {
		this.ID = ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		roomController.render(container, g);
		for (MovingModelController c : moveControllers) {
			c.render(container, g);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// Set up the rooms
		Room startingRoom = new BasicRoom();

		// Initialize weapons
		WeaponLoader.initWeapons();

		// Set up the room manager
		roomController = new RoomController();
		roomController.addStartingRoom(startingRoom);
		roomController.init(container, game);

		// Set up move controllers
		moveControllers.clear();

		// Add hero
		moveControllers.add(new HeroController(roomController));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		// Check for escp action
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			pendingEscpAction = true;
		} else if (pendingEscpAction
				&& !container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			pendingEscpAction = false;
			game.enterState(Constants.GAME_STATE_MAIN_MENU);
		}

		roomController.update(container, delta);
		for (MovingModelController c : moveControllers) {
			c.update(container, delta);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getID() {
		return ID;
	}

}
