package main;

import static org.junit.Assert.*;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ScreenTest {
	
	static Screen screen = Screen.INSTANCE;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Always make sure we restore the screen after testing is done
		screen.restoreScreen();
	}

	@Test
	public void testSetFrameAsFullscreen() {
		// Test window that should be set as fullscreen
		JFrame testWindow = new JFrame();

		// Get the device default ressolution and framerate for the
		// DisplayMode
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();

		DisplayMode dm = new DisplayMode(width, height, 32,
				DisplayMode.REFRESH_RATE_UNKNOWN);

		screen.setFullScreenWindow(dm, testWindow);

		assertEquals(screen.getFullScreenWindow(), testWindow);
	}

}
