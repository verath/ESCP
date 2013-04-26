package se.chalmers.tda367.group15.game.main;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.controllers.StateController;

/**
 * Factory class for a PsychoHeroGame.
 * 
 * @author Peter, Carl
 */
public class PsychoHeroFactory {

	/**
	 * Creates a new PsychoHero game.
	 * 
	 * @return A game implementing the PsychoHeroGame interface (suitable for
	 *         the input parameter).
	 */
	public static PsychoHeroGame createPsychoHeroGame() {

		// Create the state controller and let it do the rest of the work.
		StateController stateController = new StateController(
				Constants.GAME_NAME);
		// Create the window.
		stateController.initAppContainer( false );

		// Get the window so it can be returned.
		SlickPsychoHero psychoHeroGame = new SlickPsychoHero(
				stateController.getTheAppContainer());

		return psychoHeroGame;
	}
}