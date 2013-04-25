package se.chalmers.tda367.group15.game.models;


public class MovingModelTest {
	
	private static MovingModel testedModel;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testedModel = new MovingModel() {};
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
