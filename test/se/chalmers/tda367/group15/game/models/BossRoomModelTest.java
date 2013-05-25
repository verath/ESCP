package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class BossRoomModelTest {
	private BossRoomModel bossRoomModel;

	@Before
	public void setUp() throws Exception {
		bossRoomModel = new BossRoomModel();
	}

	@Test
	public void testGetNpcModels() throws Exception {
		assertFalse(bossRoomModel.getNpcModels().isEmpty());

	}

	@Test
	public void testGetUnlockedMapPath() throws Exception {
		assertNotNull(bossRoomModel.getUnlockedMapPath());
	}

	@Test
	public void testGetLockedMapPath() throws Exception {
		assertNotNull(bossRoomModel.getLockedMapPath());

	}
}
