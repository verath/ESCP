package se.chalmers.tda367.group15.game.models;

public class AbstractNpcModel extends AbstractCharacterModel {

	private int x1, x2, y1, y2;
	public AbstractNpcModel(int x1, int x2, int y1, int y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public int getMinTileX() {
		return x1;
	}
	
	public int getMaxTileX() {
		return x2;
	}
	
	public int getMinTileY() {
		return y1;
	}
	
	public int getMaxTileY() {
		return y2;
	}
	
	
}
