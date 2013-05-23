package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import se.chalmers.tda367.group15.game.models.rooms.WCRoomModel;

public class WCRoomModelTest {
	private WCRoomModel wcRoomModel;

	@Before
	public void setUp() throws Exception {
		wcRoomModel = new WCRoomModel();
	}

	@Test
	public void testGetNpcModels() throws Exception {
		assertFalse(wcRoomModel.getNpcModels().isEmpty());

	}

	@Test
	public void testGetUnlockedMapPath() throws Exception {
		assertNotNull(wcRoomModel.getUnlockedMapPath());
	}

	@Test
	public void testGetLockedMapPath() throws Exception {
		assertNotNull(wcRoomModel.getLockedMapPath());

	}
}
