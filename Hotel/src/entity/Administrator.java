package entity;

import java.time.LocalDate;

import enumeracije.Gender;

public class Administrator extends User {
	private static int idCounter = 1;
	private int id;
	private boolean isDeleted = false;

	// constructors
	public Administrator() {
		super();
		this.id = idCounter++;
	}

	public Administrator(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password) {
		super(firstName, lastName, gender, date, phone, address, username, password);
		this.id = idCounter++;
	}

	// copy constructor
	public Administrator(User user) {
		super(user);
		this.id = idCounter++;
	}

	// getters
	public int getId() {
		return this.id;
	}
	
	public boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	// setters
	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	// method toString
	@Override
	public String toString() {
		return "----- Administrator " + this.id + " -----\n" + super.toString() + "\n";
	}
	
	public String toStringFile() {
		return this.id + " | " + super.toStringFile();
	}
}
