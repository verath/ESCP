package se.chalmers.tda367.group15.game;

import javax.swing.SwingUtilities;

import se.chalmers.tda367.group15.game.models.PsychoHero;
import se.chalmers.tda367.group15.game.models.PsychoHeroFactory;

/**
 * Starting point of the game.
 * 
 * @author Peter
 * 
 */
public class Main {

	public static void main(String[] args) {
		final PsychoHero psychoHero = PsychoHeroFactory.getPsychoHero();
		
		psychoHero.start();
	}
}
