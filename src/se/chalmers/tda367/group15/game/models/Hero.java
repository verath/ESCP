package se.chalmers.tda367.group15.game.models;

import se.chalmers.tda367.group15.game.models.weapons.Pistol;



/**
 * Class representing the model of a hero.
 * 
 * @author simon, Carl, tholene
 * 
 */

public class Hero extends MovingModel {
	/**
	 * Create a new Hero.
	 */
	public Hero() {

		setX(44f);
		setY(44f);
		setVelocity(0.15f);
		setWidth(64);
		setHeight(64);
		setCurrentWeapon(new Pistol());
	}
}
