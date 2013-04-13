package se.chalmers.tda367.group15.game;

import se.chalmers.tda367.group15.game.models.PsychoHeroFactory;
import se.chalmers.tda367.group15.game.models.PsychoHeroGame;

/**
 * Starting point of the game.
 * 
 * @author Peter
 * 
 */
public class Main {

        public static void main(String[] args) {
                final PsychoHeroGame game = PsychoHeroFactory.createPsychoHeroGame();
                
                game.start();
        }
}