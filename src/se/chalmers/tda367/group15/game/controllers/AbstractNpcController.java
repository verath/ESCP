package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.pathfinding.AStarPathFinder;
import org.newdawn.slick.util.pathfinding.Path;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import se.chalmers.tda367.group15.game.models.*;
import se.chalmers.tda367.group15.game.util.CollisionHelper;
import se.chalmers.tda367.group15.game.views.CharacterView;

/**
 * AbstractNpcController implements methods for pathfinding and movement of npc
 * characters.
 * 
 * @author Carl Jansson
 */
public abstract class AbstractNpcController extends
		AbstractMovingModelController {

	private long swingTimer = 0;

	int ENEMY_DAMAGE_MODIFIER = 2;

	private final SoundEffectsController soundController = SoundEffectsController
			.instance();

	/**
	 * The path controller is traveling
	 */
	private Path myPath;

	/**
	 * Current step of the path
	 */
	private int currentStep;

	/**
	 * the path finder
	 */
	private AStarPathFinder myPathFinder;

	/**
	 * waiting time start for creating new path
	 */
	private long pauseTime;

	/**
	 * Time to wait before creating new path
	 */
	private long waitTime;

	/**
	 * with of x to walk in
	 */
	private int deltaX;

	/**
	 * height of y to walk in
	 */
	private int deltaY;

	/**
	 * X coordinate of area to walk in
	 */
	private int startX;

	/**
	 * Y coordinate of area to walk in
	 */
	private int startY;

	/**
	 * Create a new npc controller.
	 * 
	 * @param gameController
	 *            The GameController to be used.
	 * @param model
	 *            The model that this controller should control.
	 * @param map
	 *            The tile based map to be used.
	 */
	AbstractNpcController(GameController gameController,
			AbstractNpcModel model, TileBasedMap map) {
		super(gameController);
		this.setPathFinder(new AStarPathFinder(map, 500, true));
		this.setModel(model);
		this.setView(new CharacterView(model));
		this.setDefaultTiles(model.getMinTileX(), model.getMaxTileX(),
				model.getMinTileY(), model.getMaxTileY());
		this.pauseTime = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void render(GameContainer container, Graphics g);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds) {
	}

	/**
	 * Set path finder
	 * 
	 * @param pathFinder
	 *            the pathfinder to use
	 */
	void setPathFinder(AStarPathFinder pathFinder) {
		this.myPathFinder = pathFinder;
	}

	/**
	 * get current pathfinder
	 * 
	 * @return the active pathfinder
	 */
	AStarPathFinder getPathFinder() {
		return myPathFinder;
	}

	/**
	 * Makes npc move along path if path exists. Sets npc rotation to next path
	 * node.
	 * 
	 * @param model
	 *            the model that moves
	 * @param delta
	 *            time since last update
	 * @param dynamicBounds
	 *            other moving models
	 */
	void moveAlongPath(AbstractMovingModel model, int delta,
			Map<AbstractMovingModel, Float> dynamicBounds) {
		if (myPath.getLength() >= currentStep) {
			float diffX = model.getX() - (myPath.getX(currentStep) * 32);
			float diffY = model.getY() - (myPath.getY(currentStep) * 32);

			double dir = Math.atan2(diffY, diffX);
			model.setRotation(Math.toDegrees(dir));

			float speedY = (float) (model.getVelocity() * Math.sin(dir));
			float speedX = (float) (model.getVelocity() * Math.cos(dir));

			float tmpNewX = model.getX() - (delta * speedX);
			float tmpNewY = model.getY() - (delta * speedY);

			// if dynamic collision set path to null so a new random path will
			// be created
			if (isDynamicCollision(tmpNewX, tmpNewY, dynamicBounds)) {
				myPath = null;
				// Set the new positions
			} else {
				if (!isDynamicCollision(tmpNewX, model.getY(), dynamicBounds)) {
					model.setX(tmpNewX);
				}

				if (!isDynamicCollision(model.getX(), tmpNewY, dynamicBounds)) {
					model.setY(tmpNewY);
				}

				int currX = (int) (model.getX() + (model.getWidth() / 2)) / 32;
				int currY = (int) (model.getY() + (model.getHeight() / 2)) / 32;

				if (currX == myPath.getX(currentStep)
						&& currY == myPath.getY(currentStep)) {
					currentStep++;
				}
			}
		}
	}

	/**
	 * Make a new random path. Starting with current position. Goal is defined
	 * by setDefaultTiles.
	 */
	void calculateRandomPath() {
		int tarX = startX + (int) (Math.random() * deltaX);
		int tarY = startY + (int) (Math.random() * deltaY);

		calculateNewPath(tarX, tarY);
	}

	/**
	 * Calculates a new path from current position to (x, y)
	 * 
	 * @param tarX
	 *            ending X position in tiles
	 * @param tarY
	 *            ending Y position in tiles
	 */
	void calculateNewPath(int tarX, int tarY) {
		myPath = getPathFinder().findPath(null,
				(int) (getModel().getX() + (getModel().getWidth() / 2)) / 32,
				(int) (getModel().getY() + (getModel().getHeight() / 2)) / 32,
				tarX, tarY);
		currentStep = 1;
	}

	/**
	 * If time == 0 starts a new random timer. If time has passed sets time to 0
	 * and returns true
	 * 
	 * @return true if random time has passed
	 */
	boolean pauseTimer() {
		if (pauseTime == 0) {
			pauseTime = System.currentTimeMillis();
			waitTime = (long) (2000 * Math.random());
		} else if (System.currentTimeMillis() >= waitTime + pauseTime) {
			pauseTime = 0;
			return true;
		}
		return false;
	}

	/**
	 * check if there is a path or if end of it reached.
	 * 
	 * @return false if path is null or current step is end of path
	 */
	boolean existsPath() {
		return !(myPath == null || currentStep >= myPath.getLength());
	}

	/**
	 * Set max and min positions. If given coordinates not allowed sets entire
	 * map as default.
	 * 
	 * (x1, y1) and (x2, y2) is upper left corner and lower right corner of tile
	 * box for model to move in. Model do not have to start in this box and when
	 * they track hero they might move outside this box. But default movement
	 * position will always be inside. If x1, x2, y1, y2 not used entire map
	 * will be set as default.
	 * 
	 * @param x
	 *            minimum x tile random movement will occur on
	 * @param x2
	 *            maximum x tile random movement will occur on
	 * @param y
	 *            minimum y tile random movement will occur on
	 * @param y2
	 *            maximum y tile random movement will occur on
	 */
	void setDefaultTiles(int x, int x2, int y, int y2) {

		if (x >= 0 && y >= 0 && x < x2 && y < y2 && x2 <= 32 && y2 <= 24) {
			deltaX = x2 - x;
			deltaY = y2 - y;
			startX = x;
			startY = y;

		} else {
			deltaX = 32;
			deltaY = 24;
			startX = 0;
			startY = 0;
		}
	}

	/**
	 * Checks a way between two points against dynamic bounds
	 * 
	 * @param dynamicBounds
	 *            A map of models and their collision bounds
	 * @param point1X
	 *            x variable of first point
	 * @param point1Y
	 *            y variable of first point
	 * @param point2X
	 *            x variable of second point
	 * @param point2Y
	 *            y variable of second point
	 * @return true if path is blocked by a moving model.
	 */
	boolean isWayClear(Map<AbstractMovingModel, Float> dynamicBounds,
			float point1X, float point1Y, float point2X, float point2Y) {
		for (AbstractMovingModel otherModel : dynamicBounds.keySet()) {
			if (this.getModel() != otherModel && otherModel.isAlive()
					&& !(otherModel instanceof HeroModel)) {

				Rectangle2D.Float bound = otherModel.getBounds();
				if (CollisionHelper.recIntersectLine(bound, point1X, point1Y,
						point2X, point2Y)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Time the fire method so animation can run its course
	 */
	void fireTimed() {
		if (System.currentTimeMillis() - swingTimer > ((AbstractCharacterModel) getModel())
				.getCurrentWeapon().getFiringSpeed()) {
			swingTimer = System.currentTimeMillis();
			fire();
		}
	}

	/**
	 * Method to tell npc to fire a weapon of some kind
	 */
	protected abstract void fire();

	/**
	 * Swing the the currently equipped weapon of the enemy
	 */
	void swingWeapon() {
		AbstractCharacterModel model = (AbstractCharacterModel) getModel();

		// Run the swinging animation for the weapon
		CharacterView view = (CharacterView) getView();
		view.runAttackAnimation();

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
		newSwing.setDamage(model.getCurrentWeapon().getDamage()
				* ENEMY_DAMAGE_MODIFIER);
		newSwing.setAlive(true);

		RoomController currentRoom = getGameController().getRoomsController()
				.getCurrentRoom();

		currentRoom.addSwing(newSwing);
	}

	/**
	 * Create a bullet.
	 */
	void createBullet() {
		soundController
				.playSound(SoundEffectsController.SoundEffect.PISTOL_FIRED);
		AbstractCharacterModel model = (AbstractCharacterModel) getModel();
		AbstractProjectileModel newDonut = new BulletModel();

		float heroAngle = (float) Math.toRadians(model.getRotation());
		float heroMiddleX = model.getX() + model.getWidth() / 2;
		float heroMiddleY = model.getY() + model.getHeight() / 2;

		float heroFaceX = heroMiddleX - (float) Math.cos(heroAngle)
				* ((model.getWidth()));
		float heroFaceY = heroMiddleY - (float) Math.sin(heroAngle)
				* ((model.getHeight()));

		newDonut.setX(heroFaceX - newDonut.getWidth() / 2);
		newDonut.setY(heroFaceY - newDonut.getHeight() / 2);

		newDonut.setRotation(model.getRotation());
		newDonut.setDamage(model.getCurrentWeapon().getDamage()
				* ENEMY_DAMAGE_MODIFIER);
		newDonut.setAlive(true);
		RoomController currentRoom = getGameController().getRoomsController()
				.getCurrentRoom();
		currentRoom.addProjectile(newDonut);
	}

	/**
	 * Create a donut.
	 */
	void createDonut() {
		soundController
				.playSound(SoundEffectsController.SoundEffect.DONUT_FIRED);

		CharacterView view = (CharacterView) getView();
		view.runAttackAnimation();

		AbstractCharacterModel model = (AbstractCharacterModel) getModel();
		AbstractProjectileModel newDonut = new DonutModel();

		float heroAngle = (float) Math.toRadians(model.getRotation());
		float heroMiddleX = model.getX() + model.getWidth() / 2;
		float heroMiddleY = model.getY() + model.getHeight() / 2;

		float heroFaceX = heroMiddleX - (float) Math.cos(heroAngle)
				* ((model.getWidth()));
		float heroFaceY = heroMiddleY - (float) Math.sin(heroAngle)
				* ((model.getHeight()));

		newDonut.setX(heroFaceX - newDonut.getWidth() / 2);
		newDonut.setY(heroFaceY - newDonut.getHeight() / 2);

		newDonut.setRotation(model.getRotation());
		newDonut.setDamage(model.getCurrentWeapon().getDamage()
				* ENEMY_DAMAGE_MODIFIER);
		newDonut.setAlive(true);
		RoomController currentRoom = getGameController().getRoomsController()
				.getCurrentRoom();
		currentRoom.addProjectile(newDonut);
	}
}
