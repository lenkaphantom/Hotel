package manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enumeracije.ReservationStatus;
import reservations.AdditionalServices;
import reservations.Reservation;
import rooms.Room;
import rooms.RoomType;

public class ManageReservations {
	private static Map<Integer, Reservation> reservations = new HashMap<Integer, Reservation>();

	// constructors
	private ManageReservations() {
	}

	// getters
	public static Map<Integer, Reservation> getReservations() {
		return reservations;
	}

	// methods
	public static void addReservation(Reservation reservation) {
		reservations.put(reservation.getId(), reservation);
	}

	public static void removeReservation(Reservation reservation) {
		reservations.remove(reservation.getId());
	}

	public static void changeReservation(int id, RoomType roomType, List<AdditionalServices> additionalServices,
			ReservationStatus status, Room room) {
		if (!reservations.containsKey(id)) {
			System.out.println("Rezervacija sa id-jem " + id + " ne postoji.");
			return;
		}

		Reservation reservation = reservations.get(id);
		
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
	
	public static void printReservations() {
		for (Reservation reservation : reservations.values()) {
			System.out.println(reservation.toString());
		}
	}
}
