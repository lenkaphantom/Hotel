package rooms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

enum RoomStatus {
	FREE, OCCUPIED, RESERVED
}

public class Room {
	private RoomType roomType;
	private List<LocalDate> occupiedDates;

	// constructors
	public Room() {
	}

	public Room(RoomType roomType, List<LocalDate> occupiedDates) {
		this.roomType = new RoomType(roomType);
		this.occupiedDates = new ArrayList<>(occupiedDates);
	}
	
	// copy constructor
	public Room(Room room) {
		this.roomType = new RoomType(room.roomType);
		this.occupiedDates = new ArrayList<>(room.occupiedDates);
	}

	// getters
	public RoomType getRoomType() {
		return roomType;
	}

	public List<LocalDate> getOccupiedDates() {
		return occupiedDates;
	}

	// setter
	public void setRoomType(RoomType roomType) {
		this.roomType = new RoomType(roomType);
	}

	public void setOccupiedDates(List<LocalDate> occupiedDates) {
		this.occupiedDates = new ArrayList<>(occupiedDates);
	}
	
	// methods
	@Override
	public String toString() {
		String occupiedDates = "";
		for (LocalDate date : this.occupiedDates) {
			occupiedDates += date + " ";
		}
		return "-------Room-------" + "\nroomType: " + this.roomType + "\noccupiedDates: " + occupiedDates;
	}
}
