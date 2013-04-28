package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.WeaponLoader;

class GameController {

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
	 * @throws SlickException
	 *             Throw to indicate an internal error
	 */
	public void init(GameContainer container) throws SlickException {
		// Set up the rooms
		AbstractRoomController startingRoom = new BasicRoomController();

		// Initialize weapons
		WeaponLoader.initWeapons();

		// Set up the room manager
		roomController = new RoomsController();
		roomController.addStartingRoom(startingRoom);
		roomController.init(container);

		// Set up the hero controller
		heroController = new HeroController();

	}

	/**
	 * Update the game logic here. No rendering should take place in this method
	 * though it won't do any harm.
	 * 
	 * @param container
	 *            The container holing this game
	 * @param delta
	 *            The amount of time thats passed since last update in
	 *            milliseconds
	 * @throws SlickException
	 *             Throw to indicate an internal error
	 */
	public void update(GameContainer container, int delta)
			throws SlickException {

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
	 * @param g
	 *            The graphics context that can be used to render. However,
	 *            normal rendering routines can also be used.
	 * @throws SlickException
	 *             Throw to indicate a internal error
	 */
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		roomController.render(container, g);
		heroController.render(container, g);
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
