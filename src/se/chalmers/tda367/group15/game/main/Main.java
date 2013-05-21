package se.chalmers.tda367.group15.game.main;

import java.io.File;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.settings.Constants;
import se.chalmers.tda367.group15.game.states.StateController;

/**
 * Starting point of the game. Creates the StateController and initializes it.
 * 
 * @author Peter
 * 
 */
public class Main {

	public static void main(String[] args) {

		// lwjgl settings, see
		// http://www.lwjgl.org/wiki/index.php?title=LWJGL_Hidden_Switches
		System.setProperty("org.lwjgl.librarypath", new File("native/"
				+ LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("org.lwjgl.util.Debug",
				Boolean.toString(Constants.DEBUG));

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
