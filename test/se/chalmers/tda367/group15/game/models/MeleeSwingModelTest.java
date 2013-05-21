package se.chalmers.tda367.group15.game.models;

import static junit.framework.Assert.assertNull;

import org.junit.Test;

public class MeleeSwingModelTest {
	@Test
	public final void testImagePathIsNull() {
		MeleeSwingModel meleeSwingModel = new MeleeSwingModel();
		assertNull(meleeSwingModel.getImagePath());
	}
}
