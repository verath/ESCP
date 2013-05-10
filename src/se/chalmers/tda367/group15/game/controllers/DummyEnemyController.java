package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import se.chalmers.tda367.group15.game.models.AbstractCharacterModel;
import se.chalmers.tda367.group15.game.models.AbstractMeleeWeapon;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.AbstractProjectileModel;
import se.chalmers.tda367.group15.game.models.DummyEnemyModel;
import se.chalmers.tda367.group15.game.models.MeleeSwingModel;
import se.chalmers.tda367.group15.game.views.DummyEnemyView;

/**
 * Creates a new dummy enemy
 * 
 * @author Simon Persson, Carl Jansson
 * 
 */
public class DummyEnemyController extends AbstractNpcController {
	private boolean hasFired;

	/**
	 * Creates a new dummyenemy controller.
	 * 
	 * @param model
	 *            the DummyEnemy model
	 * @param navigator
	 *            The navigator to use.
	 * @param gameController
	 *            A reference to the controller
	 */
	public DummyEnemyController(DummyEnemyModel model, TileBasedMap map,
			GameController gameController) {
		this(model, map, gameController, 0, 10, 0, 32);
	}

	/**
	 * Creates a new dummyenemy controller that navigates in enclosed space.
	 * 
	 * @param model
	 *            the DummyEnemy model
	 * @param navigator
	 *            The navigator to use.
	 * @param gameController
	 *            A reference to the controller
	 * @param x
	 *            min x pos
	 * @param x2
	 *            max x pos
	 * @param y
	 *            min y pos
	 * @param y2
	 *            max y pos
	 */
	public DummyEnemyController(DummyEnemyModel model, TileBasedMap map,
			GameController gameController, int x, int x2, int y, int y2) {
		super(gameController, new AStarPathFinder(map, 500, true), x, x2, y, y2);
		setModel(model);
		setView(new DummyEnemyView(model));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		getView().render(container, g);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds)
			throws SlickException {
		if (hasFired) {
			swingWeapon();
			hasFired = false;
		}

		// Save old position
		float oldX = this.getModel().getX();
		float oldY = this.getModel().getY();

		// this moves the npc
		randomPosMove(container, delta, staticBounds, dynamicBounds);

		// get new position
		float newX = this.getModel().getX();
		float newY = this.getModel().getY();

		// Set whether model is moving or not
		this.getModel().setMoving(!((oldX == newX) || (oldY == newY)));

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fire() {
		hasFired = true;

	}

	private void swingWeapon() {
		AbstractCharacterModel model = (AbstractCharacterModel) getModel();

		// Run the swinging animation for the weapon
		AbstractMeleeWeapon weapon = (AbstractMeleeWeapon) model
				.getCurrentWeapon();
		DummyEnemyView view = (DummyEnemyView) getView();
		view.runAnimation(weapon.getSwingAnimation());

		AbstractProjectileModel newSwing = new MeleeSwingModel();

		float modelAngle = (float) Math.toRadians(model.getRotation());
		float modelMiddleX = model.getX() + model.getWidth() / 2;
		float heroMiddleY = model.getY() + model.getHeight() / 2;

		float modelFaceX = modelMiddleX - (float) Math.cos(modelAngle)
				* ((model.getWidth()));
		float modelFaceY = heroMiddleY - (float) Math.sin(modelAngle)
				* ((model.getHeight()));

		newSwing.setX(modelFaceX - newSwing.getWidth() / 2);
		newSwing.setY(modelFaceY - newSwing.getHeight() / 2);
		newSwing.setRotation(model.getRotation());
		newSwing.setDamage(model.getCurrentWeapon().getDamage());
		newSwing.setAlive(true);

		AbstractRoomController currentRoom = getGameController()
				.getRoomController().getCurrentRoom();

		currentRoom.addSwing(newSwing);
	}
}
