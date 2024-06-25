package controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Room;
import entity.RoomType;
import enumeracije.RoomSpecs;
import enumeracije.TypeOfRoom;
import manage.ManageHotel;

public class RoomTypeControler {
	private ManageHotel manager = ManageHotel.getInstance();
	private List<String> roomSpecs;
	private Map<Integer, RoomType> roomTypes = new HashMap<>();
	
	public RoomTypeControler(List<String> roomSpecs) {
		this.roomSpecs = roomSpecs;
		if (roomSpecs == null || roomSpecs.isEmpty())
			this.roomTypes.putAll(manager.getRoomTypesMan().getRoomTypes());
		else
			this.setRoomTypes();
	}
	
	public Map<Integer, RoomType> getRoomTypes() {
		return this.roomTypes;
	}
	
	public void setRoomTypes() {
		for (RoomType roomType : manager.getRoomTypesMan().getRoomTypes().values()) {
			for (Room room : manager.getRoomsMan().getRooms().values()) {
				if (roomType.equals(room.getRoomType())) {
					boolean contains = true;
					for (String spec : this.roomSpecs) {
						if (!room.getRoomSpecs().contains(RoomSpecs.valueOf(spec))) {
							contains = false;
							break;
						}
					}
					if (contains)
						this.roomTypes.put(roomType.getId(), roomType);
				}
			}
		}
	}
	
	public void updateRoomTypes() {
		if (roomSpecs.isEmpty()) {
			this.roomTypes.clear();
			this.roomTypes.putAll(manager.getRoomTypesMan().getRoomTypes());
		} else {
			this.roomTypes.clear();
			this.setRoomTypes();
		}
	}
	
	public List<TypeOfRoom> getRoomTypesListEnum() {
		List<TypeOfRoom> types = new ArrayList<>();
		for (RoomType roomType : this.roomTypes.values()) {
			if (!types.contains(roomType.getType()))
				types.add(roomType.getType());
		}
		return types;
	}
}
