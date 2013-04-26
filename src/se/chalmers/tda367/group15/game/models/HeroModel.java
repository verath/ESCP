package se.chalmers.tda367.group15.game.models;





/**
 * Class representing the model of a hero.
 * 
 * @author simon, Carl, tholene
 * 
 */

public class HeroModel extends AbstractMovingModel {
	/**
	 * Create a new Hero.
	 */
	public HeroModel() {
		setX(44f);
		setY(44f);
		setVelocity(0.15f);
		setWidth(64);
		setHeight(64);
		setCurrentWeapon(new PistolModel());
	}
}
