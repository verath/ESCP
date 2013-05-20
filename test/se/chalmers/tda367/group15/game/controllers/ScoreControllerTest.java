package se.chalmers.tda367.group15.game.controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import se.chalmers.tda367.group15.game.database.DatabaseScore;
import se.chalmers.tda367.group15.game.database.GameDatabase;
import se.chalmers.tda367.group15.game.models.ScoreModel;
import se.chalmers.tda367.group15.game.settings.Constants;

public class ScoreControllerTest {
	private static GameDatabase db;
	private static ScoreModel sm;
	private static ScoreController scoreController;

	@Before
	public void setUpBefore() throws Exception {
		db = new GameDatabase(true);
		sm = new ScoreModel(1000);
		scoreController = new ScoreController(null, sm);
		scoreController.setDatabase(db);
	}

	@Test
	public final void testDecreaseScoreOverTime() {
		scoreController.update(null, Constants.SCORE_DECREASE_INTERVAL + 1);
		assertTrue(sm.getScore() == 999);
		scoreController.update(null, -1);
		assertTrue(sm.getScore() == 999);
	}

	@Test
	public final void testSaveScoreWithoutName() {
		scoreController.saveScore(null);
		assertTrue(db.getHighscores().size() == 1);
		DatabaseScore s = db.getHighscores().get(0);
		assertNotNull(s.getName());
		assertEquals(s.getScore(), 1000);
	}

	@Test
	public final void testSaveScore() {
		scoreController.saveScore("Testing");
		assertTrue(db.getHighscores().size() == 1);
		DatabaseScore s = db.getHighscores().get(0);
		assertEquals(s.getName(), "Testing");
		assertEquals(s.getScore(), 1000);
	}

}
