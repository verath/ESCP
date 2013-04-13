package se.chalmers.tda367.group15.game.views;

import se.chalmers.tda367.group15.game.models.Hero;
import se.chalmers.tda367.group15.game.models.Player;

public class HeroView implements Renderable {
	
	private Player hero;
	public HeroView() {
		hero = new Hero();
	}
	@Override
	public void render() {
		hero.getSprite().draw(hero.getX(), hero.getY());

	}

}
