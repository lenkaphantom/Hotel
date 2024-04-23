package manage;

import java.util.HashMap;
import java.util.Map;

import rooms.RoomType;

public class ManageRoomTypes {
	private static Map<Integer, RoomType> roomTypes = new HashMap<Integer, RoomType>();
	
	// constructors
	private ManageRoomTypes() {}
	
	// getters
	public static Map<Integer, RoomType> getRoomTypes() {
		return roomTypes;
	}
	
	// methods
	public static void addRoomType(RoomType roomType) {
		roomTypes.put(roomType.getId(), roomType);
	}
	
	public static void removeRoomType(RoomType roomType) {
		roomTypes.remove(roomType.getId());
	}
	
	public static void changeRoomType(int id, String type) {
		if (!roomTypes.containsKey(id)) {
			System.out.println("Tip sobe sa id-jem " + id + " ne postoji.");
			return;
		}

		RoomType roomType = roomTypes.get(id);

		if (type != null) {
			roomType.setType(type);
		}
	}
	
	public static void printRoomTypes() {
		for (RoomType roomType : roomTypes.values()) {
			System.out.println(roomType.toString());
		}
	}
}
