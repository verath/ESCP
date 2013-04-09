package se.chalmers.tda367.group15.game.models.tiles;

import java.awt.Rectangle;

import javax.swing.Timer;

public class ManiacTile extends Tiles {
	private final Timer animationTimer = new Timer(50, null);

	public ManiacTile() {
		setImages("pics/hero");
		setX(1024/2);
		setY(768/2);
	}

	public Timer getTimer() {
		return animationTimer;
	}
}
