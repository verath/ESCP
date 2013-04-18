package se.chalmers.tda367.group15.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.controllers.StateController;


/**
 * Factory class for a PsychoHeroGame.
 * 
 * @author Peter
 * s
 */
public class PsychoHeroFactory {

	/**
	 * Creates a new PsychoHero game.
	 * 
	 * @return A game implementing the PsychoHeroGame interface (suitable for
	 *         the input parameter). Returns null if a game could not be
	 *         created.
	 */
	// TODO allow for specifying arguments to return a different PsychoHeroGame
	public static PsychoHeroGame createPsychoHeroGame() {
		
		// Create the state controller, add the states and set active state.
		StateController stateController = new StateController(Constants.GAME_NAME);

		// Set up the container (this is kind of like the JFrame in swing)
		AppGameContainer gameContainer;
		try {
			gameContainer = new AppGameContainer(stateController);
			gameContainer.setVerbose(Constants.DEBUG);
			gameContainer.setTargetFrameRate(120);
			// TODO Allow for changing this resolution
			gameContainer.setDisplayMode(1024, 768, false);
		} catch (SlickException e) {
			// TODO handle exception? Probably can't at this stage
			e.printStackTrace();
			return null;
		}

		SlickPsychoHero psychoHeroGame = new SlickPsychoHero(gameContainer);

		return psychoHeroGame;
	}
}