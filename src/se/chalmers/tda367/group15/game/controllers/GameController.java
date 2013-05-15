package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.group15.game.event.SharedEventHandler;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.ScoreModel;
import se.chalmers.tda367.group15.game.settings.Constants;
import se.chalmers.tda367.group15.game.views.HUDView;

public class GameController {

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
	private EventLoggerController eventLogger;

	/**
	 * Initialise the game. This can be used to load static resources. It's
	 * called before the game loop starts
	 * 
	 * @param container
	 *            The container holding the game
	 * @param game
	 *            The state based game currently running.
	 * @throws SlickException
	 *             Throw to indicate an internal error
	 */
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		// Reset the game over flags
		gameOver = false;
		gameWon = false;

		// Set up an event logger to listen for events on the shared event
		// handler
		eventLogger = new EventLoggerController(SharedEventHandler.INSTANCE);

		// Set up the rooms
		AbstractRoomController startingRoom = new BasicRoomController(this);
		AbstractRoomController secondRoom = new SecondRoomController(this);

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

		if (gameOver) {
			return;
		}

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

		// TODO: Remove this.
		if (container.getInput().isKeyPressed(Input.KEY_G)) {
			gameOver(false);
		} else if (container.getInput().isKeyPressed(Input.KEY_H)) {
			gameOver(true);
		}

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
	 * Ends the game. Saves events, updates states and shows the game over
	 * screen.
	 * 
	 * @param win
	 *            True if the game was won.
	 */
	public void gameOver(boolean win) {
		// Save events
		eventLogger.saveEvents();
		if (win) {
			// Save score
			// TODO: Name for the saved score?
			scoreController.saveScore(null);
		}

		// Set gameOver and gameWon flags. We can not directly reference the
		// StateController, and must therefore wait until it asks "us"
		gameOver = true;
		gameWon = win;

	}

	/**
	 * Method for checking if a game is over and if so, if it was won or not.
	 * 
	 * @return An array of lenght 2, where the first element is whether the game
	 *         is over and the second if the game was won.
	 */
	public boolean[] isGameOver() {
		return new boolean[] { gameOver, gameWon };
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
