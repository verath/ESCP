package se.chalmers.tda367.group15.game.models.room;

public class RoomAlreadyExistAtPositionException extends RuntimeException {
	public RoomAlreadyExistAtPositionException() {
		super();
	}
	
	public RoomAlreadyExistAtPositionException(String string) {
		super(string);
	}
}
