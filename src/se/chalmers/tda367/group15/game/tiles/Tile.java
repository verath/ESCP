package se.chalmers.tda367.group15.game.tiles;

import se.chalmers.tda367.group15.game.gfx.Colours;
import se.chalmers.tda367.group15.game.gfx.Screen;
import se.chalmers.tda367.group15.game.level.Level;

public abstract class Tile {
	
	// Colours
	public static final int WALL_COLOUR = Colours.get(000, -1, 110, -1);
	public static final int FLOOR_COLOUR = Colours.get(-1, 111, 332, -1);
	
	
	public static final Tile[] tiles = new Tile[256];
	
	// Basic tiles
	public static Tile VOID = new BasicSolidTile(0, 0, 0, Colours.get(000, -1, -1, -1), 0xff000000);
	public static Tile STONE = new BasicSolidTile(1, 1, 0, Colours.get(78, 333, 111, -1), 0xff555555);
	public static Tile GRASS = new BasicTile(2, 2, 0, Colours.get(-1, 78, 123, -1), 0xff00ff00);
	
	// Animated tiles
	public static final Tile WATER = new AnimatedTile(3, new int[][] {{0, 5}, {1, 5}, {2, 5}, {1, 5}}, 
							Colours.get(-1, 004, 115, -1), 0xff0000FF, 1000);
	
	// Office tiles
	public static final Tile TOP_LEFT_OR_BOTTOM_RIGHT_WALL = new BasicSolidTile(4, 0, 1, WALL_COLOUR, 0xffff0000);
	public static final Tile TOP_RIGHT_OR_BOTTOM_LEFT_WALL = new BasicSolidTile(5, 1, 1, WALL_COLOUR, 0xffff0001);
	public static final Tile BOTTOM_WALL = new BasicSolidTile(6, 2, 1, WALL_COLOUR, 0xffff0002);
	public static final Tile TOP_WALL = new BasicSolidTile(7, 3, 1, WALL_COLOUR, 0xffff0003);
	public static final Tile LEFT_WALL = new BasicSolidTile(8, 4, 1, WALL_COLOUR, 0xffff0004);
	public static final Tile RIGHT_WALL = new BasicSolidTile(9, 5, 1, WALL_COLOUR, 0xffff0005);

	public static final Tile FLOOR = new BasicTile(10, 6, 1, FLOOR_COLOUR, 0xffc8c864);
	
	
	protected byte id;
	protected boolean solid;
	private int levelColour;
	
	public Tile(int id, boolean isSolid, int levelColour) {
		this.id = (byte) id;
		if(tiles[id] != null) throw new RuntimeException("Duplicate tile id on " + id);
		this.solid = isSolid;
		this.levelColour = levelColour;
		tiles[id] = this;
		
	}
	
	public byte getId() {
		return id;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public int getLevelColour() {
		return levelColour;
	}
	
	public abstract void tick();
	
	public abstract void render(Screen screen, Level level, int x, int y);

}
