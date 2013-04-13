package se.chalmers.tda367.group15.game.controllers;

import java.util.List;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.tda367.group15.game.models.AnimatedObject;
import se.chalmers.tda367.group15.game.models.Player;
import se.chalmers.tda367.group15.game.models.PsychoHeroFactory;
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
	private List<AnimatedObject> animations;
	private TiledMap map;
	public PsychoHeroController(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
//		for(Renderable r: renderables) {
//			r.render();
//		}
		map.render(0, 0);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		game = PsychoHeroFactory.createPsychoHeroGame();
		renderables = game.getRenderables();
		animations = game.getAnimatedObjects();
		map = new TiledMap("res/levels/untitled.tmx");
		
	}

	@Override
	public void update(GameContainer cont, int delta) throws SlickException {
		for(AnimatedObject a: animations) {
			a.update(cont, delta);
		}

	}


}
