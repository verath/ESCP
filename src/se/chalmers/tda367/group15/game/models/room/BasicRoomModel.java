package se.chalmers.tda367.group15.game.models.room;

import java.awt.geom.Rectangle2D.Float;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import se.chalmers.tda367.group15.game.models.AbstractRoomModel;

public class BasicRoomModel extends AbstractRoomModel {

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		// TODO Auto-generated method stub
	}

	@Override
	public Float getCollsionBounds() {
		return null;
	}

	@Override
	public void collide(List<Float> collisionBounds) {
		return;
	}


}
