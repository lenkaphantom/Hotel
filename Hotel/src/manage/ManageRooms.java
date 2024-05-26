package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import entity.Guest;
import entity.Room;
import entity.RoomType;
import enumeracije.Gender;
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

	public void loadRooms(String path, ManageHotel hotel) {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" \\| ");
				this.addRoom(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), RoomStatus.valueOf(parts[3]),
						Integer.parseInt(parts[4]), hotel);
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
}
