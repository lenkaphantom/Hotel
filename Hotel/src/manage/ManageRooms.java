package manage;

import java.util.ArrayList;
import java.util.List;

import rooms.Room;

public abstract class ManageRooms {
	private List<Room> rooms;
	
	// constructors
	public ManageRooms() {
	}
	
	public ManageRooms(List<Room> rooms) {
		this.rooms = new ArrayList<Room>(rooms);
	}
	
	// copy constructor
	public ManageRooms(ManageRooms manageRooms) {
		this.rooms = new ArrayList<Room>(manageRooms.rooms);
	}
	
	// getters
	public List<Room> getRooms() {
		return rooms;
	}
	
	// setters
	public void setRooms(List<Room> rooms) {
		this.rooms = new ArrayList<Room>(rooms);
	}
	
	// methods
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	public void removeRoom(Room room) {
		this.rooms.remove(room);
	}
	
	public void changeRoom(Room oldRoom, Room newRoom) {
		this.rooms.set(this.rooms.indexOf(oldRoom), newRoom);
	}
	
	public void printRooms() {
		for (Room room : rooms) {
			System.out.println(room.toString());
		}
	}
}
