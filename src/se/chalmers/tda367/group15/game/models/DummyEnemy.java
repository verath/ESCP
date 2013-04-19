package se.chalmers.tda367.group15.game.models;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Float;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class DummyEnemy extends MovingModel {

	public DummyEnemy() {
		setX(32f);
		setY(128f);
		setVelocity(0.1f);
		setWidth(32);
		setHeight(32);
	}

}
