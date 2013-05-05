package se.chalmers.tda367.group15.game.models;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;

/**
 * An abstract definition of a weapon in the game. Every weapon should be able
 * to fire.
 * 
 * @author tholene
 * 
 */
public abstract class AbstractWeaponModel {

	private String name;
	private boolean isEquipped;
	private int baseDamage;
	private int maxDamage;
	private int firingSpeed;

	private Random randomGenerator = new Random();

	/**
	 * Create a new weapon.
	 * 
	 * @param name
	 *            The name of the weapon.
	 * @param baseDamage
	 *            The base damage of the weapon.
	 * @param maxDamage
	 *            The maximum damage of the weapon.
	 * @param firingSpeed
	 *            The amount of time (in ms) that will pass between every shot
	 *            while firing.
	 * @param isEquipped
	 *            Is the weapon equipped or not?
	 */
	public AbstractWeaponModel(String name, int baseDamage, int maxDamage, int firingSpeed,
			boolean isEquipped) {
		this.name = name;
		this.baseDamage = baseDamage;
		this.maxDamage = maxDamage - baseDamage;
		this.setFiringSpeed(firingSpeed);
		this.isEquipped = isEquipped;
	}

	/**
	 * Equip the weapon.
	 */
	public void equip() {
		isEquipped = true;
	}

	/**
	 * Unequip the weapon.
	 */
	public void unequip() {
		isEquipped = false;
	}

	/**
	 * Get the name of the weapon.
	 * 
	 * @return the name of the weapon.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Check wether the weapon is equipped or not.
	 * 
	 * @return true if the weapon is currently equipped, else false.
	 */
	public boolean isEquipped() {
		return isEquipped;
	}

	/**
	 * Get the amount of damage that the weapon deals upon firing.
	 * 
	 * @return the damage that the weapon will deal.
	 */
	public int getDamage() {
		return baseDamage + randomGenerator.nextInt(maxDamage);
	}

	/**
	 * The image of the hero is changed dependning on the weapon he is currently
	 * using. This method will return the animation corresponding to the weapon
	 * calling the method due to polymorphism.
	 * 
	 * @return the animation for the weapon calling the method.
	 */
	public abstract Animation getAnimation();

	/**
	 * This method returns the images of a folder in an alphabetical order so
	 * that the animation runs correctly.
	 * 
	 * @param path
	 *            the name of the weapon's folder. Only provide the two last levels
	 *            of the hierarchy due to the implementation;
	 *            "res/animation/" + path.
	 * @return a sorted array of images corresponding to the path given.
	 * @throws SlickException
	 */
	protected Image[] sortImages(String path) {
		File folder = new File("res/animation/" + path);
		if (folder != null) {
			File[] files = folder.listFiles();
			if (files != null) {
				Arrays.sort(files);
				Image[] sortedImages = new Image[files.length];
				for (int i = 0; i < files.length; i++) {
					try {
						sortedImages[i] = new Image(files[i].getPath());
						
						if(Constants.DEBUG) {
							System.out.println(sortedImages[i]);
						}
					} catch (SlickException e) {
						e.printStackTrace();
					}
				}
				return sortedImages;
			}
		}
		return null;
	}

	protected abstract void initAnimation();

	public int getFiringSpeed() {
		return firingSpeed;
	}

	public void setFiringSpeed(int firingSpeed) {
		this.firingSpeed = firingSpeed;
	}
}
