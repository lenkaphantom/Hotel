package users;

import java.time.LocalDate;

import enumeracije.Gender;
import reservations.Reservation;

public class Guest extends User {
	private static int idCounter = 1;
	private int id;
	
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
	
	// methods
	@Override
	public String toString() {
		return super.toString();
	}
	
	public void makeReservation() {
		Reservation reservation = new Reservation();
		System.out.println("Reservation made successfully!");
	}
}
