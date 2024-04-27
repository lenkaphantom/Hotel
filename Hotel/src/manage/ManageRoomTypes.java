package manage;

import java.util.HashMap;
import java.util.Map;

import entity.RoomType;
import enumeracije.TypeOfRoom;

public class ManageRoomTypes {
	private Map<Integer, RoomType> roomTypes;
	
	// constructors
	public ManageRoomTypes() {
		this.roomTypes = new HashMap<Integer, RoomType>();
	}
	
	// getters
	public Map<Integer, RoomType> getRoomTypes() {
		return this.roomTypes;
	}
	
	// methods
	public void addRoomType(RoomType roomType) {
		this.roomTypes.put(roomType.getId(), roomType);
	}
	
	public void removeRoomType(RoomType roomType) {
		this.roomTypes.remove(roomType.getId());
	}
	
	public void changeRoomType(int id, TypeOfRoom type, int index) {
		if (!this.roomTypes.containsKey(id)) {
			System.out.println("Tip sobe sa id-jem " + id + " ne postoji.");
			return;
		}

		RoomType roomType = this.roomTypes.get(id);

		if (type != null) {
			roomType.setType(type);
		}
		if (index >= 0 && index < type.getBedLayouts().length) {
			roomType.setBeds(index);
		}
	}
	
	public void printRoomTypes() {
		for (RoomType roomType : this.roomTypes.values()) {
			System.out.println(roomType.toString());
		}
	}
}
