package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enumeracije.Gender;
import enumeracije.Qualifications;
import enumeracije.Type;

public class HouseKeeper extends Employee {
	private List<Room> roomsToClean = new ArrayList<>();

	// constructors
	public HouseKeeper() {
		super();
	}

	public HouseKeeper(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password, Qualifications qualification, double salary, int yearsOfExperience) {
		super(firstName, lastName, gender, date, phone, address, username, password, qualification, salary,
				yearsOfExperience, Type.HouseKeeper);
	}

	// getters
	public List<Room> getRoomsToClean() {
		return roomsToClean;
	}
	
	// methods
	public void addRoomToClean(Room room) {
		this.roomsToClean.add(room);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public String toStringForFile() {
		return super.toStringFile();
	}
}