package se.chalmers.tda367.group15.game.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;


public class LobbyRoomModelTest {
	private LobbyRoomModel lobbyRoomModel;

	@Before
	public void setUp() throws Exception {
		lobbyRoomModel = new LobbyRoomModel();
	}

	@Test
	public void testGetNpcModels() throws Exception {
		assertFalse(lobbyRoomModel.getNpcModels().isEmpty());

	}

	@Test
	public void testGetUnlockedMapPath() throws Exception {
		assertNotNull(lobbyRoomModel.getUnlockedMapPath());
	}

	@Test
	public void testGetLockedMapPath() throws Exception {
		assertNotNull(lobbyRoomModel.getLockedMapPath());

	}
}
