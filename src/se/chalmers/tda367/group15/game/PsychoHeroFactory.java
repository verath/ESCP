package se.chalmers.tda367.group15.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.controllers.MainMenuState;
import se.chalmers.tda367.group15.game.controllers.PlayState;
import se.chalmers.tda367.group15.game.controllers.StateController;
import se.chalmers.tda367.group15.game.models.GameModel;
import se.chalmers.tda367.group15.game.models.Hero;
import se.chalmers.tda367.group15.game.views.GameView;
import se.chalmers.tda367.group15.game.views.HeroView;
import se.chalmers.tda367.group15.game.views.RoomView;
import se.chalmers.tda367.group15.game.views.room.RoomManager;

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
		
		GameView gameView = new GameView();
		GameModel gameModel = new GameModel();
		
		// Set up the room view and handler
		RoomManager roomManager = RoomManager.getInstance();
		RoomView roomView = new RoomView(roomManager);
		gameView.addView(roomView);
		
		// Set up the hero
		Hero heroModel = new Hero();
		HeroView heroView = new HeroView(heroModel);
		gameView.addView(heroView);
		gameModel.addModel(heroModel);
		
		PlayState theGameState = new PlayState(gameView, gameModel, Constants.PLAYING);
		MainMenuState theMainMenu = new MainMenuState( Constants.MAIN_MENU );
		
		// Create the state controller, add the states and set active state.
		StateController theStateController = new StateController(Constants.GAME_NAME);
		theStateController.addState(theGameState);
		theStateController.addState(theMainMenu);
		theStateController.enterState( Constants.MAIN_MENU );
		// Set up the container (this is kind of like the JFrame in swing)
		AppGameContainer gameContainer;
		try {
			gameContainer = new AppGameContainer(theStateController);
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