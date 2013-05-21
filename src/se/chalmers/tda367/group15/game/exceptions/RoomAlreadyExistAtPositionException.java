package se.chalmers.tda367.group15.game.exceptions;

/**
 * Exception to be thrown when trying to add romm to position where room already
 * exists
 * 
 */
public class RoomAlreadyExistAtPositionException extends RuntimeException {
	private static final long serialVersionUID = -5312651513339462450L;

	public RoomAlreadyExistAtPositionException() {
		super();
	}

	public RoomAlreadyExistAtPositionException(String string) {
		super(string);
	}
}
