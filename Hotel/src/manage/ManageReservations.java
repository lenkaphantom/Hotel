package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.AdditionalServices;
import entity.Guest;
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

	public void removeReservation(int id) {
		Reservation reservation = this.reservations.get(id);
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

	public void loadReservations(String path, ManageHotel manager) {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" \\| ");
				String id = parts[0].trim();
				LocalDate startDate = LocalDate.parse(parts[1].trim());
				LocalDate endDate = LocalDate.parse(parts[2].trim());
				ReservationStatus status = ReservationStatus.valueOf(parts[3].trim());
				int roomTypeId = Integer.parseInt(parts[4].trim());
				RoomType roomType = manager.getRoomTypesMan().getRoomTypes().get(roomTypeId);

				List<AdditionalServices> additionalServices = new ArrayList<>();
				if (!parts[5].trim().isEmpty()) {
					String[] serviceIds = parts[5].trim().split("\\*");
					for (String serviceId : serviceIds) {
						additionalServices.add(manager.getAdditionalServicesMan().getAdditionalServices()
								.get(Integer.parseInt(serviceId)));
					}
				}
				
				Reservation reservation = new Reservation(roomType, startDate, endDate, additionalServices, status);

				Room room = null;
				if (parts.length > 6 && !parts[6].trim().isEmpty()) {
					int roomId = Integer.parseInt(parts[6].trim());
					room = manager.getRoomsMan().getRooms().get(roomId);
					reservation.setRoom(room);
				}

				Guest guest = null;
				if (parts.length > 7 && !parts[7].trim().isEmpty()) {
					int guestId = Integer.parseInt(parts[7].trim());
					reservation.setGuest(guestId, manager);
				}
			}
		} catch (IOException e) {
			System.out.println("Greška prilikom čitanja iz fajla.");
		}
	}
	
	public void writeReservations(String path) {
		try {
			FileWriter writer = new FileWriter(path);
			for (Reservation reservation : this.reservations.values()) {
				writer.write(reservation.toStringFile() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Greška prilikom upisa u fajl.");
		}
	}
}
