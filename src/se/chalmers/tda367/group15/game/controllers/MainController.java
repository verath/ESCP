package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

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
import se.chalmers.tda367.group15.game.models.MovingModel;
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

	private List<Rectangle2D.Float> staticBounds = new ArrayList<Rectangle2D.Float>();

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
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		// Set up the rooms
		BasicRoomModel roomModel = new BasicRoomModel();
		BasicRoomView roomView = new BasicRoomView(roomModel);
		Room startingRoom = new Room(roomView, roomModel);

		// Set up the room manager
		roomController = new RoomController();
		roomController.addStartingRoom(startingRoom);

		staticBounds = roomController.getCurrentRoom().getRoomView()
				.getCollisionBounds();

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

		if (container.getInput().isKeyDown(Input.KEY_ESCAPE)) {
			game.enterState(Constants.GAME_STATE_MAIN_MENU);
		}

		roomController.update(container, delta);
		moveController.update(container, delta);
		move(heroModel, container, delta);

	}

	@Override
	public int getID() {
		return ID;
	}

	public void move(MovingModel model, GameContainer container, int delta) {
		Rectangle2D.Float modelBounds = model.getBounds();
		if (model instanceof Hero) {
			model = (Hero) model;
			Input input = container.getInput();
			float mouseX = input.getMouseX();
			float mouseY = input.getMouseY();

			// Calculate facing depedning on where the mouse is relative
			// to the center of the hero
			((Hero) model).setRotation(Math.toDegrees(Math.atan2(
					(model.getHeight() / 2 + model.getY() - mouseY),
					(model.getWidth() / 2 + model.getX() - mouseX))));

			boolean goingUp = input.isKeyDown(Input.KEY_W)
					|| input.isKeyDown(Input.KEY_UP);
			boolean goingDown = input.isKeyDown(Input.KEY_S)
					|| input.isKeyDown(Input.KEY_DOWN);
			boolean goingRight = input.isKeyDown(Input.KEY_D)
					|| input.isKeyDown(Input.KEY_RIGHT);
			boolean goingLeft = input.isKeyDown(Input.KEY_A)
					|| input.isKeyDown(Input.KEY_LEFT);

			// Calculate move direction and move
			float speedY = (goingUp ? 1 : 0) - (goingDown ? 1 : 0);
			float speedX = (goingLeft ? 1 : 0) - (goingRight ? 1 : 0);

			if (speedY != 0 || speedX != 0) {
				double direction = Math.atan2(speedY, speedX);
				speedY = (float) (model.getVelocity() * Math.sin(direction));
				speedX = (float) (model.getVelocity() * Math.cos(direction));
			}

			model.setY(model.getY() - (delta * speedY));
			// if (isCollision(collisionBounds)) {
			// model.setY(oldY);
			// }
			//
			model.setX(model.getX() - (delta * speedX));
			// calculateCollisionBounds();
			// if (isCollision(collisionBounds)) {
			// model.setX(oldX);
			// }
		}

	}

}
