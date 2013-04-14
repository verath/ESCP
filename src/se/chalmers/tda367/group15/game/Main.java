package se.chalmers.tda367.group15.game;


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