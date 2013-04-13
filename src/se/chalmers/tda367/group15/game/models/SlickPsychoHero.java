package se.chalmers.tda367.group15.game.models;

import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.constants.Constants;
import se.chalmers.tda367.group15.game.models.room.BasicRoom;
import se.chalmers.tda367.group15.game.views.HeroView;
import se.chalmers.tda367.group15.game.views.Renderable;
import se.chalmers.tda367.group15.game.views.RoomView;

/**
 * Class representing a Psycho Hero game using the Slick2d framework.
 * 
 * @author Peter
 * 
 */
public class SlickPsychoHero implements PsychoHeroGame {

	private List<Renderable> renderables;
	private Renderable roomView, heroView;
	private final Room testroom = new BasicRoom();
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
		roomView = new RoomView(testroom);
		heroView = new HeroView();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start() {	
		// If we are debugging, PRINT THEM INFOS!
		gameContainer.setVerbose(Constants.DEBUG);
		
		// TODO: Allow for changing this resolution
		try {
			gameContainer.setDisplayMode(1024, 768, false);
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		try {
			gameContainer.start();
		} catch (SlickException e) {
			// TODO handle exception?
			e.printStackTrace();
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
	
	public List<Renderable> getRenderables() {
		return renderables;
	}

	public List<AnimatedObject> getAnimatedObjects() {
		return null;
	}
}
