package se.chalmers.tda367.group15.game.controllers;

import java.util.Random;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundEffectsController {

	private static SoundEffectsController instance;

	public enum SoundEffect {
		ENEMY_DEATH(new String[] { "res/sound/enemy/ondamage/die.aif" }), ENEMY_HURT(
				new String[] { "res/sound/enemy/ondamage/damage1.aif",
						"res/sound/enemy/ondamage/damage2.aif",
						"res/sound/enemy/ondamage/damage3.aif" }), PISTOL_FIRED(
				new String[] { "res/sound/pistol/shoot.aif" }), ENEMY_COLLISION(
				new String[] { "res/sound/enemy/collision.aif" });

		private String[] pathsToFiles;

		private SoundEffect(String[] pathsToFiles) {
			this.pathsToFiles = pathsToFiles;
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
		Sound sound;
		int maxRandomNbr = soundEffect.pathsToFiles.length;
		Random generator = new Random();

		try {
			sound = new Sound(
					soundEffect.pathsToFiles[generator.nextInt(maxRandomNbr)]);
			sound.play();

		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

}
