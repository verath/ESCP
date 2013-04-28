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
		setWidth(42);
		setHeight(42);
		setOffset(11);
		
		addWeapon(new UnarmedModel());
		addWeapon(new AxeModel());
		addWeapon(new PistolModel());
	}
}
