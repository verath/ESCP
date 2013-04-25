package se.chalmers.tda367.group15.game.models;

import se.chalmers.tda367.group15.game.models.weapons.Pistol;



/**
 * Class representing the model of a hero.
 * 
 * @author simon, Carl, tholene
 * 
 */

public class Hero extends MovingModel {

	private double rotation;


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

	/**
	 * Returns the angle the hero is currently facing in degrees. The angle
	 * starts from the left of the hero.
	 * 
	 * @return The angle in degrees
	 */
	public double getRotation() {
		return rotation;
	}
	
	/**
	 * Method for setting the rotation angle that the hero should be facing. 
	 * @param rotation
	 */
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
}
