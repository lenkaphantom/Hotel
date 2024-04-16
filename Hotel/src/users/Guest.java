package users;

import java.time.LocalDate;

public class Guest extends User {
	// constructors
	public Guest() {
		super();
	}

	public Guest(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password) {
		super(firstName, lastName, gender, date, phone, address, username, password);
	}
	
	// copy constructor
	public Guest(Guest guest) {
		super(guest);
	}
	
	// methods
	@Override
	public String toString() {
		return super.toString();
	}
	
	public void makeReservation() {
		System.out.println("Reservation made successfully!");
	}
}
