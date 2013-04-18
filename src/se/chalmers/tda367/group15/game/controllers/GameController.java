package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.controllers.room.Room;
import se.chalmers.tda367.group15.game.controllers.room.RoomController;
import se.chalmers.tda367.group15.game.models.Hero;
import se.chalmers.tda367.group15.game.models.room.BasicRoomModel;
import se.chalmers.tda367.group15.game.views.HeroView;
import se.chalmers.tda367.group15.game.views.room.BasicRoomView;

/**
 * The main controller for the slick2d implementation of PsychoHero.
 * 
 * @author Peter
 * 
 */
public class GameController extends BasicGame {

	private final Hero heroModel;
	private final HeroView heroView;

	private final RoomController roomController;

	/**
	 * Creates a new GameController
	 * 
	 * @param title
	 *            The title of the game
	 * @param gameView
	 *            The GameView that should receive render and init
	 * @param gameModel
	 *            The GameModel that should receive update
	 * @param roomController
	 */
	public GameController(String title) {
		super(title);

		// Set up the rooms
		BasicRoomModel roomModel = new BasicRoomModel();
		BasicRoomView roomView = new BasicRoomView(roomModel);
		Room startingRoom = new Room(roomView, roomModel);

		// Set up the room manager
		roomController = new RoomController();
		roomController.addStartingRoom(startingRoom);

		// Set up the hero
		heroModel = new Hero();
		heroView = new HeroView(heroModel);
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		roomController.render(container, g);
		heroView.render(container, g);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		roomController.init(container);
		heroView.init(container);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		roomController.update(container, delta);
		heroModel.update(container, delta);

		// Handle collisions
		List<Rectangle2D.Float> collisionBounds = new ArrayList<Rectangle2D.Float>();

		collisionBounds.addAll(heroModel.getCollisionBounds());
		collisionBounds.addAll(roomController.getCurrentRoom().getRoomModel().getCollisionBounds());

		heroModel.collide(collisionBounds);
		roomController.getCurrentRoom().getRoomModel().collide(collisionBounds);
	}

}
