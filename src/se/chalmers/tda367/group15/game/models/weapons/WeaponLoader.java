package se.chalmers.tda367.group15.game.models.weapons;

import java.util.ArrayList;
import java.util.List;
/**
 * A class to initialize all the images for every weapon in the game.
 * @author tholene
 *
 */
public class WeaponLoader {
	
	/**
	 * Initialize the images for all the weapons currently implemented.
	 */
	public static void initWeapons() {
		List<Weapon> weapons = new ArrayList<Weapon>();
		
		weapons.add(new Unarmed());
		weapons.add(new Axe());	
		weapons.add(new Pistol());
		
		System.out.println("--Initializing loading of all weapon images--");
		for(Weapon w: weapons) {
			w.initAnimation();
		}
		System.out.println("--All images finished loading!--");
		
	}
}
