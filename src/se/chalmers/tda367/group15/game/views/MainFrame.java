package se.chalmers.tda367.group15.game.views;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame() {
		setTitle("PsychoManiac");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1024,768));
		setVisible(true);
		pack();
	}

}
