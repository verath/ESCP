package se.chalmers.tda367.group15.game.models;

import java.util.ArrayList;
import java.util.List;

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

	private List<Renderable> renderables = new ArrayList<Renderable>();
	private List<AnimatedModel> animatedObjs = new ArrayList<AnimatedModel>();
	private Renderable roomView, heroView;
	private final Room testroom = new BasicRoom();

	/**
	 * The app container for the currently running Slick2d game. This container
	 * allows us to start and stop the game.
	 */

	/**
	 * Constructor for the PsychoHero game.
	 * 
	 * @param gameContainer
	 *            The slick game container
	 */
	public SlickPsychoHero() {

		roomView = new RoomView(testroom);
		heroView = new HeroView();
		renderables.add(roomView);
		renderables.add(heroView);
		animatedObjs.add(((HeroView) heroView).getAnimatedModel());
	}

	@Override
	public List<Renderable> getRenderables() {
		return renderables;
	}

	@Override
	public List<AnimatedModel> getAnimatedModels() {
		return animatedObjs;
	}
}
