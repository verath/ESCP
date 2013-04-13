package se.chalmers.tda367.group15.game.views;

import se.chalmers.tda367.group15.game.models.AnimatedModel;
import se.chalmers.tda367.group15.game.models.Hero;

public class HeroView implements Renderable, AnimatedModelView{

	private Hero hero;

	/**
	 * Create a new hero view.
	 */
	public HeroView() {
		hero = new Hero();
	}

	@Override
	public void render() {
		hero.getCurrentSprite().draw(hero.getX(), hero.getY());

	}

	@Override
	public AnimatedModel getAnimatedModel() {
		return hero;
	}

}
