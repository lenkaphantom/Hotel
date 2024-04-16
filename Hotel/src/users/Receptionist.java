package users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import reservations.Reservation;
import rooms.Room;

public class Receptionist extends Employee{
	private Reservation reservation;
	private List<Room> roomsInHotel;
	
	// constructors
	public Receptionist() {
		super();
	}
	
	public Receptionist(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password, Qualifications qualification, double salary, int yearsOfExperience,
			Type type, Reservation reservation, List<Room> roomsInHotel) {
		super(firstName, lastName, gender, date, phone, address, username, password, qualification, salary, yearsOfExperience,
				type);
		this.reservation = new Reservation(reservation);
		this.roomsInHotel = new ArrayList<>(roomsInHotel);
	}
	
	// copy constructor
	public Receptionist(Receptionist receptionist) {
		super(receptionist);
		this.reservation = new Reservation(receptionist.reservation);
		this.roomsInHotel = new ArrayList<>(receptionist.roomsInHotel);
	}
	
	// getters
	public Reservation getReservation() {
		return reservation;
	}
	
	public List<Room> getRoomsInHotel() {
		return roomsInHotel;
	}
	
	// setters
	public void setReservation(Reservation reservation) {
		this.reservation = new Reservation(reservation);
	}
	
	public void setRoomsInHotel(List<Room> roomsInHotel) {
		this.roomsInHotel = new ArrayList<>(roomsInHotel);
	}
}
