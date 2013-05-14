package se.chalmers.tda367.group15.game.models;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.group15.game.settings.Constants;

/**
 * A class to initialize all the images for every weapon in the game.
 * 
 * @author tholene
 * 
 */
public class WeaponLoader {

	/**
	 * Initialize the images for all the weapons currently implemented.
	 */
	public static void initWeapons() {
		List<AbstractWeaponModel> weapons = new ArrayList<AbstractWeaponModel>();

		weapons.add(new UnarmedModel());
		weapons.add(new AxeModel());
		weapons.add(new PistolModel());
		
		if (Constants.DEBUG) {
			System.out.println("--Initializing loading of all weapon images--");
		}
		
		for (AbstractWeaponModel w : weapons) {
			w.initAnimation();
		}

		if (Constants.DEBUG) {
			System.out.println("--All images finished loading!--");
		}

	}
}
