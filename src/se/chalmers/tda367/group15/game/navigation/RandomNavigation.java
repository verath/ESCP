package se.chalmers.tda367.group15.game.navigation;


public class RandomNavigation implements NpcNavigation {
	
	/**
	 * Creates a new random navigation controller.
	 */
	public RandomNavigation() {
	}
	
	/**
	 * {@inheritDoc}
	 */
	public float getNewX(float x, double rot, int delta, float speed){
		return (float) (x - Math.cos(Math.PI*rot/180) * delta * speed);
	}
	/**
	 * {@inheritDoc}
	 */
	public float getNewY(float y, double rot, int delta, float speed){
		return (float) (y - Math.sin(Math.PI*rot/180) * delta * speed);
	}
	/**
	 * {@inheritDoc}
	 */
	public double getNewDirection(){
		return Math.random()*360;
	}

}
