package se.chalmers.tda367.group15.game.controllers;

public class RoomDoesNotExistException extends RuntimeException {
	private static final long serialVersionUID = 7956667496071790859L;

	public RoomDoesNotExistException() {
		super();
	}
	
	public RoomDoesNotExistException(String string) {
		super(string);
	}
}
