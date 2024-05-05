package manage;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import entity.Guest;
import enumeracije.Gender;

public class ManageGuests {
	private Map<Integer, Guest> guests;
	
	// constructors
	public ManageGuests() {
		this.guests = new HashMap<Integer, Guest>();
	}
	
	// getters
	public Map<Integer, Guest> getGuests() {
		return this.guests;
	}
	
	// methods
	public void addGuest(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password) {
		Guest guest = new Guest(firstName, lastName, gender, date, phone, address, username, password);
		this.guests.put(guest.getId(), guest);
	}
	
	public void removeGuest(int id) {
		Guest guest = this.guests.get(id);
		guest.setIsDeleted(true);
		this.guests.remove(guest.getId());
	}
	
	public void changeGuest(int id, String firstName, String lastName, String phone, String address) {
		if (!this.guests.containsKey(id)) {
			System.out.println("Gost sa id-jem " + id + " ne postoji.");
			return;
		}

		Guest guest = this.guests.get(id);

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
	
	public void printGuests() {
		for (Guest guest : this.guests.values()) {
			System.out.println(guest.toString());
		}
	}
}
