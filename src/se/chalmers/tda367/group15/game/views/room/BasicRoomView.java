package se.chalmers.tda367.group15.game.views.room;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.tda367.group15.game.models.AbstractRoomModel;
import se.chalmers.tda367.group15.game.models.room.BasicRoomModel;
import se.chalmers.tda367.group15.game.views.AbstractRoomView;

public class BasicRoomView extends AbstractRoomView {

	private static final String MAP_PATH = "res/levels/untitled.tmx";

	private BasicRoomModel roomModel;

	public BasicRoomView() {
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		getTiledMap().render(0, 0);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		TiledMap map = new TiledMap(MAP_PATH);
		super.setTiledMap(map);
		roomModel.generateCollisionBounds(map);
	}

	@Override
	public void setRoomModel(AbstractRoomModel roomModel) {
		this.roomModel = (BasicRoomModel) roomModel;
	}
}
