package se.chalmers.tda367.group15.game.views.room;

import se.chalmers.tda367.group15.game.models.Room;

public class BasicRoom implements Room {

	@Override
	public String getTiledMapPath() {
		return "res/levels/untitled.tmx";
	}

}
