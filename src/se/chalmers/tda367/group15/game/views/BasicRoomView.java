package se.chalmers.tda367.group15.game.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


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

	public BasicRoomView() {
		super(MAP_PATH);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		getTiledMap().render(0, 0);
	}

}
