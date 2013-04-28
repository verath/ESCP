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
	protected GameController() {
	}

	private RoomsController roomController;
	private AbstractMovingModelController heroController;

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		roomController.render(container, g);
		heroController.render(container, g);
	}

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

}
