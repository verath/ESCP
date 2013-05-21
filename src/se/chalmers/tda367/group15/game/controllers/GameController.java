package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.group15.game.event.SharedEventHandler;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.BossRoomModel;
import se.chalmers.tda367.group15.game.models.LeftWingRoomModel;
import se.chalmers.tda367.group15.game.models.LobbyRoomModel;
import se.chalmers.tda367.group15.game.models.ParkingLotRoomModel;
import se.chalmers.tda367.group15.game.models.ScoreModel;
import se.chalmers.tda367.group15.game.models.WCRoomModel;
import se.chalmers.tda367.group15.game.settings.Constants;
import se.chalmers.tda367.group15.game.views.HUDView;

public class GameController {

	private SoundEffectsController soundEffectsController;

	/**
	 * A flag for if the game is over
	 */
	private boolean gameOver;

	/**
	 * A flag for if the game was won
	 */
	private boolean gameWon;

	/**
	 * The roomsController handling what room is the current room.
	 */
	private RoomsController roomsController;

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
	private EventLoggerController eventLogger;

	/**
	 * Initialise the game. This can be used to load static resources. It's
	 * called before the game loop starts
	 * 
	 * @param container
	 *            The container holding the game
	 * @param game
	 *            The state based game currently running.
	 */
	public void init(GameContainer container, StateBasedGame game) {

		soundEffectsController = SoundEffectsController.instance();

		// Reset the game over flags
		gameOver = false;
		gameWon = false;
		// Set up an event logger to listen for events on the shared event
		// handler
		eventLogger = new EventLoggerController(SharedEventHandler.INSTANCE);

		// Set up the rooms
		RoomController parkingLot = new RoomController(this,
				new ParkingLotRoomModel());
		RoomController lobby = new RoomController(this, new LobbyRoomModel());
		RoomController wcRoom = new RoomController(this, new WCRoomModel());
		RoomController leftWing = new RoomController(this,
				new LeftWingRoomModel());
		RoomController bossRoom = new RoomController(this, new BossRoomModel());

		// Set up the room manager
		roomsController = new RoomsController();
		roomsController.addStartingRoom(parkingLot);
		roomsController.addRoom(parkingLot, lobby,
				RoomsController.RelativePosition.ABOVE);
		roomsController.addRoom(lobby, wcRoom,
				RoomsController.RelativePosition.RIGHTOF);
		roomsController.addRoom(lobby, leftWing,
				RoomsController.RelativePosition.LEFTOF);
		roomsController.addRoom(lobby, bossRoom,
				RoomsController.RelativePosition.ABOVE);
		roomsController.init(container);

		// Set up the hero controller
		heroController = new HeroController(this);

		// Set up a score model, shared between the scoreController and the
		// HUDController
		ScoreModel scoreModel = new ScoreModel(Constants.STARTING_SCORE);

		// Set up the score controller
		scoreController = new ScoreController(this, scoreModel);

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
	 *            The amount of time that's passed since last update in
	 *            milliseconds
	 */
	public void update(GameContainer container, StateBasedGame game, int delta) {

		if (gameOver) {
			return;
		}

		soundEffectsController.update();

		scoreController.update(container, delta);

		// get current room
		RoomController currentRoom = roomsController.getCurrentRoom();

		// get all static bounds
		List<Rectangle2D.Float> staticBounds = currentRoom.getStaticBounds();

		// get all dynamic bounds
		Map<AbstractMovingModel, Rectangle2D.Float> dynamicBounds = new HashMap<AbstractMovingModel, Rectangle2D.Float>();
		dynamicBounds.putAll(currentRoom.getDynamicBounds());
		dynamicBounds.put(heroController.getModel(), heroController.getModel()
				.getBounds());

		heroController.update(container, delta, staticBounds, dynamicBounds);
		roomsController.update(container, delta, staticBounds, dynamicBounds);

	}

	/**
	 * Render the game's screen here.
	 * 
	 * @param container
	 *            The container holing this game
	 * @param game
	 *            The state based game currently running.
	 * @param g
	 *            The graphics context that can be used to render. However,
	 *            normal rendering routines can also be used.
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) {

		roomsController.render(container, g);
		heroController.render(container, g);

		hudView.render(container, g);
	}

	/**
	 * Ends the game. Saves events, updates states and shows the game over
	 * screen.
	 * 
	 * @param win
	 *            True if the game was won.
	 */
	public void gameOver(boolean win) {
		// Save events
		eventLogger.saveEvents();

		// Set gameOver and gameWon flags. We can not directly reference the
		// StateController, and must therefore wait until it asks "us"
		gameOver = true;
		gameWon = win;
	}

	/**
	 * Method for checking if a game is over and if so, if it was won or not.
	 * 
	 * @return An array of length 2, where the first element is whether the game
	 *         is over and the second if the game was won.
	 */
	public boolean[] isGameOver() {
		return new boolean[] { gameOver, gameWon };
	}

	/**
	 * Getter for the RoomsController associated with this controller.
	 * 
	 * @return A RoomsController.
	 */
	RoomsController getRoomsController() {
		return roomsController;
	}

	/**
	 * Getter for the HeroController associated with this controller.
	 */
	HeroController getHeroController() {
		return heroController;
	}

	/**
	 * Getter for the score controller. This is used by the won game state to
	 * save the score.
	 * 
	 * @return The score controller used this game.
	 */
	public ScoreController getScoreController() {
		return this.scoreController;
	}

}
