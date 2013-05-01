package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.event.AbstractEventListener;
import se.chalmers.tda367.group15.game.event.EventHandler;
import se.chalmers.tda367.group15.game.event.HeroDamagedEvent;
import se.chalmers.tda367.group15.game.models.HUDModel;
import se.chalmers.tda367.group15.game.views.HUDView;

/**
 * The controller for the HUD (ie. the information displayed "on top of" the
 * game, such as health and ammo)
 * 
 * @author Peter
 * 
 */
public class HUDController {
	private final HUDModel hudModel;
	private final HUDView hudView;
	
	public HUDController(EventHandler eventHandler) {
		eventHandler.addListener(new HeroDamageListener());
		hudModel = new HUDModel(100);
		hudView = new HUDView(hudModel);
	}
	
	
	private class HeroDamageListener extends AbstractEventListener<HeroDamagedEvent> {
		public HeroDamageListener() {
			super(HeroDamagedEvent.class);
		}
		@Override
		public void onEvent(HeroDamagedEvent event) {
			System.out.println("Damage man!");
			hudModel.setHealth(event.getCurrentHealth());
		}
	}
	
	/**
	 * Method for rendering all views.
	 * 
	 * @param container
	 *            The container holding this game.
	 * @param g
	 *            The graphics context that can be used to render. However,
	 *            normal rendering routines can also be used.
	 * @throws SlickException
	 *             Throw to indicate a internal error
	 */
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		hudView.render(container, g);
	}
}
