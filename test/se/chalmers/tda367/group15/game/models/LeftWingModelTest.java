package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import se.chalmers.tda367.group15.game.models.rooms.LeftWingRoomModel;

public class LeftWingModelTest {
	private LeftWingRoomModel leftWingRoomModel;

	@Before
	public void setUp() throws Exception {
		leftWingRoomModel = new LeftWingRoomModel();
	}

	@Test
	public void testGetNpcModels() throws Exception {
		assertFalse(leftWingRoomModel.getNpcModels().isEmpty());

	}

	@Test
	public void testGetUnlockedMapPath() throws Exception {
		assertNotNull(leftWingRoomModel.getUnlockedMapPath());
	}

	@Test
	public void testGetLockedMapPath() throws Exception {
		assertNotNull(leftWingRoomModel.getLockedMapPath());

	}
}
