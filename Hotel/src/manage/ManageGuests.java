package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.AdditionalServices;
import entity.Employee;
import entity.Guest;
import entity.Reservation;
import entity.RoomType;
import enumeracije.Gender;
import enumeracije.Qualifications;
import enumeracije.ReservationStatus;
import enumeracije.Type;

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
		this.guests.remove(guest.getId());
	}

	public void changeGuest(int id, String firstName, String lastName, Gender gender, String phone, String address) {
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
		if (gender != null) {
			guest.setGender(gender);
		}
	}

	public void printGuests() {
		for (Guest guest : this.guests.values()) {
			System.out.println(guest.toString());
		}
	}

	public void loadGuests(String path) {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" \\| ");
				this.addGuest(parts[1], parts[2], Gender.valueOf(parts[3]), LocalDate.parse(parts[4]), parts[5],
						parts[6], parts[7], parts[8]);
			}
		} catch (IOException e) {
			System.out.println("Greška prilikom čitanja iz fajla.");
		}
	}

	public void writeGuests(String path) {
		try {
			FileWriter writer = new FileWriter(path);
			for (Guest guest : this.guests.values()) {
				writer.write(guest.toStringFile() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Greška prilikom upisa u fajl.");
		}
	}
}
