package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.*;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class MovingModelTest {
	
	private static MovingModel testedModel;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testedModel = new MovingModel() {
			@Override
			public List<Float> getCollisionBounds() {
				return null;
			}
			
			@Override
			public void update(GameContainer container, int delta)
					throws SlickException {
			}
			
			@Override
			public void collide(List<Float> collisionBounds) {
			}
		};
	}
	
	@Test
	public void testSetX() {
		testedModel.setX(10);
		assertTrue(testedModel.getX() == 10);
	}
	
	@Test
	public void testSetY() {
		testedModel.setY(10);
		assertTrue(testedModel.getY() == 10);
	}
	
	@Test
	public void testSetVelocity() {
		testedModel.setVelocity(10);
		assertTrue(testedModel.getVelocity() == 10);
	}

}
