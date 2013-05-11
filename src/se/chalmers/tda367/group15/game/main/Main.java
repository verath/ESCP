package se.chalmers.tda367.group15.game.main;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.states.StateController;

/**
 * Starting point of the game.
 * 
 * @author Peter
 * 
 */
public class Main {

	public static void main(String[] args) {
		// Create the state controller and let it do the rest of the work.
		StateController stateController = new StateController(
				Constants.GAME_NAME);

		// Create the window.
		stateController.initAppContainer(false);

		// Get the container
		AppGameContainer gameContainer = stateController.getAppGameContainer();

		try {
			gameContainer.start();
		} catch (SlickException e) {
			if (Constants.DEBUG) {
				e.printStackTrace();
			}
		} catch (org.lwjgl.openal.OpenALException e) {
			System.err
					.println("An OpenALException occurred. Make sure your audio drivers are updated!");
		}
	}
}