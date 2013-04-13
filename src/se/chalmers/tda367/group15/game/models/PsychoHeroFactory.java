package se.chalmers.tda367.group15.game.models;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.controllers.PsychoHeroController;

/**
 * Factory class for a PsychoHeroGame.
 * 
 * @author Peter
 * 
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

                // Set up our game based starting point, ie. the main controller
                PsychoHeroController slickGame = new PsychoHeroController(
                                Constants.GAME_NAME);

                // Set up the container (this is kind of like the JFrame if one would
                // have used swing)
                AppGameContainer gameContainer;
                try {
                        gameContainer = new AppGameContainer(slickGame);
                } catch (SlickException e) {
                        // TODO handle exception? Probably can't at this stage
                        e.printStackTrace();
                        return null;
                }

                // Finally set up our PsychoHeroGame representation for the slick
                // implementation
                SlickPsychoHero psychoHeroGame = new SlickPsychoHero(gameContainer);

                return psychoHeroGame;
        }

}