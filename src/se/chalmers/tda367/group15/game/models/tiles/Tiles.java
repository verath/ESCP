package se.chalmers.tda367.group15.game.models.tiles;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import se.chalmers.tda367.group15.game.tools.FileNameSorter;


public abstract class Tiles {
	private int frameCount;
	private boolean alive;
	private boolean isBlocked;
	private double x, y;
	private double velX, velY;
	private double moveAngle;
	private Image image;
	private List<Image> images = new ArrayList<Image>();
	private Toolkit tk = Toolkit.getDefaultToolkit();

	public Tiles() {
		setAlive(false);
		setBlocked(false);
		setX(0.0);
		setY(0.0);
		setVelX(0.0);
		setVelY(0.0);
		setMoveAngle(0.0);
	}

	/**
	 * Make a gameobject solid, or "blocked"
	 * 
	 * @param isBlocked
	 *            , should be set to true if gameobject is solid
	 */
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	/**
	 * Check if object is alive and active
	 * 
	 * @return true if object is alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * Get the rectangle representing the bounds of the object
	 * 
	 * @return the bounds of the gameobject
	 */
	public Rectangle getBounds() {
		Rectangle r;
		r = new Rectangle((int) getX(), (int) getY(), 32, 32);
		return r;
	}

	/**
	 * Get the x coordinate of a game object
	 * 
	 * @return the x coordinate
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get the y coordinate of a game object
	 * 
	 * @return the y coordinate
	 */
	public double getY() {
		return y;
	}

	/**
	 * Get the movement speed in X, e.g the velocity of a game object
	 * 
	 * @return the velocity of a game object in X
	 */
	public double getVelX() {
		return velX;
	}

	/**
	 * Get the movement speed in Y, e.g the velocity of a game object
	 * 
	 * @return the velocity of a game object in Y
	 */
	public double getVelY() {
		return velY;
	}

	/**
	 * Get the movement angle of a game object
	 * 
	 * @return the movement angle
	 */
	public double getMoveAngle() {
		return moveAngle;
	}

	/**
	 * Get the image of the game object
	 * 
	 * @return the game object's image
	 */
	public Image getCurrentImage() {
		return image;
	}

	/**
	 * Change a game object's alive status
	 * 
	 * @param alive
	 *            status
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * Set the x coordinate for the game object
	 * 
	 * @param x
	 *            coordinate
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Increase the x coordinate for the game object
	 * 
	 * @param dx
	 */
	public void incX(double i) {
		this.x += i;
	}

	/**
	 * Set the y coordinate for the game object
	 * 
	 * @param y
	 *            coordinate
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Increase the y coordinate for the game object
	 * 
	 * @param dy
	 */
	public void incY(double i) {
		this.y += i;
	}

	/**
	 * Change velocity for x coordinate
	 * 
	 * @param velocity
	 *            in x
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}

	/**
	 * Increase the velocity in x
	 * 
	 * @param dx
	 */
	public void incVelX(double i) {
		this.velX += i;
	}

	/**
	 * Change velocity for y coordinate
	 * 
	 * @param velocity
	 *            in y
	 */
	public void setVelY(double velY) {
		this.velY = velY;
	}

	/**
	 * Increase the velocity in y
	 * 
	 * @param dy
	 */
	public void incVelY(double i) {
		this.velY += i;
	}

	/**
	 * Set the move angle of the game object
	 * 
	 * @param move
	 *            angle
	 */
	public void setMoveAngle(double angle) {
		this.moveAngle = angle;
	}

	/**
	 * Set the game object's image
	 * 
	 * @param the
	 *            image to be set
	 */
	public void setCurrentImage(Image image) {
		this.image = image;
	}

	/**
	 * Set the game object's image
	 * 
	 * @param the
	 *            path image to be set
	 */
	public void setCurrentImage(String path) {
		this.image = tk.getImage(path);
	}

	/**
	 * Check if the gameobject is blocked
	 * 
	 * @return true if gameobject is blocked, false otherwise
	 */
	public boolean isBlocked() {
		return isBlocked;
	}

	/**
	 * Method for setting a game object's animation images
	 * 
	 * @param path
	 *            to images
	 */
	public void setImages(String path) {
		File folder = new File(path);
		File[] files = folder.listFiles();
		Arrays.sort(files, new FileNameSorter());
		for (File f : files) {
			System.out.println(f.getPath());
			try {
				images.add(ImageIO.read(f));
			} catch (IOException e) {
				System.out.println("File Not Found!!");
			}
		}

		setCurrentImage(images.get(0));
	}

	/**
	 * Method for getting a game object's images
	 * 
	 * @return a list of images
	 */
	public List<Image> getImages() {
		return images;
	}

	public void animate() {
		setCurrentImage(images.get(frameCount));
		frameCount = (frameCount + 1) % images.size();
	}

}
