package se.chalmers.tda367.group15.game.models.room;

public class RoomAlreadyExistAtPositionException extends RuntimeException {
	private static final long serialVersionUID = -5312651513339462450L;

	public RoomAlreadyExistAtPositionException() {
		super();
	}
	
	public RoomAlreadyExistAtPositionException(String string) {
		super(string);
	}
}
