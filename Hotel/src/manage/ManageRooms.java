package manage;

import java.util.HashMap;
import java.util.Map;

import rooms.Room;

public class ManageRooms {
	private static Map<Integer, Room> rooms = new HashMap<Integer, Room>();
	
	// constructors
	private ManageRooms() {}
	
	// getters
	public static Map<Integer, Room> getRooms() {
		return rooms;
	}
	
	// methods
	public static void addRoom(Room room) {
		rooms.put(room.getId(), room);
	}
	
	public static void removeRoom(Room room) {
		rooms.remove(room.getId());
	}
	
	public static void changeRoom(int id, int floor, int roomNumber) {
		if (!rooms.containsKey(id)) {
			System.out.println("Soba sa id-jem " + id + " ne postoji.");
			return;
		}

		Room room = rooms.get(id);

		if (floor != 0) {
			room.setFloor(floor);
		}
		if (roomNumber != 0) {
			room.setRoomNumber(roomNumber);
		}
	}
	
	public static void printRooms() {
		for (Room room : rooms.values()) {
			System.out.println(room.toString());
		}
	}
}
