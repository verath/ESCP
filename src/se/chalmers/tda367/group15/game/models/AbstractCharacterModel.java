package se.chalmers.tda367.group15.game.models;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.group15.game.models.weapons.AbstractWeaponModel;

/**
 * Class for representing a moving character model.
 * 
 * @author simon
 */
public class AbstractCharacterModel extends AbstractMovingModel {
	/**
	 * A list of the character's weapon arsenal
	 */
	private final List<AbstractWeaponModel> weapons = new ArrayList<AbstractWeaponModel>();
	/**
	 * The currently equipped weapon
	 */
	private AbstractWeaponModel currentWeapon;

	private String animationPath;

	/**
	 * Method for getting the current weapon.
	 * 
	 * @return the current weapon
	 */
	public AbstractWeaponModel getCurrentWeapon() {
		return currentWeapon;
	}

	/**
	 * Method for setting the current weapon.
	 * 
	 * @param weapon
	 *            the new weapon to be set as current
	 */
	public void setCurrentWeapon(AbstractWeaponModel weapon) {
		currentWeapon = weapon;
	}

	/**
	 * Add another weapon to the arsenal of the hero.
	 * 
	 * @param weapon
	 *            the weapon to be added
	 */
	public void addWeapon(AbstractWeaponModel weapon) {
		weapons.add(weapon);
	}

	/**
	 * Get the current weapons of the hero
	 * 
	 * @return a list of all the weapons that the hero currently has available
	 */
	public List<AbstractWeaponModel> getWeapons() {
		return weapons;
	}

	public String getAnimationPath() {
		return animationPath;
	}

	void setAnimationPath(String path) {
		this.animationPath = path;
	}
}
