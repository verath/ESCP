package main;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private GameBoard gameBoard;
	private InputManager inputManager;
	
	public MainFrame(GameBoard gameBoard, InputManager inputManager) {
		setTitle("PsychoHero");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(Color.cyan);
		
		this.gameBoard = gameBoard;
		this.inputManager = inputManager;
		
		add(gameBoard);
        
        addMouseListener(inputManager);
        addMouseMotionListener(inputManager);
        addKeyListener(inputManager);
        
	}
	
	public GameBoard getGameBoard(){
		return gameBoard;
	}
	
	public InputManager getInputManager(){
		return inputManager;
	}
}
