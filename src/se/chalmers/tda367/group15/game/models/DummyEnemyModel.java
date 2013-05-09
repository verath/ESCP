package se.chalmers.tda367.group15.game.models;

import java.io.File;
import java.util.Arrays;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Simple class for representing a dummy enemy. This enemy is only intended for
 * testing.
 * 
 * @author simon
 * 
 */
public class DummyEnemyModel extends AbstractCharacterModel {

	/**
	 * Creates a new dummy enemy.
	 */
	public DummyEnemyModel() {
		this(64f, 128f);
	}
	
	public DummyEnemyModel(float x, float y){
		this(x, y, 360*Math.random());
	}
	
	/**
	 * Creates a new dummy enemy.
	 * @param x x position of DummyEnemyModel
	 * @param y y position of DummyEnemyModel
	 * @param rot angle to face in beginning
	 */
	public DummyEnemyModel(float x, float y, double rot) {
		setX(x);
		setY(y);
		setVelocity(0.1f);
		setWidth(42);
		setHeight(42);
		setRotation(rot);
		setOffset(11);
		setAlive(true);
		setHealth(100);
	}
	
	public Animation getDeathAnimation() {
		File folder = new File("res/animation/enemy/coworker/death");
		File[] files = folder.listFiles();
		Arrays.sort(files);
		Image[] image = new Image[files.length];

		for (int i = 0; i < files.length; i++) {
			try {
				image[i] = new Image(files[i].getPath());
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		return new Animation(image, 50, true);
	}
}
