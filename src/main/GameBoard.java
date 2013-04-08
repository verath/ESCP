package main;

import gameObjects.Drawable;
import gameObjects.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameBoard extends JPanel implements FrameListener {
	private List<Drawable> displayedObject = new LinkedList<>();
	
	 Player player;
	 GameThread gameThread;
	
	public GameBoard(Player player, GameThread gameThread) {
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		
		this.player = player;
		this.gameThread = gameThread;
	}
	
	@Override
	public void addNotify(){
		super.addNotify();
		gameThread.addTickListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for (Drawable obj : displayedObject) {
			obj.draw(g);
		}
		
		player.draw(g);
		
		g.setColor(Color.white);
		g.drawString(Integer.toString(gameThread.getCurrentFPS()), 20, 20);
	}

	public void addDisplayedObject(Drawable obj) {
		displayedObject.add(obj);
	}

	public void clearDisplayedObjects() {
		displayedObject.clear();
	}

	@Override
	public void onTick(long elapsedTime) {
		repaint();
	}


}
