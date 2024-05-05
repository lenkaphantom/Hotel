package manage;

import java.util.HashMap;
import java.util.Map;

import entity.Room;
import entity.RoomType;
import enumeracije.RoomStatus;

public class ManageRooms {
	private Map<Integer, Room> rooms;
	
	// constructors
	public ManageRooms() {
		this.rooms = new HashMap<Integer, Room>();
	}
	
	// getters
	public Map<Integer, Room> getRooms() {
		return this.rooms;
	}
	
	// methods
	public void addRoom(int floor, int roomNumber, RoomStatus roomStatus, int idRoomType, ManageHotel hotel) {
		Room room = new Room(floor, roomNumber, roomStatus, idRoomType, hotel);
		this.rooms.put(room.getId(), room);
	}
	
	public void removeRoom(int id) {
		Room room = this.rooms.get(id);
		room.setIsDeleted(true);
		this.rooms.remove(room.getId());
	}
	
	public void changeRoom(int id, int floor, int roomNumber, RoomType roomType) {
		if (!this.rooms.containsKey(id)) {
			System.out.println("Soba sa id-jem " + id + " ne postoji.");
			return;
		}

		Room room = this.rooms.get(id);

		if (floor != 0) {
			room.setFloor(floor);
		}
		if (roomNumber != 0) {
			room.setRoomNumber(roomNumber);
		}
		if (roomType != null) {
			room.setRoomType(roomType);
		}
	}
	
	public void printRooms() {
		for (Room room : this.rooms.values()) {
			System.out.println(room.toString());
		}
	}
}
