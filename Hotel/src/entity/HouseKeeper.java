package entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enumeracije.Gender;
import enumeracije.Qualifications;
import enumeracije.Type;

public class HouseKeeper extends Employee {
	private Map<LocalDate, List<Room>> roomsToClean = new HashMap<>();

	// constructors
	public HouseKeeper() {
		super();
	}

	public HouseKeeper(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password, Qualifications qualification, int yearsOfExperience, Type type) {
		super(firstName, lastName, gender, date, phone, address, username, password, qualification,
				yearsOfExperience, type);
	}

	// getters
	public Map<LocalDate, List<Room>> getRoomsToClean() {
		return roomsToClean;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
	public String toStringForFile() {
	    String rooms = "";
	    for (LocalDate date : roomsToClean.keySet()) {
	        rooms += date + ":";
	        for (Room room : roomsToClean.get(date)) {
	            rooms += room.getId() + "*";
	        }
	        rooms = rooms.substring(0, rooms.length() - 1);
	    }
	    return super.toStringFile() + " | " + rooms;
	}
}