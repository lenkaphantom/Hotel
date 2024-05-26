package entity;

import java.time.LocalDate;
import java.util.Map;

import enumeracije.Gender;
import enumeracije.Qualifications;
import enumeracije.ReservationStatus;
import enumeracije.RoomStatus;
import enumeracije.Type;
import manage.ManageHotel;

public class Receptionist extends Employee {
	private Reservation reservation;

	// constructors
	public Receptionist() {
		super();
	}

	public Receptionist(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password, Qualifications qualification, double salary, int yearsOfExperience, Type type) {
		super(firstName, lastName, gender, date, phone, address, username, password, qualification, salary,
				yearsOfExperience, type);
	}

	// copy constructor
	public Receptionist(Receptionist receptionist) {
		super(receptionist);
	}

	// getters
	public Reservation getReservation() {
		return this.reservation;
	}
	
	// setters
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	public String toStringFile() {
		return super.toStringFile();
	}
}
