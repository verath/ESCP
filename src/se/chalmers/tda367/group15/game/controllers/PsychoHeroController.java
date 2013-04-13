package se.chalmers.tda367.group15.game.controllers;

import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.AnimatedModel;
import se.chalmers.tda367.group15.game.models.PsychoHeroGame;
import se.chalmers.tda367.group15.game.models.SlickPsychoHero;
import se.chalmers.tda367.group15.game.views.Renderable;

/**
 * The main controller for the slick2d implementation of PsychoHero.
 * 
 * @author Peter
 * 
 */
public class PsychoHeroController extends BasicGame {

	private PsychoHeroGame game;
	private List<Renderable> renderables;
	private List<AnimatedModel> animations;
	public PsychoHeroController(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		for(Renderable r: renderables) {
			r.render();
		}
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		game = new SlickPsychoHero();
		renderables = game.getRenderables();
		animations = game.getAnimatedModels();
	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		for(AnimatedModel a: animations) {
			a.update(cont, delta);
		}

	}


}
