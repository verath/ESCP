package se.chalmers.tda367.group15.game.models;

import java.awt.event.KeyEvent;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;


/**
 * Class representing the model of a hero.
 * 
 * @author simon, Carl, tholene
 * 
 */
public class Hero extends MovingModel implements Model {
	
	private double rotation;
	private boolean goingUp;
	private boolean goingDown;
	private boolean goingRight;
	private boolean goingLeft;
	private int heroPicWidth;
	private int heroPicHeight;
	
	
	/**
	 * Create a new Hero.
	 */
	public Hero() {

		// TODO fix hardcoded values..
		setX(34f);
		setY(34f);
		setVelocity(0.15f);
		this.heroPicWidth = 64;
		this.heroPicHeight = 64;

	}

	@Override
	public void update(GameContainer cont, int delta) {
		Input input = cont.getInput();
		
		float mouseX = input.getMouseX();
		float mouseY = input.getMouseY();
		rotation = Math.toDegrees( Math.atan2( ( heroPicHeight/2 + getY() - mouseY) , ( heroPicWidth/2 + getX() - mouseX ) ) ); // rotation in degrees starting to the left of model
		
		goingUp = input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP);
		goingDown = input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN);
		goingRight = input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT);
		goingLeft = input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT);
		
		// TODO fix ugly else if!
		
		if ( goingUp && goingLeft && !goingRight && !goingDown ) { // up & left
			
			this.setY( (float) (this.getY() - (delta * this.getVelocity() * Math.sin( Math.PI / 4)) ) );
			this.setX( (float) (this.getX() - (delta * this.getVelocity() * Math.sin( Math.PI / 4)) ) );
			
		} else if ( goingUp && goingRight && !goingLeft && !goingDown ) { // up & right
			
			this.setY( (float) (this.getY() - (delta * this.getVelocity() * Math.sin( Math.PI / 4)) ) );
			this.setX( (float) (this.getX() + (delta * this.getVelocity() * Math.sin( Math.PI / 4)) ) );
			
		} else if ( goingUp && !goingDown ) { // up
			
			this.setY(this.getY() - delta * this.getVelocity());
			
		}else if ( goingLeft && goingDown && !goingUp && !goingRight ) { // down & left
			
			this.setY( (float) (this.getY() + (delta * this.getVelocity() * Math.sin( Math.PI / 4)) ) );
			this.setX( (float) (this.getX() - (delta * this.getVelocity() * Math.sin( Math.PI / 4)) ) );
			
		} else if ( goingRight && goingDown && !goingUp && !goingLeft  ) { // down & right
			
			this.setY( (float) (this.getY() + (delta * this.getVelocity() * Math.sin( Math.PI / 4)) ) );
			this.setX( (float) (this.getX() + (delta * this.getVelocity() * Math.sin( Math.PI / 4)) ) );
			
		} else if ( goingDown && !goingUp ) { // down
			
			this.setY(this.getY() + delta * this.getVelocity());
			
		} else if ( goingLeft && !goingRight ) { // left
			
			this.setX(this.getX() - delta * this.getVelocity());
			
		} else if ( goingRight && !goingLeft ) { // right
			
			this.setX(this.getX() + delta * this.getVelocity());
		}
		
	}

	public double getDirection(){
		return rotation;
	}
	
	public boolean isMoving() {
		return goingUp || goingDown || goingLeft || goingRight;
	}
}
