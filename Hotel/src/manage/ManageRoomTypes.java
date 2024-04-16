package manage;

import java.util.ArrayList;
import java.util.List;

import rooms.RoomType;

public class ManageRoomTypes {
	private List<RoomType> roomTypes;
	
	// constructors
	public ManageRoomTypes() {
	}
	
	public ManageRoomTypes(List<RoomType> roomTypes) {
		this.roomTypes = new ArrayList<RoomType>(roomTypes);
	}
	
	// copy constructor
	public ManageRoomTypes(ManageRoomTypes manageRoomTypes) {
		this.roomTypes = new ArrayList<RoomType>(manageRoomTypes.roomTypes);
	}
	
	// getters
	public List<RoomType> getRoomTypes() {
		return roomTypes;
	}
	
	// setters
	public void setRoomTypes(List<RoomType> roomTypes) {
		this.roomTypes = new ArrayList<RoomType>(roomTypes);
	}
	
	// methods
	public void addRoomType(RoomType roomType) {
		this.roomTypes.add(roomType);
	}
	
	public void removeRoomType(RoomType roomType) {
		this.roomTypes.remove(roomType);
	}
}
