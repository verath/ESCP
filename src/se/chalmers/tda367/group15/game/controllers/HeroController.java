package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;

import se.chalmers.tda367.group15.game.controllers.SoundController.SoundEffect;
import se.chalmers.tda367.group15.game.models.AbstractCharacterModel;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.AbstractProjectileModel;
import se.chalmers.tda367.group15.game.models.BulletModel;
import se.chalmers.tda367.group15.game.models.HeroModel;
import se.chalmers.tda367.group15.game.models.MeleeSwingModel;
import se.chalmers.tda367.group15.game.models.weapons.AbstractMeleeWeaponModel;
import se.chalmers.tda367.group15.game.models.weapons.AbstractRangedWeaponModel;
import se.chalmers.tda367.group15.game.models.weapons.AbstractWeaponModel;
import se.chalmers.tda367.group15.game.models.weapons.AxeModel;
import se.chalmers.tda367.group15.game.models.weapons.PistolModel;
import se.chalmers.tda367.group15.game.models.weapons.UnarmedModel;
import se.chalmers.tda367.group15.game.settings.KeyBindings;
import se.chalmers.tda367.group15.game.settings.KeyBindings.Key;
import se.chalmers.tda367.group15.game.views.CharacterView;

/**
 * Class for controlling the hero.
 * 
 * @author Carl, Peter, Simon, Erik
 *
 */
public class HeroController extends AbstractMovingModelController {

	private long swingTimer = 0;
	private final SoundController soundController = SoundController
			.instance();

	/**
	 * Create a new controller for the hero.
	 * 
	 * @param gameController
	 *            The gameController instance.
	 * 
	 */
	public HeroController(GameController gameController) {
		super(gameController);

		HeroModel model = new HeroModel();

		// configure model
		model.setX(2 * 32f);
		model.setY(21 * 32f);
		model.setVelocity(0.2f);
		model.setHealth(100);
		model.setAlive(true);

		// add weapons

		model.addWeapon(new UnarmedModel());
		model.addWeapon(new AxeModel());
		model.addWeapon(new PistolModel());
		model.setCurrentWeapon(model.getWeapons().get(0));

		setModel(model);
		setView(new CharacterView(model));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds) {

		AbstractCharacterModel model = (AbstractCharacterModel) getModel();

		if (!model.isAlive()) {
			getGameController().gameOver(false);
			return;
		}

		Input input = container.getInput();

		// Handle changing of weapons
		changeWeapons(input, model);

		// Handle firing of weapon
		fireWeapon(input, model);

		// Make the model face the mouse cross-hair
		calculateFacing(input, model);

		// Calculate the new x and y
		Point nextPosition = calculateNextPosition(input, model, delta);

		// Check the new X against collision bounds. If no collision, allow
		// movement in X.
		if (!isCollision(nextPosition.getX(), model.getY(), model.getHeight(),
				model.getWidth(), staticBounds, dynamicBounds)) {
			model.setX(nextPosition.getX());
		}

		// Check the new Y against collision bounds. If no collision, allow
		// movement in Y.
		if (!isCollision(model.getX(), nextPosition.getY(), model.getHeight(),
				model.getWidth(), staticBounds, dynamicBounds)) {
			model.setY(nextPosition.getY());
		}

		// Handle checking for change of room.
		changeRoom(model);

	}

	/**
	 * Checks the current location of the model against pre-defined room
	 * borders. If the model is outside of the room border, move the game field
	 * to the next room according to what direction the hero went outside.
	 * 
	 * @param model
	 *            Model to calculate facing for.
	 */
	private void changeRoom(AbstractCharacterModel model) {
		RoomsController roomsController = getGameController()
				.getRoomsController();
		float tmpX = model.getX() + model.getWidth() / 2;
		float tmpY = model.getY() + model.getHeight() / 2;

		if (tmpX <= 0) {
			roomsController.moveLeft();
			model.setX(1024 - model.getWidth() - 32);
		} else if (tmpX >= 1024) {
			roomsController.moveRight();
			model.setX(32);
		} else if (tmpY <= 0) {
			roomsController.moveUp();
			model.setY(768 - model.getHeight() - 32);
		} else if (tmpY >= 768) {
			roomsController.moveDown();
			model.setY(32);
		}
	}

	/**
	 * Calculates the next position the model would be at by checking the user
	 * input and the model's movement speed. Also sets the model's isMoving
	 * property.
	 * 
	 * 
	 * @param input
	 *            Input object to mouse position against.
	 * @param model
	 *            Model to calculate facing for.
	 * @param delta
	 *            The time in milliseconds since the last update.
	 * @return The next position the model would be in.
	 */
	private Point calculateNextPosition(Input input,
			AbstractCharacterModel model, int delta) {
		boolean goingUp = input.isKeyDown(KeyBindings.getBinding(Key.UP));
		boolean goingDown = input.isKeyDown(KeyBindings.getBinding(Key.DOWN));
		boolean goingRight = input.isKeyDown(KeyBindings.getBinding(Key.RIGHT));
		boolean goingLeft = input.isKeyDown(KeyBindings.getBinding(Key.LEFT));

		// Calculate move direction and move
		float speedY = (goingUp ? 1 : 0) - (goingDown ? 1 : 0);
		float speedX = (goingLeft ? 1 : 0) - (goingRight ? 1 : 0);

		if (speedY != 0 || speedX != 0) {
			double direction = Math.atan2(speedY, speedX);
			speedY = (float) (model.getVelocity() * Math.sin(direction));
			speedX = (float) (model.getVelocity() * Math.cos(direction));
		}

		float newX = model.getX() - (delta * speedX);
		float newY = model.getY() - (delta * speedY);

		model.setMoving(speedY != 0 || speedX != 0);

		return new Point(newX, newY);
	}

