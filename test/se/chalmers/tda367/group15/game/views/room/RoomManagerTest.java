package se.chalmers.tda367.group15.game.views.room;

import static org.junit.Assert.*;

import org.junit.Test;

import se.chalmers.tda367.group15.game.models.Room;
import se.chalmers.tda367.group15.game.views.room.BasicRoom;
import se.chalmers.tda367.group15.game.views.room.RoomDoesNotExistException;
import se.chalmers.tda367.group15.game.views.room.RoomManager;

public class RoomManagerTest {
	
	@Test(expected = RoomDoesNotExistException.class)
	public void addRoomAboveNonExistingRoom() {
		RoomManager rm = new RoomManager();
		Room r1 = new BasicRoom();
		Room r2 = new BasicRoom();
		rm.addRoomAbove(r1, r2);
	}
	
	@Test
	public void addRoomAsStartingRoom() {
		RoomManager rm = new RoomManager();
		Room r1 = new BasicRoom();
		rm.addStartingRoom(r1);
		assertEquals(r1, rm.getCurrentRoom());
	}
	
	@Test
	public void addRoomAboveRoom(){
		RoomManager rm = new RoomManager();
		Room r1 = new BasicRoom();
		Room r2 = new BasicRoom();
		
		rm.addStartingRoom(r1);
		rm.addRoomAbove(r1, r2);
		
		rm.moveUp();
		assertEquals(r2, rm.getCurrentRoom());
	}
	
	@Test
	public void addRoomBelowRoom(){
		RoomManager rm = new RoomManager();
		Room r1 = new BasicRoom();
		Room r2 = new BasicRoom();
		
		rm.addStartingRoom(r1);
		rm.addRoomBelow(r1, r2);
		
		rm.moveDown();
		assertEquals(r2, rm.getCurrentRoom());
	}
	
	@Test
	public void addRoomLeftOfRoom(){
		RoomManager rm = new RoomManager();
		Room r1 = new BasicRoom();
		Room r2 = new BasicRoom();
		
		rm.addStartingRoom(r1);
		rm.addRoomLeftOf(r1, r2);
		
		rm.moveLeft();
		assertEquals(r2, rm.getCurrentRoom());
	}
	
	@Test
	public void addRoomRightOfRoom(){
		RoomManager rm = new RoomManager();
		Room r1 = new BasicRoom();
		Room r2 = new BasicRoom();
		
		rm.addStartingRoom(r1);
		rm.addRoomRightOf(r1, r2);
		
		rm.moveRight();
		assertEquals(r2, rm.getCurrentRoom());
	}
	
	
}