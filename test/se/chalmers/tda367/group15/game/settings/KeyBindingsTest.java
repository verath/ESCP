package se.chalmers.tda367.group15.game.settings;

import static org.junit.Assert.assertTrue;

import java.util.prefs.Preferences;

import org.junit.BeforeClass;
import org.junit.Test;

import se.chalmers.tda367.group15.game.settings.KeyBindings.Key;

public class KeyBindingsTest {

	private static final Preferences prefs = Preferences
			.userNodeForPackage(KeyBindingsTest.class);

	@BeforeClass
	public static void beforeClass() {
		// Make sure we are note operating on the "real" storage!
		KeyBindings.setPreferences(prefs);
	}

	/**
	 * Helper method for clearing the preference store. So that we can try
	 * default values.
	 */
	private static void clearPrefs() {
		try {
			prefs.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Test to see that we get a default value back if no value is set. This is
	 * essentially guaranteed by the Preference library already, so really
	 * shouldn't fail.
	 */
	@Test
	public final void testDefaultValue() {
		clearPrefs();
		assertTrue(KeyBindings.getBinding(Key.DOWN, 123) == 123);
		clearPrefs();
		assertTrue(KeyBindings.getBinding(Key.DOWN) == Key.DOWN
				.getDefaultBinding());
	}

	/*
	 * Tests a simple set and get operation. Make sure we don't get a default
	 * value when we have a value for a key.
	 */
	@Test
	public final void testSetAndGetValue() {
		KeyBindings.setBinding(Key.LEFT, 123);
		assertTrue(KeyBindings.getBinding(Key.LEFT, -1) == 123);
	}

}
