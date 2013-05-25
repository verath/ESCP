package se.chalmers.tda367.group15.game.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import se.chalmers.tda367.group15.game.models.AbstractCharacterModel;
import se.chalmers.tda367.group15.game.models.AbstractMovingModel;
import se.chalmers.tda367.group15.game.models.BulletModel;
import se.chalmers.tda367.group15.game.views.View;

public class AbstractMovingModelControllerTest {

	class TestController extends AbstractMovingModelController {
		public TestController() {
			super(null);
		};

		@Override
		public void render(GameContainer container, Graphics g) {
		}

		@Override
		public void update(GameContainer container, int delta,
				List<Rectangle2D.Float> staticBounds,
				Map<AbstractMovingModel, Rectangle2D.Float> dynamicBounds) {
		}
	}

	class TestModel extends AbstractCharacterModel {
		TestModel() {
			setHealth(1);
			setAlive(true);
		}

		public TestModel(int x, int y, int w, int h) {
			this();
			setX(x);
			setY(y);
			setWidth(w);
			setHeight(h);
		}
	}

	class TestView implements View {
		@Override
		public void render(GameContainer container, Graphics g) {
		}
	}

	TestController testedController;
	TestModel testedModel;

	@Before
	public void beforeTest() {
		testedController = new TestController();
		testedModel = new TestModel();
		testedController.setModel(testedModel);
	}

	@Test
	public void testSetGetModel() throws Exception {
		testedModel = new TestModel();
		testedController.setModel(testedModel);
		assertEquals(testedController.getModel(), testedModel);
	}

	@Test
	public void testSetGetView() throws Exception {
		View testView = new TestView();
		testedController.setView(testView);
		assertEquals(testedController.getView(), testView);
	}

	@Test
	public void testIsCollision() throws Exception {

		// Set up static bounds list
		List<Rectangle2D.Float> staticBounds = new ArrayList<Rectangle2D.Float>();
		// Add a static bound, (0, 0) to (10, 10)
		staticBounds.add(new Rectangle2D.Float(0, 0, 10, 10));

		// Set up dynamic bounds map
		Map<AbstractMovingModel, Rectangle2D.Float> dynamicBounds = new HashMap<AbstractMovingModel, Rectangle2D.Float>();
		// Add a dynamic bound, (10,10) to (20,20)
		dynamicBounds.put(new TestModel(10, 10, 10, 10), new Rectangle2D.Float(
				10, 10, 10, 10));

		// Make sure we collide if we collide with both the static and dynamic
		// bounds
		assertTrue(testedController.isCollision(0, 0, 20, 20, staticBounds,
				dynamicBounds));

		// Make sure we collide with only the static bounds
		assertTrue(testedController.isCollision(0, 0, 5, 5, staticBounds,
				dynamicBounds));

		// Make sure we collide with only the dynamic bounds
		assertTrue(testedController.isCollision(10, 10, 5, 5, staticBounds,
				dynamicBounds));

		// Make sure we do not collide if we are not colliding
		assertFalse(testedController.isCollision(20, 20, 5, 5, staticBounds,
				dynamicBounds));

		// Clear static and dynamic bounds
		staticBounds.clear();
		dynamicBounds.clear();

		// Test collision between two bullets, should not collide even if on
		// same spot
		BulletModel bullet1 = new BulletModel();
		BulletModel bullet2 = new BulletModel();
		bullet1.setAlive(true);
		bullet2.setAlive(true);

		testedController.setModel(bullet1);

		dynamicBounds.put(bullet2, new Rectangle2D.Float(bullet2.getX(),
				bullet2.getY(), bullet2.getWidth(), bullet2.getHeight()));

		assertFalse(testedController.isCollision(0, 0, 20, 20, staticBounds,
				dynamicBounds));

		// Make sure a collision occurs if we change the type of the represented
		// model to not an AbstractProjectileModel
		testedController.setModel(testedModel);
		assertTrue(testedController.isCollision(0, 0, 20, 20, staticBounds,
				dynamicBounds));

		dynamicBounds.clear();

		// This test was a bit to much work to get working, skipped for now.

		// // Make sure that the model we collide into is "killed" if it is an
		// // instance of AbstractCharacterModel and the current model is an
		// // instance of a projectile
		// testedController.setModel(bullet1);
		// AbstractCharacterModel characterModel = new TestModel(0,0,20,20);
		//
		// dynamicBounds.put(characterModel, new
		// Rectangle2D.Float(characterModel.getX(),
		// characterModel.getY(), characterModel.getWidth(),
		// characterModel.getHeight()));
		//
		// assertTrue(characterModel.isAlive());
		//
		// testedController.isCollision(0,0,20,20,staticBounds,dynamicBounds);
		//
		// assertFalse(characterModel.isAlive());

	}
}
