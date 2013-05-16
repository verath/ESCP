package se.chalmers.tda367.group15.game.models;

/**
 * Class representing the model of a hero.
 * 
 * @author simon, Carl, tholene
 * 
 */

public class HeroModel extends AbstractCharacterModel {

	/**
	 * Create a new Hero.
	 *
     */
	public HeroModel() {
		setWidth(42);
		setHeight(42);
		setOffset(11);
		setAnimationPath("hero");

	}
}
