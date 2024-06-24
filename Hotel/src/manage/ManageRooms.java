package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.Room;
import entity.RoomType;
import enumeracije.RoomSpecs;
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
	public void addRoom(int floor, int roomNumber, RoomStatus roomStatus, int idRoomType, ManageHotel hotel,
			ArrayList<RoomSpecs> roomSpecs) {
		Room room = new Room(floor, roomNumber, roomStatus, idRoomType, hotel, roomSpecs);
		this.rooms.put(room.getId(), room);
	}

	public void removeRoom(int id) {
		Room room = this.rooms.get(id);
		this.rooms.remove(room.getId());
	}

	public void changeRoom(int id, int floor, int roomNumber, RoomType roomType, ArrayList<RoomSpecs> roomSpecs) {
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
		if (roomSpecs != null) {
			room.setRoomSpecs(roomSpecs);
		}
	}

	public void printRooms() {
		for (Room room : this.rooms.values()) {
			System.out.println(room.toString());
		}
	}

	public void loadRooms(String path, ManageHotel hotel) {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" \\| ");
				ArrayList<RoomSpecs> roomSpecs = new ArrayList<RoomSpecs>();
				String[] specs = parts[5].split("*");
				for (String spec : specs) {
					roomSpecs.add(RoomSpecs.valueOf(spec));
				}
				this.addRoom(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), RoomStatus.valueOf(parts[3]),
						Integer.parseInt(parts[4]), hotel, roomSpecs);
			}
		} catch (IOException e) {
			System.out.println("Greška prilikom čitanja iz fajla.");
		}
	}

	public void writeRooms(String path) {
		try {
			FileWriter writer = new FileWriter(path);
			for (Room room : this.rooms.values()) {
				writer.write(room.toStringFile() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Greška prilikom upisa u fajl.");
		}
	}

	public boolean isOccupied(int id, LocalDate date1, LocalDate date2) {
		if (!this.rooms.containsKey(id)) {
			System.out.println("Soba sa id-jem " + id + " ne postoji.");
			return false;
		}

		Room room = this.rooms.get(id);

		if (room.getOccupiedDates() == null)
			return false;
		for (LocalDate startDate : room.getOccupiedDates().keySet()) {
			LocalDate endDate = room.getOccupiedDates().get(startDate);
			if (date1.isBefore(endDate) && date2.isAfter(startDate)) {
				return true;
			}
			if (date1.isEqual(endDate) || date2.isEqual(startDate) || date1.isEqual(startDate)
					|| date2.isEqual(endDate)) {
				return true;
			}
			if (date1.isAfter(startDate) && date2.isBefore(endDate)) {
				return true;
			}
		}
		return false;
	}
}
