
package se.chalmers.tda367.group15.game.main;

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
	 * The AppGameContainer associated with the current running SlickGame.
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
			return;
		} catch (org.lwjgl.openal.OpenALException e) {
			e.printStackTrace();
			System.out
					.println("caught OpenALException, probably about shutting down sound. Maybe your drives are outdated?");
			return;
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
