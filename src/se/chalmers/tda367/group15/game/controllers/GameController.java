package se.chalmers.tda367.group15.game.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import se.chalmers.tda367.group15.game.models.TestMap;
import se.chalmers.tda367.group15.game.models.tiles.ManiacTile;
import se.chalmers.tda367.group15.game.models.tiles.Tiles;
import se.chalmers.tda367.group15.game.views.BoardView;

public class GameController implements KeyListener, ActionListener {

	private final int LEFT = 0;
	private final int RIGHT = 1;
	private final int UP = 2;
	private final int DOWN = 3;
	private boolean[] currentKeys = new boolean[4];
	private TestMap boardModel;
	private ManiacTile maniac;
	private BoardView view;
	private double oldX, oldY;

	public GameController(TestMap boardModel, BoardView view) {
		this.boardModel = boardModel;
		maniac = boardModel.maniac();
		maniac.getTimer().addActionListener(this);
		maniac.getTimer().start();
		this.view = view;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// do nothing

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_DOWN:
			currentKeys[DOWN] = true;
			break;
		case KeyEvent.VK_UP:
			currentKeys[UP] = true;
			break;
		case KeyEvent.VK_LEFT:
			currentKeys[LEFT] = true;
			break;
		case KeyEvent.VK_RIGHT:
			currentKeys[RIGHT] = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_DOWN:
			currentKeys[DOWN] = false;
			break;
		case KeyEvent.VK_UP:
			currentKeys[UP] = false;
			break;
		case KeyEvent.VK_LEFT:
			currentKeys[LEFT] = false;
			break;
		case KeyEvent.VK_RIGHT:
			currentKeys[RIGHT] = false;
			break;
		}

	}

	public void update() {
		oldX = maniac.getX();
		oldY = maniac.getY();
		if (currentKeys[DOWN]) {
			maniac.setMoveAngle(90);
			maniac.incY(4);
		}
		if (currentKeys[UP]) {
			maniac.setMoveAngle(-90);
			maniac.incY(-4);
		}

		if (currentKeys[LEFT]) {
			maniac.setMoveAngle(180);
			maniac.incX(-4);
		}

		if (currentKeys[RIGHT]) {
			maniac.setMoveAngle(0);
			maniac.incX(4);
		}

		if (currentKeys[RIGHT] && currentKeys[UP]) {
			maniac.setMoveAngle(-45);
		}

		if (currentKeys[LEFT] && currentKeys[UP]) {
			maniac.setMoveAngle(-135);
		}

		if (currentKeys[RIGHT] && currentKeys[DOWN]) {
			maniac.setMoveAngle(45);
		}

		if (currentKeys[LEFT] && currentKeys[DOWN]) {
			maniac.setMoveAngle(135);
		}
		if (isCollision()) {
			maniac.setX(oldX);
			maniac.setY(oldY);
		}
		view.repaint();

	}

	public boolean isCollision() {
		Tiles[][] tiles = boardModel.tiles();
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if (tiles[i][j].getBounds().intersects(maniac.getBounds())
						&& tiles[i][j].isBlocked()) {
					return true;
				}
			}
		}
		return false;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(keysPressed()) {
			maniac.animate();
		}
	}

	private boolean keysPressed() {
		boolean isPressed = false;
		for(int i = 0; i < currentKeys.length; i++) {
			if(currentKeys[i]) {
				isPressed = true;
			}
		}
		return isPressed;
	}

}
