package controler;

import java.util.HashMap;
import java.util.Map;

import entity.Guest;
import entity.Reservation;
import manage.ManageHotel;

public class ReservationControler {
	private ManageHotel manager = ManageHotel.getInstance();
	private Guest guest;
	private Map<Integer, Reservation> reservations = new HashMap<>();
	
	public ReservationControler(Guest guest) {
		this.guest = guest;
		this.setReservations();
	}
	
	public void setReservations() {
		for (Map.Entry<Integer, Reservation> entry : manager.getReservationsMan().getReservations().entrySet()) {
			if (entry.getValue().getGuest().getUsername().equals(this.guest.getUsername())) {
				reservations.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public Map<Integer, Reservation> getReservations() {
		return reservations;
	}
}
