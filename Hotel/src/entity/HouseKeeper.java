package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enumeracije.Gender;
import enumeracije.Qualifications;
import enumeracije.Type;

public class HouseKeeper extends Employee {
	private List<Room> roomsToClean;

	// constructors
	public HouseKeeper() {
		super();
	}

	public HouseKeeper(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password, Qualifications qualification, double salary, int yearsOfExperience) {
		super(firstName, lastName, gender, date, phone, address, username, password, qualification, salary,
				yearsOfExperience, Type.HouseKeeper);
		this.roomsToClean = new ArrayList<>();
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