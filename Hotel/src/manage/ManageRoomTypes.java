package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public String[] getRoomTypesList() {
		String[] types = new String[this.roomTypes.size()];
		int i = 0;
		for (RoomType roomType : this.roomTypes.values()) {
			types[i] = roomType.getType().toString();
			i++;
		}
		return types;
	}

	// methods
	public void addRoomType(TypeOfRoom type, int index) {
		RoomType roomType = new RoomType(type, index);
		this.roomTypes.put(roomType.getId(), roomType);
	}

	public void removeRoomType(int id) {
		RoomType roomType = this.roomTypes.get(id);
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

	public void loadRoomTypes(String path) {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" \\| ");
				this.addRoomType(TypeOfRoom.valueOf(parts[1]), Integer.parseInt(parts[2]));
			}
		} catch (IOException e) {
			System.out.println("Greška prilikom čitanja iz fajla.");
		}
	}
	
	public void writeRoomTypes(String path) {
		try {
			FileWriter writer = new FileWriter(path);
			for (RoomType type : this.roomTypes.values()) {
				writer.write(type.toStringFile() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Greška prilikom upisa u fajl.");
		}
	}
	
	public RoomType getRoomTypeFromType(String type) {
		for (RoomType roomType : this.roomTypes.values()) {
			if (roomType.getType().toString().equals(type)) {
				return roomType;
			}
		}
		return null;
	}
	
	public RoomType getRoomTypeFromTypeAndBeds(TypeOfRoom type, String beds) {
		for (RoomType roomType : this.roomTypes.values()) {
			if (roomType.getType().equals(type) && roomType.getBeds().equals(beds)) {
				return roomType;
			}
		}
		return null;
	}
}
