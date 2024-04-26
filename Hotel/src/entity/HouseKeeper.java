package users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enumeracije.Gender;
import enumeracije.Qualifications;
import enumeracije.Type;

import rooms.Room;

public class HouseKeeper extends Employee {
	private List<Room> roomsToClean;

	// constructors
	public HouseKeeper() {
		super();
	}

	public HouseKeeper(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password, Qualifications qualification, double salary, int yearsOfExperience,
			List<Room> roomsToClean) {
		super(firstName, lastName, gender, date, phone, address, username, password, qualification, salary,
				yearsOfExperience, Type.HouseKeeper);
		this.roomsToClean = roomsToClean;
	}

	// getters
	public List<Room> getRoomsToClean() {
		return roomsToClean;
	}

	// setters
	public void setRoomsToClean(List<Room> rooms_to_clean) {
        this.roomsToClean = new ArrayList<>();

        for (Room room : rooms_to_clean) {
            this.roomsToClean.add(new Room(room));
        }
    }
	
	// methods
	@Override
	public String toString() {
		String rooms = "";
		for (Room room : this.roomsToClean) {
			rooms += room.getId() + "*";
		}
		return super.toString() + " | " + rooms.substring(0, rooms.length() - 1);
	}
}