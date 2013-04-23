package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
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
import se.chalmers.tda367.group15.game.controllers.room.BasicRoom;
import se.chalmers.tda367.group15.game.controllers.room.Room;
import se.chalmers.tda367.group15.game.controllers.room.RoomController;
import se.chalmers.tda367.group15.game.models.MovingModel;
import se.chalmers.tda367.group15.game.models.weapons.WeaponLoader;

/**
 * The main controller for the slick2d implementation of PsychoHero.
 * 
 * @author Peter
 * 
 */
public class PlayState extends BasicGameState {

	private final int ID;

	private boolean pendingEscpAction;
	private RoomController roomController;
	private MovingModelController heroController;

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
		Room startingRoom = new BasicRoom();

		// Set up the room manager
		roomController = new RoomController();
		roomController.addStartingRoom(startingRoom);
		roomController.init(container, game);

		// le weapons
		WeaponLoader.initWeapons();

		// Set up move controllers

		// Add hero
		heroController = new HeroController();

		// Set Cursor
		container.setMouseCursor("res/tiles/crosshair.png", 16, 16);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			pendingEscpAction = true;
		} else if (pendingEscpAction
				&& !container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			pendingEscpAction = false;
			game.enterState(Constants.GAME_STATE_MAIN_MENU);
		}

		heroController.update(container, delta);
		roomController.update(container, delta);

		collide();

	}

	private void collide() {
		Room currentRoom = roomController.getCurrentRoom();

		// get all controllers from the current room, and add heroController
		List<MovingModelController> modelControllers = new ArrayList<MovingModelController>();
		modelControllers.addAll(currentRoom.getControllers());
		modelControllers.add(heroController);

		// get all static bounds
		List<Rectangle2D.Float> staticBounds = currentRoom.getStaticBounds();

		// get all dynamic bounds
		Map<MovingModel, Rectangle2D.Float> dynamicBounds = new HashMap<MovingModel, Rectangle2D.Float>();
		dynamicBounds.putAll(currentRoom.getDynamicBounds());
		dynamicBounds.put(heroController.getModel(), heroController.getModel()
				.getBounds());

		for (MovingModelController controller : modelControllers) {
			for (Rectangle2D.Float blockedTile : staticBounds) {
				MovingModel model = controller.getModel();
				Rectangle2D.Float modelBound = model.getBounds();
				if (modelBound.intersects(blockedTile)) {
					controller.collisionDetected();
				}
			}

		}

		for (MovingModelController controller : modelControllers) {
			for (MovingModel model2 : dynamicBounds.keySet()) {
				MovingModel model1 = controller.getModel();

				if (model1 != model2) {
					boolean isCollision = model1.getBounds().intersects(
							model2.getBounds());
					if (isCollision) {
						controller.collisionDetected();
					}
				}
			}
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
