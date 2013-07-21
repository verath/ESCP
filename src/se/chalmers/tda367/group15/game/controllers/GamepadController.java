package se.chalmers.tda367.group15.game.controllers;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

/**
 * A class that will manage a gamepad for the Psycho Hero game that will be used
 * to control the hero.
 * 
 * @author Erik
 * 
 */
public class GamepadController {

	private Controller gamepad;

	private boolean A;
	private boolean B;
	private boolean X;
	private boolean Y;
	private boolean LB;
	private boolean RB;

	/**
	 * Create a new GamepadController that will manage button and control stick
	 * input.
	 */
	public GamepadController() {

		try {
			Controllers.create();
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Controllers.poll();

		/*
		 * The 360 controller may be located differently on different computers.
		 * This is not a failsafe method, but due to a rather limited API this
		 * will have to do.
		 */
		int indexOfGamepad = 0;
		boolean gamepadExists = false;
		for (int i = 0; i < Controllers.getControllerCount(); i++) {
			System.out.println(Controllers.getController(i).getName());
			if (Controllers.getController(i).getName().contains("360")) {
				indexOfGamepad = i;
				gamepadExists = true;
			}
		}
		if (gamepadExists)
			gamepad = Controllers.getController(indexOfGamepad);

		
	}

	/**
	 * The gamepad constantly needs to check for input. This method needs to be
	 * called by the client in order to query accurate information.
	 */
	public void update() {
		// if (gamepad != null) {
		// gamepad.poll();
		// A = gamepad.isButtonPressed(0);
		// B = gamepad.isButtonPressed(2);
		// X = gamepad.isButtonPressed(1);
		// Y = gamepad.isButtonPressed(3);
		// LB = gamepad.isButtonPressed(4);
		// RB = gamepad.isButtonPressed(5);
		
		test();
	}

	void test() {
			
		System.out.println((gamepad.getAxisValue(0)));
	}
}
