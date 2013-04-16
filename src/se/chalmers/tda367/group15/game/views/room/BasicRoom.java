package se.chalmers.tda367.group15.game.views.room;

import se.chalmers.tda367.group15.game.models.Room;

public class BasicRoom extends Room {

	private static final String MAP_PATH = "res/levels/untitled.tmx";

	public BasicRoom() {
		super(MAP_PATH);
	}

}
