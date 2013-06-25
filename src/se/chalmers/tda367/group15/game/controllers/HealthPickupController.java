package se.chalmers.tda367.group15.game.controllers;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.util.pathfinding.TileBasedMap;

import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.AbstractPickupModel;
import se.chalmers.tda367.group15.game.models.HeroModel;
import se.chalmers.tda367.group15.game.views.PickupView;

/**
 * A controller that manages health pickups in manners such as spawning the
 * pickup at a specified position and what happens when the pickup is run over.
 * 
 * @author Erik
 * 
 */
public class HealthPickupController extends AbstractMovingModelController {
	
	public HealthPickupController(AbstractPickupModel pickup, GameController gameController) {
		super(gameController);
		setModel(pickup);
		setView(new PickupView(pickup));
	}


	@Override
	public void render(GameContainer container, Graphics g) {
		getView().render(container, g);
	}


	@Override
	public void update(GameContainer container, int delta,
			List<Float> staticBounds,
			Map<AbstractMovingModel, Float> dynamicBounds) {
		
		AbstractMovingModel pickup = getModel();
		if(pickup.isAlive()) {
			
			for (AbstractMovingModel otherModel : dynamicBounds.keySet()) {
				if(otherModel instanceof HeroModel) {
					HeroModel hero = (HeroModel) otherModel;
					if(hero.getBounds().intersects(pickup.getBounds())) {
						int health = hero.getHealth() + 50;
						hero.setHealth(health >= 100 ? 100 : health);
						pickup.setAlive(false);
					}
					
				}
			
				
				
			}
		}
		
	}
}
