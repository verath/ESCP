package se.chalmers.tda367.group15.game.models;

/**
 * Class for representing a npc model.
 * 
 * @author simon
 * 
 */
public class AbstractNpcModel extends AbstractCharacterModel {

	/**
	 * Coordinates for pathfinding.
	 */
	private final int x1;
	private final int x2;
	private final int y1;
	private final int y2;

	/**
	 * Creates a new npc model.
	 * 
	 * @param x1
	 *            minimum x
	 * @param x2
	 *            maximum x
	 * @param y1
	 *            minimum y
	 * @param y2
	 *            maximum y
	 */
	AbstractNpcModel(int x1, int x2, int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	/**
	 * Method for getting minimum x.
	 * 
	 * @return min tile x
	 */
	public int getMinTileX() {
		return x1;
	}

	/**
	 * Method for getting maximum x.
	 * 
	 * @return max tile x.
	 */
	public int getMaxTileX() {
		return x2;
	}

	/**
	 * Method for getting minimum y.
	 * 
	 * @return min tile x.
	 */
	public int getMinTileY() {
		return y1;
	}

	/**
	 * Method for getting maximum y.
	 * 
	 * @return max tile y.
	 */
	public int getMaxTileY() {
		return y2;
	}

}
