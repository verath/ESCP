package se.chalmers.tda367.group15.game;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;

import javax.swing.JFrame;

/**
 * A singelton class for handling of displaying JFrames as fullscreen
 * applications using the local graphics environment.
 * 
 * @author Peter
 * 
 */
public enum Screen {
	/**
	 * The Screen instance
	 */
	INSTANCE;

	/**
	 * The graphics device, ie. the screen
	 */
	private GraphicsDevice vc;

	/**
	 * Private constructor
	 */
	private Screen() {
		vc = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
	}

	/**
	 * Sets a JFrame as the full screen window and changes the graphics
	 * environment depending on the provided DisplayMode.
	 * 
	 * @param dm The DisplayMode to display in.
	 * @param window a JFrame that should be displayed as the screen content.
	 */
	public void setFullScreenWindow(DisplayMode dm, JFrame window) {
		window.setUndecorated(true);
		window.setResizable(false);
		vc.setFullScreenWindow(window);
		
		if (dm != null && vc.isDisplayChangeSupported()) {
			vc.setDisplayMode(dm);
		}
	}

	/**
	 * Getter for the current full screen window.
	 * @return
	 */
	public Window getFullScreenWindow() {
		return vc.getFullScreenWindow();
	}

	/**
	 * Restores the screen to the state it was before set to fullscreen.
	 */
	public void restoreScreen() {
		Window w = vc.getFullScreenWindow();
		if (w != null) {
			w.dispose();
		}

		vc.setFullScreenWindow(null);
	}
}
