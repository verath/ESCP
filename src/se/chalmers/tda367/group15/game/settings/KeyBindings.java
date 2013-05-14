package se.chalmers.tda367.group15.game.settings;

import java.util.prefs.Preferences;

/**
 * A class providing static access to the stored key binds for the user.
 * 
 * @author Peter
 * 
 */
public class KeyBindings {
	/**
	 * The Preference store used to store user data.
	 */
	private static Preferences prefs = Preferences
			.userNodeForPackage(se.chalmers.tda367.group15.game.main.Main.class);

	/**
	 * An enum holding the bindable keys.
	 * 
	 * @author Peter
	 * 
	 */
	public enum Key {
		LEFT, UP, RIGHT, DOWN;
		private final String preferenceKey;

		private Key() {
			this.preferenceKey = "KEYBINDS_" + name();
		}

		public String getPreferenceKey() {
			return this.preferenceKey;
		}
	}

	/**
	 * Returns the stored integer representing the binding for the key, if
	 * existing. If not, returns a default value.
	 * 
	 * @param key
	 *            The key to find bindings for.
	 * @param def
	 *            The default value used should the key not exist.
	 * @return
	 */
	public static int getBinding(final Key key, final int def) {
		// This _should_ be cached by the Preferences library.
		return prefs.getInt(key.getPreferenceKey(), def);
	}

	/**
	 * Private constructor, as this class only has static methods.
	 */
	private KeyBindings() {
	};

	/**
	 * Stores a new value as the key bind for the key.
	 * 
	 * @param key
	 *            The key that should have a new binding.
	 * @param keyCode
	 *            The new keyCode for this key.
	 */
	public static void setBinding(final Key key, final int keyCode) {
		prefs.putInt(key.getPreferenceKey(), keyCode);
	}

	/**
	 * Changes the used preference instance. Should most likely only be used for
	 * testing.
	 * 
	 * @param prefs
	 */
	protected static void setPreferences(Preferences prefs) {
		KeyBindings.prefs = prefs;
	}
}
