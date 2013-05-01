package se.chalmers.tda367.group15.game.models;

import se.chalmers.tda367.group15.game.event.EventHandler;
import se.chalmers.tda367.group15.game.event.HeroDamagedEvent;

/**
 * Class representing the model of a hero.
 * 
 * @author simon, Carl, tholene
 * 
 */

public class HeroModel extends AbstractMovingModel {

	private final EventHandler eventHandler;

	/**
	 * Create a new Hero.
	 * 
	 * @param eventHandler
	 */
	public HeroModel(EventHandler eventHandler) {
		setHealth(100);
		setX(44f);
		setY(44f);
		setVelocity(0.15f);
		setWidth(42);
		setHeight(42);
		setCurrentWeapon(new PistolModel());
		setOffset(11);
		this.eventHandler = eventHandler;
	}

	@Override
	public void takeDamage(int damage) {
		super.takeDamage(damage);
		eventHandler.publish(new HeroDamagedEvent(damage, getHealth()));
	}
}
