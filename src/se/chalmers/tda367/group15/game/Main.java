package se.chalmers.tda367.group15.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.controllers.PsychoHeroController;

/**
 * Starting point of the game.
 * 
 * @author Peter
 * 
 */
public class Main {

	public static void main(String[] args) {
	       try
	         {
	             AppGameContainer app = new AppGameContainer(new PsychoHeroController(Constants.GAME_NAME));
	             app.setDisplayMode(1024, 768, false);
	             app.start();
	         }
	         catch (SlickException e)
	         {
	             e.printStackTrace();
	         }	
	}
}
