package manage;

import java.util.ArrayList;
import java.util.List;

import reservations.Reservation;

public class ManageReservation {
	private List<Reservation> reservations;
	
	// constructors
	public ManageReservation() {
	}
	
	public ManageReservation(List<Reservation> reservations) {
		this.reservations = new ArrayList<Reservation>(reservations);
	}
	
	// copy constructor
	public ManageReservation(ManageReservation manageReservation) {
		this.reservations = new ArrayList<Reservation>(manageReservation.reservations);
	}
	
	// getters
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	// setters
	public void setReservations(List<Reservation> reservations) {
		this.reservations = new ArrayList<Reservation>(reservations);
	}
	
	// methods
	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}
	
	public void removeReservation(Reservation reservation) {
		this.reservations.remove(reservation);
	}
	
	public void updateReservation(Reservation reservation, Reservation newReservation) {
		this.reservations.set(this.reservations.indexOf(reservation), newReservation);
	}
	
	public void printReservations() {
		for (Reservation reservation : this.reservations) {
			System.out.println(reservation.toString());
		}
	}
}
