package entity;

import java.time.LocalDate;
import java.util.List;

import enumeracije.Gender;
import enumeracije.ReservationStatus;

public class Guest extends User {
	private static int idCounter = 1;
	private int id;
	private boolean isDeleted = false;

	// constructors
	public Guest() {
		super();
		this.id = idCounter++;
	}

	public Guest(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password) {
		super(firstName, lastName, gender, date, phone, address, username, password);
		this.id = idCounter++;
	}

	// copy constructor
	public Guest(Guest guest) {
		super(guest);
		this.id = idCounter++;
	}

	// getters
	public int getId() {
		return this.id;
	}
	
	public boolean isDeleted() {
		return this.isDeleted;
	}
	
	// setters
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	// methods
	@Override
	public String toString() {
		return "--------- Gost " + this.id + " ---------\n" + super.toString() + "\n";
	}
	
	public String toStringFile() {
		return this.id + " | " + super.toStringFile() + " | " + this.isDeleted;
	}
}
