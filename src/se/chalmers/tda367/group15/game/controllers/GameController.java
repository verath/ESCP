package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.event.SharedEventHandler;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.ScoreModel;
import se.chalmers.tda367.group15.game.models.WeaponLoader;
import se.chalmers.tda367.group15.game.views.HUDView;

class GameController {

	private boolean imagesAlreadyLoaded = false;

	/**
	 * The roomsController handling what room is the current room.
	 */
	private RoomsController roomController;

	/**
	 * The hero controller, handling actions to be taken by the hero (ie. the
	 * player)
	 */
	private HeroController heroController;

	/**
	 * The HUD view (displaying things such as the hp of the hero)
	 */
	private HUDView hudView;

	/**
	 * The Score controller, handling decreasing of score over time and saving
	 * score to the database.
	 */
	private ScoreController scoreController;

	/**
	 * The Event logger, logging events and saves them to the database.
	 */
	private EventLogger eventLogger;

	/**
	 * Creates the GameController
	 */
	protected GameController() {
	}

	/**
	 * Initialise the game. This can be used to load static resources. It's
	 * called before the game loop starts
	 * 
	 * @param container
	 *            The container holding the game
	 * @param game The state based game currently running.
	 * @throws SlickException
	 *             Throw to indicate an internal error
	 */
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// Set up the event logger
		if (eventLogger != null) {
			// If this is a new game. Save and clear events instead
			eventLogger.saveEvents();
		} else {
			eventLogger = new EventLogger(SharedEventHandler.INSTANCE);
		}

		// Set up the rooms
		AbstractRoomController startingRoom = new BasicRoomController(this);
		AbstractRoomController secondRoom = new SecondRoomController(this);

		// Initialize weapons
		if (!imagesAlreadyLoaded) {
			WeaponLoader.initWeapons();
			imagesAlreadyLoaded = true;
		}

		// Set up the room manager
		roomController = new RoomsController();
		roomController.addStartingRoom(startingRoom);
		roomController.addRoom(startingRoom, secondRoom,
				RoomsController.RelativePosition.LEFTOF);
		roomController.init(container);

		// Set up the hero controller
		heroController = new HeroController(this);

		// Set up a score model, shared between the scoreController and the
		// HUDController
		ScoreModel scoreModel = new ScoreModel(Constants.STARTING_SCORE);

		// Set up the score controller
		scoreController = new ScoreController(scoreModel);

		// Set up the HUD controller
		hudView = new HUDView(scoreModel);
	}

	/**
	 * Update the game logic here. No rendering should take place in this method
	 * though it won't do any harm.
	 * 
	 * @param container
	 *            The container holing this game
	 * @param game
	 *            The state based game currently running.
	 * @param delta
	 *            The amount of time thats passed since last update in
	 *            milliseconds
	 * @throws SlickException
	 *             Throw to indicate an internal error
	 */
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		scoreController.update(container, delta);

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
	 * Render the game's screen here.
	 * 
	 * @param container
	 *            The container holing this game
	 * @param game The state based game currently running.
	 * @param g
	 *            The graphics context that can be used to render. However,
	 *            normal rendering routines can also be used.
	 * @throws SlickException
	 *             Throw to indicate a internal error
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		roomController.render(container, g);
		heroController.render(container, g);

		hudView.render(container, g);
	}

	/**
	 * Getter for the RoomsController associated with this controller.
	 * 
	 * @return
	 */
	protected RoomsController getRoomController() {
		return roomController;
	}

	/**
	 * Getter for the HeroController associated with this controller.
	 */
	protected HeroController getHeroController() {
		return heroController;
	}

}
