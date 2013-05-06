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
	 * @param eventHandler
	 */
	public HeroModel() {
		setX(44f);
		setY(44f);
		setVelocity(0.15f);
		setWidth(42);
		setHeight(42);
		setOffset(11);
		setHealth(100);
		setAlive(true);

		addWeapon(new UnarmedModel());
		addWeapon(new AxeModel());
		addWeapon(new PistolModel());
		setCurrentWeapon(getWeapons().get(0));
	}
}
