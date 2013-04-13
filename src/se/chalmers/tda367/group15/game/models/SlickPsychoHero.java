package se.chalmers.tda367.group15.game.models;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Class representing a Psycho Hero game using the Slick2d framework.
 * 
 * @author Peter
 * 
 */
public class SlickPsychoHero implements PsychoHeroGame {

	/**
	 * The app container for the currently running Slick2d game. This container
	 * allows us to start and stop the game.
	 */
	private final AppGameContainer gameContainer;

	/**
	 * Constructor for the PsychoHero game.
	 * 
	 * @param gameContainer
	 *            The slick game container
	 */
	public SlickPsychoHero(final AppGameContainer gameContainer) {
		this.gameContainer = gameContainer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start() {
		try {
			gameContainer.start();
		} catch (SlickException e) {
			// TODO handle exception?
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop() {
		gameContainer.exit();
	}
}
