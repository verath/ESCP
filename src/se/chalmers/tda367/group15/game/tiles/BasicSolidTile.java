package se.chalmers.tda367.group15.game.tiles;

public class BasicSolidTile extends BasicTile {

	public BasicSolidTile(int id, int x, int y, int tileColour, int levelColour) {
		super(id, x, y, tileColour, levelColour);
		this.solid = true;
	}

}
