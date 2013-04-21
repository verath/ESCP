package se.chalmers.tda367.group15.game.models.room;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.tda367.group15.game.models.AbstractRoomModel;
import se.chalmers.tda367.group15.game.models.DummyEnemy;
import se.chalmers.tda367.group15.game.models.MovingModel;

/**
 * The model for a BasicRoom.
 * @author Peter
 *
 */
public class BasicRoomModel extends AbstractRoomModel {
	private List<MovingModel> movingModels = new ArrayList<MovingModel>();
	
	public BasicRoomModel() {
		DummyEnemy model = new DummyEnemy();
		movingModels.add(model);
	}

	@Override
	public List<MovingModel> getMovingModels() {
		return movingModels;
	}
}