	/**
	 * Calculate facing depending on where the mouse is relative to the center
	 * of the model.
	 * 
	 * @param input
	 *            Input object to mouse position against.
	 * @param model
	 *            Model to calculate facing for.
	 */
	private void calculateFacing(Input input, AbstractCharacterModel model) {

		float mouseX = input.getMouseX();
		float mouseY = input.getMouseY();
		float yDiff = (model.getHeight() / 2 + model.getY() - mouseY);
		float xDiff = (model.getWidth() / 2 + model.getX() - mouseX);
		model.setRotation(Math.toDegrees(Math.atan2(yDiff, xDiff)));
	}

	/**
	 * Handles firing of weapons depending on if the mouse is clicked and what
	 * weapon is equipped.
	 * 
	 * @param input
	 *            Input object to check key presses against.
	 * @param model
	 *            Model to fire weapons for.
	 */
	private void fireWeapon(Input input, AbstractCharacterModel model) {
		AbstractWeaponModel weapon = model.getCurrentWeapon();

		// If last attack was less than the weapons allowed speed
		boolean weaponCanFireAgain = (System.currentTimeMillis() - swingTimer) > model
				.getCurrentWeapon().getFiringSpeed();
		// If we can spam-attack again, this is 1/4th of the time of
		// hold-and-shot
		boolean weaponCanSpamAgain = (System.currentTimeMillis() - swingTimer) > model
				.getCurrentWeapon().getFiringSpeed() / 4;

		// Get status of mouse button
		boolean isMousePressed = input.isMousePressed(Input.MOUSE_LEFT_BUTTON);
		boolean isMouseDown = input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);

		// Get type of weapon currently equipped
		boolean isRangedWeapon = weapon instanceof AbstractRangedWeaponModel;
		boolean isMeleeWeapon = weapon instanceof AbstractMeleeWeaponModel;

		// Fire bullets if mouse clicked and a ranged weapon is equipped, or
		// swing weapon if melee weapon.
		if (isMousePressed && weaponCanSpamAgain || isMouseDown
				&& weaponCanFireAgain) {
			if (isRangedWeapon) {
				createBullet();
			} else if (isMeleeWeapon) {
				swingWeapon();
			}
			swingTimer = System.currentTimeMillis();
		}
	}

	/**
	 * Checks if a key bind for changing weapon is pressed, if so switches to
	 * the appropriate weapon slot.
	 * 
	 * @param input
	 *            Input object to check key presses against.
	 * @param model
	 *            Model to change weapons for.
	 */
	private void changeWeapons(Input input, AbstractCharacterModel model) {
		if (input.isKeyPressed(KeyBindings.getBinding(Key.WEAPON_1))) {
			model.setCurrentWeapon(model.getWeapons().get(0));
		} else if (input.isKeyPressed(KeyBindings.getBinding(Key.WEAPON_2))) {
			model.setCurrentWeapon(model.getWeapons().get(1));
		} else if (input.isKeyPressed(KeyBindings.getBinding(Key.WEAPON_3))) {
			model.setCurrentWeapon(model.getWeapons().get(2));
		}
	}

	private void createBullet() {
		soundController
				.playSound(SoundController.SoundEffect.PISTOL_FIRED);
		AbstractCharacterModel model = (AbstractCharacterModel) getModel();
		AbstractProjectileModel newBullet = new BulletModel();

		float heroAngle = (float) Math.toRadians(model.getRotation());
		float heroMiddleX = model.getX() + model.getWidth() / 2;
		float heroMiddleY = model.getY() + model.getHeight() / 2;

		float heroFaceX = heroMiddleX - (float) Math.cos(heroAngle)
				* ((model.getWidth()));
		float heroFaceY = heroMiddleY - (float) Math.sin(heroAngle)
				* ((model.getHeight()));

		newBullet.setX(heroFaceX - newBullet.getWidth() / 2);
		newBullet.setY(heroFaceY - newBullet.getHeight() / 2);

		newBullet.setRotation(model.getRotation());
		newBullet.setDamage(model.getCurrentWeapon().getDamage());
		newBullet.setAlive(true);
		RoomController currentRoom = getGameController().getRoomsController()
				.getCurrentRoom();
		currentRoom.addProjectile(newBullet);
	}

	private void swingWeapon() {
		AbstractCharacterModel model = (AbstractCharacterModel) getModel();

		// Run the swinging animation for the weapon
		CharacterView view = (CharacterView) getView();
		view.runAttackAnimation();

		AbstractProjectileModel newSwing = new MeleeSwingModel();
		if (model.getCurrentWeapon() instanceof AxeModel) {
			soundController.playSound(SoundEffect.AXE_SWING);
		} else {
			soundController.playSound(SoundEffect.UNARMED_SMASH);
		}

		float heroAngle = (float) Math.toRadians(model.getRotation());
		float heroMiddleX = model.getX() + model.getWidth() / 2;
		float heroMiddleY = model.getY() + model.getHeight() / 2;

		float heroFaceX = heroMiddleX - (float) Math.cos(heroAngle)
				* ((model.getWidth()));
		float heroFaceY = heroMiddleY - (float) Math.sin(heroAngle)
				* ((model.getHeight()));

		newSwing.setX(heroFaceX - newSwing.getWidth() / 2);
		newSwing.setY(heroFaceY - newSwing.getHeight() / 2);
		newSwing.setRotation(model.getRotation());
		newSwing.setDamage(model.getCurrentWeapon().getDamage());
		newSwing.setAlive(true);

		RoomController currentRoom = getGameController().getRoomsController()
				.getCurrentRoom();

		currentRoom.addSwing(newSwing);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g) {
		getView().render(container, g);
	}
}
