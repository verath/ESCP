package se.chalmers.tda367.group15.game.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import se.chalmers.tda367.group15.game.models.TestMap;
import se.chalmers.tda367.group15.game.models.tiles.ManiacTile;
import se.chalmers.tda367.group15.game.models.tiles.Tiles;


public class BoardView extends JPanel {
	private BufferedImage backbuffer;
	private Graphics2D g2d;
	private TestMap boardModel;
	private ManiacTile maniac;
	private AffineTransform trans = new AffineTransform();

	public BoardView(TestMap boardModel) {
		setPreferredSize(new Dimension(1024, 768));
		this.boardModel = boardModel;
		maniac = boardModel.maniac();
		backbuffer = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_RGB);
		g2d = backbuffer.createGraphics();
	}

	@Override
	public void paint(Graphics g) {
		g2d.setTransform(trans);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, getSize().width, getSize().height);
		g2d.setTransform(trans);
		g2d.translate(maniac.getX(), maniac.getY());
		g2d.rotate(Math.toRadians(maniac.getMoveAngle()), 16, 16);
		Tiles[][] tiles = boardModel.tiles();

		// Draw Map
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				g2d.setTransform(trans);
				g2d.translate(tiles[i][j].getX(), tiles[i][j].getY());
				g2d.drawImage(tiles[i][j].getCurrentImage(), trans, this);
			}
		}

		// Draw Maniac
		g2d.setTransform(trans);
		g2d.translate(maniac.getX(), maniac.getY());
		g2d.rotate(Math.toRadians(maniac.getMoveAngle()), 16, 16);
		g2d.drawImage(maniac.getCurrentImage(), trans, this);

		// draw collisionbound around maniac

//		 g2d.setTransform(trans);
//		 g2d.setColor(Color.RED);
//		 g2d.drawRect(maniac.getBounds().x, maniac.getBounds().y,
//		 maniac.getBounds().width, maniac.getBounds().height);
		g2d.scale(10, 10);

		g.drawImage(backbuffer, 0, 0, this);
	}
}
