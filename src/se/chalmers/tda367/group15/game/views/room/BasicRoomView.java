package se.chalmers.tda367.group15.game.views.room;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import se.chalmers.tda367.group15.game.models.AbstractRoomModel;
import se.chalmers.tda367.group15.game.models.room.BasicRoomModel;
import se.chalmers.tda367.group15.game.views.AbstractRoomView;

/**
 * A view for the BasicRoom.
 * 
 * @author Peter
 * 
 */
public class BasicRoomView extends AbstractRoomView {
	/**
	 * Location of the map file used for this room.
	 */
	private static final String MAP_PATH = "res/levels/untitled.tmx";

	/**
	 * The model this view is representing.
	 */
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
		// Load the map now that we have OpenGL
		TiledMap map = new TiledMap(MAP_PATH);
		super.setTiledMap(map);
		// Make sure we also forward it to our model, so that it can generate
		// collisionBounds
		roomModel.generateCollisionBounds(map);
	}

	@Override
	public void setRoomModel(AbstractRoomModel roomModel) {
		if (roomModel instanceof BasicRoomModel) {
			this.roomModel = (BasicRoomModel) roomModel;
		} else {
			throw new IllegalArgumentException(
					"The roomModel provided was not the expected class.");
		}
	}
}
