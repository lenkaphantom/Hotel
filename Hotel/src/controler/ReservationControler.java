package controler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.AdditionalServices;
import entity.Reservation;
import manage.ManageHotel;

public class ReservationControler {
	private ManageHotel manager = ManageHotel.getInstance();
	private String username;
	private Map<Integer, Reservation> reservations = new HashMap<>();
	
	public ReservationControler(String username) {
		this.username = username;
		this.setReservations();
	}
	
	public void setReservations() {
		for (Map.Entry<Integer, Reservation> entry : manager.getReservationsMan().getReservations().entrySet()) {
			if (entry.getValue().getGuest().getUsername().equals(username)) {
				reservations.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public Map<Integer, Reservation> getReservations() {
		return reservations;
	}
	
	public List<String> getAdditionalServices(int id) {
		List<String> additionalServices = new ArrayList<>();
		for (AdditionalServices service : manager.getReservationsMan().getReservations().get(id).getAdditionalServices()) {
			additionalServices.add(service.getService());
		}
		return additionalServices;
	}
}
