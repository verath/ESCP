package se.chalmers.tda367.group15.game.models;

/**
 * Class representing the entire game.
 * 
 * @author Peter
 * 
 */
public class PsychoHero implements PsychoHeroGame {
	private Thread gameThread;
	
	/**
	 * Starts the game
	 */
	public void start() {
		gameThread = new Thread(new GameThread());
		gameThread.start();
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
			//TODO: Do stuff here.
		}

	}
}
