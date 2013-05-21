package se.chalmers.tda367.group15.game.exceptions;

/**
 * Exception to be thrown when trying enter a room that does not exist.
 * 
 */
public class RoomDoesNotExistException extends RuntimeException {
	private static final long serialVersionUID = 7956667496071790859L;

	public RoomDoesNotExistException() {
		super();
	}

	public RoomDoesNotExistException(String string) {
		super(string);
	}
}
