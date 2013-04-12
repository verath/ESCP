package se.chalmers.tda367.group15.game.models.room;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class RoomManagerTest {
	
	@Test(expected = RoomDoesNotExistException.class)
	public void addRoomAboveNonExistingRoom() {
		RoomManager rm = new RoomManager();
		Room r1 = new Room();
		Room r2 = new Room();
		rm.addRoomAbove(r1, r2);
	}
	
	@Test
	public void addRoomAsStartingRoom() {
		RoomManager rm = new RoomManager();
		Room r1 = new Room();
		rm.addStartingRoom(r1);
		assertEquals(r1, rm.getCurrentRoom());
	}
	
	@Test
	public void addRoomAboveRoom(){
		RoomManager rm = new RoomManager();
		Room r1 = new Room();
		Room r2 = new Room();
		
		rm.addStartingRoom(r1);
		rm.addRoomAbove(r1, r2);
		
		rm.moveUp();
		assertEquals(r2, rm.getCurrentRoom());
	}
	
	@Test
	public void addRoomBelowRoom(){
		RoomManager rm = new RoomManager();
		Room r1 = new Room();
		Room r2 = new Room();
		
		rm.addStartingRoom(r1);
		rm.addRoomBelow(r1, r2);
		
		rm.moveDown();
		assertEquals(r2, rm.getCurrentRoom());
	}
	
	@Test
	public void addRoomLeftOfRoom(){
		RoomManager rm = new RoomManager();
		Room r1 = new Room();
		Room r2 = new Room();
		
		rm.addStartingRoom(r1);
		rm.addRoomLeftOf(r1, r2);
		
		rm.moveLeft();
		assertEquals(r2, rm.getCurrentRoom());
	}
	
	@Test
	public void addRoomRightOfRoom(){
		RoomManager rm = new RoomManager();
		Room r1 = new Room();
		Room r2 = new Room();
		
		rm.addStartingRoom(r1);
		rm.addRoomRightOf(r1, r2);
		
		rm.moveRight();
		assertEquals(r2, rm.getCurrentRoom());
	}
	
	
}
