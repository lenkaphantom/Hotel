package controler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.HouseKeeper;
import entity.Room;
import manage.ManageHotel;

public class RoomControler {
	private ManageHotel manager = ManageHotel.getInstance();
	private String username;
	private HouseKeeper houseKeeper;
	private Map<Integer, Room> rooms = new HashMap<>();

	public RoomControler(String username, LocalDate date) {
		this.username = username;
		this.houseKeeper = manager.getEmployeesMan().getHouseKeeperFromUsername(username);
		if (username == "")
			this.rooms.putAll(manager.getRoomsMan().getRooms());
		else
			this.setRooms(date);
	}

	public String getUsername() {
		return this.username;
	}

	public Map<Integer, Room> getRooms() {
		return this.rooms;
	}
	
	public void setRooms(LocalDate date) {
		if (houseKeeper.getRoomsToClean().get(date) == null) {
			this.rooms.clear();
			return;
		}
		List<Room> roomsToClean = houseKeeper.getRoomsToClean().get(date);
		for (Room room : roomsToClean) {
			this.rooms.put(room.getId(), room);
		}
	}

	public void updateRooms(LocalDate date) {
		if (username == "") {
			this.rooms.clear();
			this.rooms.putAll(manager.getRoomsMan().getRooms());
		} else {
			this.rooms.clear();
			this.setRooms(date);
		}
	}

}
