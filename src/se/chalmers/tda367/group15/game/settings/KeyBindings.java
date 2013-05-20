package se.chalmers.tda367.group15.game.settings;

import java.util.prefs.Preferences;

import org.newdawn.slick.Input;

/**
 * A class providing static access to the stored key binds for the user.
 * 
 * @author Peter
 */
public class KeyBindings {

	/**
	 * The default binding for going left
	 */
	public static final int DEFAULT_KEY_BIND_LEFT = Input.KEY_A;

	/**
	 * The default binding for going up
	 */
	public static final int DEFAULT_KEY_BIND_UP = Input.KEY_W;

	/**
	 * The default binding for going down
	 */
	public static final int DEFAULT_KEY_BIND_DOWN = Input.KEY_S;

	/**
	 * The default binding for going right
	 */
	public static final int DEFAULT_KEY_BIND_RIGHT = Input.KEY_D;

	/**
	 * The default binding for changing to weapon 1
	 */
	public static final int DEFAULT_KEY_BIND_WEAPON_1 = Input.KEY_1;

	/**
	 * The default binding for changing to weapon 2
	 */
	public static final int DEFAULT_KEY_BIND_WEAPON_2 = Input.KEY_2;

	/**
	 * The default binding for changing to weapon 3
	 */
	public static final int DEFAULT_KEY_BIND_WEAPON_3 = Input.KEY_3;

	public enum Key {
		LEFT(KeyBindings.DEFAULT_KEY_BIND_LEFT), UP(
				KeyBindings.DEFAULT_KEY_BIND_UP), RIGHT(
				KeyBindings.DEFAULT_KEY_BIND_RIGHT), DOWN(
				KeyBindings.DEFAULT_KEY_BIND_DOWN), WEAPON_1(
				KeyBindings.DEFAULT_KEY_BIND_WEAPON_1), WEAPON_2(
				KeyBindings.DEFAULT_KEY_BIND_WEAPON_2), WEAPON_3(
				KeyBindings.DEFAULT_KEY_BIND_WEAPON_3);
		private final String preferenceKey;
		private final int defaultBinding;

		private Key(final int defaultBinding) {
			this.defaultBinding = defaultBinding;
			this.preferenceKey = "KEYBINDS_" + name();
		}

		String getPreferenceKey() {
			return this.preferenceKey;
		}

		/**
		 * Returns the default binding for this key
		 * 
		 * @return The default binding.
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
	}

	/**
	 * Returns the stored integer representing the binding for the key, if
	 * existing. If not, returns a default value.
	 * 
	 * @param key
	 *            The key to find bindings for.
	 * @param def
	 *            The default value used should the key not exist.
	 * @return The binding for the key.
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
	 * @return The binding for the key.
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
	 *            A new Preference object to be used for storing keys.
	 */
	protected static void setPreferences(Preferences prefs) {
		KeyBindings.prefs = prefs;
	}
}
