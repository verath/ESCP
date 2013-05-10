package se.chalmers.tda367.group15.game.controllers;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundEffectsController {

	private static SoundEffectsController instance;

	public enum SoundEffect {
		ENEMY_DEATH("res/sound/enemy/ondamage/die.aif"), ENEMY_HURT(
				"res/sound/enemy/ondamage/damage1.aif"), PISTOL_FIRED(
				"res/sound/pistol/shoot.aif");

		private String pathToFile;

		private SoundEffect(String pathToFile) {
			this.pathToFile = pathToFile;
		}
	}

	private SoundEffectsController() {
	}

	public static SoundEffectsController instance() {
		if (instance == null) {
			instance = new SoundEffectsController();
		}
		return instance;
	}

	public void playSound(SoundEffect soundEffect) {
		Sound sound;;
		try {
			sound = new Sound(soundEffect.pathToFile);
			sound.play();
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}

}
