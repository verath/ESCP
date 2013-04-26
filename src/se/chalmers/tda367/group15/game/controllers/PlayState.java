package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.WeaponLoader;

/**
 * The main controller for the slick2d implementation of PsychoHero.
 * 
 * @author Carl Jansson, Peter, Simon Persson
 * 
 */
public class PlayState extends BasicGameState {

	private final int ID;

	private boolean pendingEscpAction;
	private RoomsController roomController;
	private AbstractMovingModelController heroController;

	/**
	 * Creates a new GameController
	 * 
	 * @param ID
	 *            The id used to identify the state.
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
		heroController.render(container, g);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// Set up the rooms
		AbstractRoomController startingRoom = new BasicRoomController();

		// Initialize weapons
		WeaponLoader.initWeapons();

		// Set up the room manager
		roomController = new RoomsController();
		roomController.addStartingRoom(startingRoom);
		roomController.init(container, game);

		// Set up the hero controller
		heroController = new HeroController();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		Input input = container.getInput();
		if(input.isKeyPressed(Input.KEY_COMMA))
			Constants.SHOW_BOUNDS = !Constants.SHOW_BOUNDS;
		// Check for esc key
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			pendingEscpAction = true;
		} else if (pendingEscpAction
				&& !container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			pendingEscpAction = false;
			game.enterState(Constants.GAME_STATE_MAIN_MENU);
		}

		// get current room
		AbstractRoomController currentRoom = roomController.getCurrentRoom();

		// get all static bounds
		List<Rectangle2D.Float> staticBounds = currentRoom.getStaticBounds();

		// get all dynamic bounds
		Map<AbstractMovingModel, Rectangle2D.Float> dynamicBounds = new HashMap<AbstractMovingModel, Rectangle2D.Float>();
		dynamicBounds.putAll(currentRoom.getDynamicBounds());
		dynamicBounds.put(heroController.getModel(), heroController.getModel()
				.getBounds());

		heroController.update(container, delta, staticBounds, dynamicBounds);
		roomController.update(container, delta, staticBounds, dynamicBounds);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getID() {
		return ID;
	}

}
