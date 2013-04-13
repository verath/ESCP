package se.chalmers.tda367.group15.game.models;

/**
 * Class representing a Psycho Hero game using the Slick2d framework.
 * 
 * @author Peter
 * 
 */
public class SlickPsychoHero implements PsychoHeroGame {
	private Thread gameThread;
	private boolean isRunning = false;

	/**
	 * Constructor for the PsychoHero game.
	 */
	public SlickPsychoHero() {
		gameThread = new Thread(new GameThread());
	}

	/**
	 * Starts the game loop.
	 */
	public void start() {
		isRunning = true;
		gameThread.start();
	}

	/**
	 * Stops the game loop.
	 */
	public void stop() {
		isRunning = false;
	}

	/**
	 * An inner class representing the thread used to run the game logic.
	 * 
	 * @author Peter
	 * 
	 */
	private class GameThread implements Runnable {
		@Override
		public void run() {
			while (isRunning) {
				try {
					// TODO: do stuff here.
					Thread.sleep(20);
				} catch (InterruptedException e) {
				}

			}
		}

	}
}
