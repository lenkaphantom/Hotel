package manage;

import java.util.HashMap;
import java.util.Map;

import users.Guest;

public class ManageGuests {
	private static Map<Integer, Guest> guests = new HashMap<>();
	
	// constructors
	private ManageGuests() {}
	
	// getters
	public static Map<Integer, Guest> getGuests() {
		return guests;
	}
	
	// methods
	public static void addGuest(Guest guest) {
		guests.put(guest.getId(), guest);
	}
	
	public static void removeGuest(Guest guest) {
		guests.remove(guest.getId());
	}
	
	public static void changeGuest(int id, String firstName, String lastName, String phone, String address) {
		if (!guests.containsKey(id)) {
			System.out.println("Gost sa id-jem " + id + " ne postoji.");
			return;
		}

		Guest guest = guests.get(id);

		if (firstName != null) {
			guest.setFirstName(firstName);
		}
		if (lastName != null) {
			guest.setLastName(lastName);
		}
		if (phone != null) {
			guest.setPhone(phone);
		}
		if (address != null) {
			guest.setAddress(address);
		}
	}
	
	public static void printGuests() {
		for (Guest guest : guests.values()) {
			System.out.println(guest.toString());
		}
	}
}
