package users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import rooms.Room;

public class HouseKeeper extends Employee {
	List<Room> roomsToClean;

	// constructors
	public HouseKeeper() {
		super();
	}

	public HouseKeeper(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password, Qualifications qualification, double salary, int yearsOfExperience,
			Type type, List<Room> roomsToClean) {
		super(firstName, lastName, gender, date, phone, address, username, password, qualification, salary,
				yearsOfExperience, type);
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
		return super.toString();
	}
}