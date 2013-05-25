package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class ParkingLotRoomModelTest {
	private ParkingLotRoomModel parkingLotRoomModel;

	@Before
	public void setUp() throws Exception {
		parkingLotRoomModel = new ParkingLotRoomModel();
	}

	@Test
	public void testGetNpcModels() throws Exception {
		assertFalse(parkingLotRoomModel.getNpcModels().isEmpty());

	}

	@Test
	public void testGetUnlockedMapPath() throws Exception {
		assertNotNull(parkingLotRoomModel.getUnlockedMapPath());
	}

	@Test
	public void testGetLockedMapPath() throws Exception {
		assertNotNull(parkingLotRoomModel.getLockedMapPath());

	}
}
