package se.chalmers.tda367.group15.game.controllers;

import se.chalmers.tda367.group15.game.models.TestMap;
import se.chalmers.tda367.group15.game.views.BoardView;
import se.chalmers.tda367.group15.game.views.MainFrame;

public class PsychoManiac implements Runnable{
	private Thread gameloop;
	private MainFrame mainFrame;
	private BoardView boardView;
	private TestMap boardModel;
	private GameController gameController;
	
	public PsychoManiac() {
		boardModel = new TestMap();
		mainFrame = new MainFrame();
		boardView = new BoardView(boardModel);
		gameController = new GameController(boardModel, boardView);
		mainFrame.addKeyListener(gameController);
		mainFrame.add(boardView);
		
		gameloop = new Thread(this);
		gameloop.start();
	}
	
	public static void main(String[] args) {
		new PsychoManiac();
	}

	@Override
	public void run() {
		Thread t = Thread.currentThread();
		while(t == gameloop) {
			try{
				gameController.update();
				Thread.sleep(20);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
