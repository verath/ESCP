package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import se.chalmers.tda367.group15.game.constants.Constants;
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
public class MainController extends BasicGameState {

	private final int ID;

	private Hero heroModel;
	private HeroView heroView;

	private RoomController roomController;
	private MovementController moveController;

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
	public MainController(int ID) {
		this.ID = ID;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		roomController.render(container, g);
		heroView.render(container, g);
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// Set up the rooms
		BasicRoomModel roomModel = new BasicRoomModel();
		BasicRoomView roomView = new BasicRoomView(roomModel);
		Room startingRoom = new Room(roomView, roomModel);

		// Set up the room manager
		roomController = new RoomController();
		roomController.addStartingRoom(startingRoom);
		
		// Set up move controller
		moveController = new MovementController();

		// Set up the hero
		heroModel = new Hero();
		heroView = new HeroView(heroModel);
		roomController.init(container);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		if ( container.getInput().isKeyDown(Input.KEY_ESCAPE)){
			game.enterState(Constants.GAME_STATE_MAIN_MENU);
		}
		
		roomController.update(container, delta);
		moveController.update(container, delta);
		heroModel.update(container, delta);
		

		// Handle collisions
//		List<Rectangle2D.Float> collisionBounds = new ArrayList<Rectangle2D.Float>();
//
//		collisionBounds.addAll(heroModel.getCollisionBounds());
//		collisionBounds.addAll(roomController.getCurrentRoom().getRoomModel()
//				.getCollisionBounds());
//
//		heroModel.collide(collisionBounds);
//		roomController.getCurrentRoom().getRoomModel().collide(collisionBounds);
	}


	@Override
	public int getID() {
		return ID;
	}

}
