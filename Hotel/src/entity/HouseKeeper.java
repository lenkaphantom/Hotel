package entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import enumeracije.Gender;
import enumeracije.Qualifications;
import enumeracije.Type;

public class HouseKeeper extends Employee {
	private Map<LocalDate, Room> roomsToClean = new HashMap<>();

	// constructors
	public HouseKeeper() {
		super();
	}

	public HouseKeeper(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password, Qualifications qualification, double salary, int yearsOfExperience, Type type) {
		super(firstName, lastName, gender, date, phone, address, username, password, qualification, salary,
				yearsOfExperience, type);
	}

	// getters
	public Map<LocalDate, Room> getRoomsToClean() {
		return roomsToClean;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public String toStringForFile() {
		String rooms = "";
		for (LocalDate date : roomsToClean.keySet()) {
			rooms += date + ":" + roomsToClean.get(date).getId() + ",";
		}
		return super.toStringFile() + " | " + rooms;
	}
}