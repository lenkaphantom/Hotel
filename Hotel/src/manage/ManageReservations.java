package manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.AdditionalServices;
import entity.Reservation;
import entity.Room;
import entity.RoomType;
import enumeracije.ReservationStatus;

public class ManageReservations {
	private Map<Integer, Reservation> reservations;

	// constructors
	public ManageReservations() {
		this.reservations = new HashMap<Integer, Reservation>();
	}

	// getters
	public Map<Integer, Reservation> getReservations() {
		return this.reservations;
	}

	// methods
	public void addReservation(Reservation reservation) {
		this.reservations.put(reservation.getId(), reservation);
	}

	public void removeReservation(Reservation reservation) {
		this.reservations.remove(reservation.getId());
	}

	public void changeReservation(int id, RoomType roomType, List<AdditionalServices> additionalServices,
			ReservationStatus status, Room room) {
		if (!this.reservations.containsKey(id)) {
			System.out.println("Rezervacija sa id-jem " + id + " ne postoji.");
			return;
		}

		Reservation reservation = this.reservations.get(id);
		
		if (roomType != null) {
			reservation.setRoomType(roomType);
		}
		if (additionalServices != null) {
			reservation.setAdditionalServices(additionalServices);
		}
		if (status != null) {
			reservation.setStatus(status);
		}
		if (room != null) {
			reservation.setRoom(room);
		}
	}
	
	public void printReservations() {
		for (Reservation reservation : this.reservations.values()) {
			System.out.println(reservation.toString());
		}
	}
}
