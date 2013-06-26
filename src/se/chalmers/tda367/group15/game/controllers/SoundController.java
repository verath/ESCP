package se.chalmers.tda367.group15.game.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundController {

	private static final List<Sound> sounds = new ArrayList<Sound>();

	private static Music music;
	private static Music queuedMusic;

	private static boolean queued = false;

	public enum SoundEffect {
		ENEMY_DEATH(new String[] { "res/sound/enemy/ondeath/enemy_death.aif" }), ENEMY_HURT(
				new String[] { "res/sound/enemy/ondamage/damage1.aif",
						"res/sound/enemy/ondamage/damage2.aif",
						"res/sound/enemy/ondamage/damage3.aif" }), PISTOL_FIRED(
				new String[] { "res/sound/pistol/shoot.aif" }), ENEMY_COLLISION(
				new String[] { "res/sound/enemy/collision.aif" }), NARRATOR_NEXT(
				new String[] { "res/sound/narrator/next_room.aif" }), NARRATOR_BOSS(
				new String[] { "res/sound/narrator/boss_waiting.aif" }), AXE_SWING(
				new String[] { "res/sound/axe/axe_swing.aif" }), UNARMED_SMASH(
				new String[] { "res/sound/unarmed/melee_swing.aif" }), BOSS_DEATH(
				new String[] { "res/sound/boss/ondeath/boss_death.aif" }), DONUT_FIRED(
				new String[] { "res/sound/donut/donut.aif" }), POWERUP(
				new String[] { "res/sound/misc/powerup.aif" });

		private final String[] pathsToFiles;

		private SoundEffect(String[] pathsToFiles) {
			this.pathsToFiles = pathsToFiles;
		}
	}

	public enum GameMusic {
		NORMAL_MUSIC(new String[] { "res/music/normal.aif" }), BOSS_MUSIC(
				new String[] { "res/music/boss.aif" });

		private final String[] pathsToFiles;

		private GameMusic(String[] pathsToFiles) {
			this.pathsToFiles = pathsToFiles;
		}
	}

	public static void playSound(SoundEffect soundEffect) {
		Sound sound;
		int maxRandomNbr = soundEffect.pathsToFiles.length;
		Random generator = new Random();

		try {
			sound = new Sound(
					soundEffect.pathsToFiles[generator.nextInt(maxRandomNbr)]);
			sound.play();
			sounds.add(sound);
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public static void playGameMusic(GameMusic gameMusic) {

		try {
			if (music != null) {
				if (music.playing()) {
					queuedMusic = new Music(gameMusic.pathsToFiles[0]);
					queued = true;
					fadeGameMusic();
				}
			} else {
				music = new Music(gameMusic.pathsToFiles[0]);
				music.loop();
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	private static void fadeGameMusic() {
		if (music != null) {
			music.fade(1500, 0.0f, true);
		}
	}

	public static void stopAll() {
		if (music != null) {
			music.release();
		}
		if (queuedMusic != null) {
			queuedMusic.release();
		}
		for (Sound s : sounds) {
			s.release();
		}
	}

	public static void update() {
		// If a piece of music has been queued
		if (queued && !music.playing()) {
			music.setVolume(0.0f);
			queuedMusic.setVolume(0.0f);
			queuedMusic.fade(3000, 0.7f, false);
			music = queuedMusic;
			music.loop();
			queued = false;

		}
	}
}
