package se.chalmers.tda367.group15.game.models;

import se.chalmers.tda367.group15.game.models.tiles.FloorTile;
import se.chalmers.tda367.group15.game.models.tiles.ManiacTile;
import se.chalmers.tda367.group15.game.models.tiles.RockTile;
import se.chalmers.tda367.group15.game.models.tiles.Tiles;


public class TestMap {
	private ManiacTile maniac;
	private Tiles[][] tiles = new Tiles[1024 / 32][768 / 32];

	public TestMap() {
		maniac = new ManiacTile();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++)
				tiles[i][j] = new FloorTile();
		}
		
		for(int i = 0; i < tiles.length; i++) {
			tiles[i][0] = new RockTile();
			tiles[i][tiles[0].length - 1] = new RockTile();
		}
		
		for(int i = 0; i < tiles[0].length; i++) {
			tiles[0][i] = new RockTile();
			tiles[tiles.length - 1][i] = new RockTile();
		}
		
		for(int i = 1; i < 10; i++) {
			tiles[i][4] = new RockTile();
		}
		
		tiles[9][1] = new RockTile();
		tiles[9][3] = new RockTile();

		for (int i = 0; i < tiles.length; i++) {
			double x = 0;
			if (i > 0)
				x = tiles[i - 1][0].getX() + 32;
			for (int j = 0; j < tiles[0].length; j++) {
				double y = 0;
				if (j > 0)
					y = tiles[i][j - 1].getY() + 32;
				tiles[i][j].setX(x);
				tiles[i][j].setY(y);

			}
		}
		
	
	}

	public ManiacTile maniac() {
		return maniac;
	}
	
	public Tiles[][] tiles() {
		return tiles;
	}

}
