package se.chalmers.tda367.group15.game.models;

/**
 * Factory class for the PsychoHero class.
 * @author Peter
 *
 */
public class PsychoHeroFactory {

	/**
	 * Creates a new PsychoHero game.
	 * @return
	 */
	// TODO allow for specifying arguments to return a different PHGame
	public static PsychoHeroGame createPsychoHeroGame() {
		return new PsychoHero();
	}

}
