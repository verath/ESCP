package se.chalmers.tda367.group15.game.models.room;

public class RoomDoesNotExistException extends RuntimeException {
	public RoomDoesNotExistException() {
		super();
	}
	
	public RoomDoesNotExistException(String string) {
		super(string);
	}
}
