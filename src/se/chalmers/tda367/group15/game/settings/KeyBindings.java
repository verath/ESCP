package se.chalmers.tda367.group15.game.settings;

import java.util.prefs.Preferences;

/**
 * A class providing static access to the stored key binds for the user.
 * 
 * @author Peter
 * 
 */
public class KeyBindings {

	public enum Key {
		LEFT(Constants.DEFAULT_KEY_BIND_LEFT), UP(Constants.DEFAULT_KEY_BIND_UP), RIGHT(
				Constants.DEFAULT_KEY_BIND_RIGHT), DOWN(
				Constants.DEFAULT_KEY_BIND_DOWN);
		private final String preferenceKey;
		private final int defaultBinding;

		private Key(final int defaultBinding) {
			this.defaultBinding = defaultBinding;
			this.preferenceKey = "KEYBINDS_" + name();
		}

		protected String getPreferenceKey() {
			return this.preferenceKey;
		}

		/**
		 * Returns the default binding for this key
		 * 
		 * @return
		 */
		public int getDefaultBinding() {
			return this.defaultBinding;
		}
	}

	/**
	 * The Preference store used to store user data.
	 */
	private static Preferences prefs = Preferences
			.userNodeForPackage(KeyBindings.class);

	/**
	 * Private constructor, as this class only has static methods.
	 */
	private KeyBindings() {
	};

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
	 * Returns a stored integer representing the binding for the key, if
	 * existing. If not, returns the default value associated with the key.
	 * 
	 * @param key
	 *            The key to find bindings for.
	 * @return
	 */
	public static int getBinding(final Key key) {
		return KeyBindings.getBinding(key, key.getDefaultBinding());
	}

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
