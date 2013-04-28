package se.chalmers.tda367.group15.game.navigation;

public class RotatingNavigation implements NpcNavigation {

	public RotatingNavigation() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	public float getNewX(float x, double rot, int delta, float speed) {
		return (float) (x - Math.cos(Math.PI * rot / 180) * delta * speed);
	}

	/**
	 * {@inheritDoc}
	 */
	public float getNewY(float y, double rot, int delta, float speed) {
		return (float) (y - Math.sin(Math.PI * rot / 180) * delta * speed);
	}

	/**
	 * {@inheritDoc}
	 */
	public double getNewDirection(double prev, boolean collision) {
		return (prev + 1) % 360;
	}

}
