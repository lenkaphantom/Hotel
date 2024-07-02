package manage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import entity.AdditionalServices;
import entity.HouseKeeper;
import entity.Prices;
import entity.Reservation;
import entity.Room;
import entity.RoomType;
import enumeracije.ReservationStatus;
import enumeracije.RoomSpecs;
import enumeracije.RoomStatus;
import enumeracije.TypeOfRoom;

public class ManageHotel {
	private ManageAdditionalServices additionalServicesMan;
	private ManageAdministrators administratorsMan;
	private ManageEmployees employeesMan;
	private ManageGuests guestsMan;
	private ManagePrices pricesMan;
	private ManageReservations reservationsMan;
	private ManageRooms roomsMan;
	private ManageRoomTypes roomTypesMan;

	private static ManageHotel instance;

	private ManageHotel() {
		this.additionalServicesMan = new ManageAdditionalServices();
		this.administratorsMan = new ManageAdministrators();
		this.employeesMan = new ManageEmployees();
		this.guestsMan = new ManageGuests();
		this.pricesMan = new ManagePrices();
		this.reservationsMan = new ManageReservations();
		this.roomsMan = new ManageRooms();
		this.roomTypesMan = new ManageRoomTypes();
	}

	public static ManageHotel getInstance() {
		if (instance == null) {
			synchronized (ManageHotel.class) {
				if (instance == null) {
					instance = new ManageHotel();
				}
			}
		}
		return instance;
	}

	// Getters
	public ManageAdditionalServices getAdditionalServicesMan() {
		return additionalServicesMan;
	}

	public ManageAdministrators getAdministratorsMan() {
		return administratorsMan;
	}

	public ManageEmployees getEmployeesMan() {
		return employeesMan;
	}

	public ManageGuests getGuestsMan() {
		return guestsMan;
	}

	public ManagePrices getPricesMan() {
		return pricesMan;
	}

	public ManageReservations getReservationsMan() {
		return reservationsMan;
	}

	public ManageRooms getRoomsMan() {
		return roomsMan;
	}

	public ManageRoomTypes getRoomTypesMan() {
		return roomTypesMan;
	}

	// methods
	public void loadData() {
		this.getAdditionalServicesMan().loadAdditionalServices("data/additional_services.csv");
		this.getAdministratorsMan().loadAdministrators("data/administrators.csv");
		this.getGuestsMan().loadGuests("data/guests.csv");
		this.getRoomTypesMan().loadRoomTypes("data/room_types.csv");
		this.getRoomsMan().loadRooms("data/rooms.csv", this);
		this.getEmployeesMan().loadEmployees("data/employees.csv", this);
		this.getPricesMan().loadPrices("data/prices.csv", this);
		this.getReservationsMan().loadReservations("data/reservations.csv", this);

		for (Room room : this.roomsMan.getRooms().values()) {
			room.setOccupiedDates(this);
		}

		for (Reservation reservation : this.reservationsMan.getReservations().values()) {
			if (reservation.getRoom() == null) {
				continue;
			}
			if (reservation.getCheckInDate() == null && LocalDate.now().isAfter(reservation.getStartDate())) {
				reservation.setStatus(ReservationStatus.EXPIRED);
			}
		}
	}

	public void writeData() {
		this.getAdditionalServicesMan().writeAdditionalServices("data/additional_services.csv");
		this.getAdministratorsMan().writeAdministrators("data/administrators.csv");
		this.getEmployeesMan().writeEmployees("data/employees.csv");
		this.getGuestsMan().writeGuests("data/guests.csv");
		this.getRoomsMan().writeRooms("data/rooms.csv");
		this.getRoomTypesMan().writeRoomTypes("data/room_types.csv");
		this.getPricesMan().writePrices("data/prices.csv");
		this.getReservationsMan().writeReservations("data/reservations.csv");
	}

	public void makeReservation(int guestID, int roomTypeID, LocalDate startDate, LocalDate endDate,
			List<AdditionalServices> additionalServices) {
		if (!this.roomTypesMan.getRoomTypes().containsKey(roomTypeID)) {
			System.out.println("Tip sobe sa id-jem " + roomTypeID + " ne postoji.");
			return;
		}
		if (!this.guestsMan.getGuests().containsKey(guestID)) {
			System.out.println("Gost sa id-jem " + guestID + " ne postoji.");
			return;
		}

		RoomType roomType = this.roomTypesMan.getRoomTypes().get(roomTypeID);
		Reservation reservation = new Reservation(roomType, startDate, endDate, additionalServices,
				ReservationStatus.WAITING, LocalDate.now());
		reservation.setGuest(guestID, this);
		this.calculatePrice(reservation.getId());

		this.reservationsMan.addReservation(reservation);
	}

	public void cancleReservation(int id) {
		if (!this.reservationsMan.getReservations().containsKey(id)) {
			System.out.println("Rezervacija sa id-jem " + id + " ne postoji.");
			return;
		}

		Reservation reservation = this.reservationsMan.getReservations().get(id);
		reservation.setStatus(ReservationStatus.CANCELLED);
	}

	public int checkIn(int id) {
		if (!this.reservationsMan.getReservations().containsKey(id)) {
			System.out.println("Rezervacija sa id-jem " + id + " ne postoji.");
			return 1;
		}

		if (LocalDate.now().isBefore(this.reservationsMan.getReservations().get(id).getStartDate())) {
			System.out.println("Ne može se izvršiti check-in pre datuma početka rezervacije.");
			return -1;
		} else if (LocalDate.now().isAfter(this.reservationsMan.getReservations().get(id).getEndDate())) {
			System.out.println("Rezervacija je istekla.");
			this.reservationsMan.getReservations().get(id).setStatus(ReservationStatus.EXPIRED);
			return -2;
		}

		Reservation reservation = this.reservationsMan.getReservations().get(id);
		if (reservation.getCheckInDate() != null)
			return -3;
		this.assignRoomAtCheckIn(reservation.getId());
		reservation.getRoom().setRoomStatus(RoomStatus.OCCUPIED);
		this.calculatePrice(id);
		reservation.setCheckInDate(LocalDate.now());

		return 0;
	}

	public void checkOut(int id) {
		if (!this.reservationsMan.getReservations().containsKey(id)) {
			System.out.println("Rezervacija sa id-jem " + id + " ne postoji.");
			return;
		}

		Reservation reservation = this.reservationsMan.getReservations().get(id);
		reservation.getRoom().setRoomStatus(RoomStatus.CLEANING);
		reservation.setCheckOutDate(LocalDate.now());
		this.addRoomToHouseKeeper(reservation.getRoom().getId());
		reservation.getRoom().setOccupiedDates(this);
	}

	public void addRoomToHouseKeeper(int id) {
		if (!this.roomsMan.getRooms().containsKey(id)) {
			System.out.println("Soba sa id-jem " + id + " ne postoji.");
			return;
		}

		Room room = this.roomsMan.getRooms().get(id);

		int houseKeeperId = 2;
		int minCleaning;
		minCleaning = Integer.MAX_VALUE;
		for (HouseKeeper houseKeeper : this.employeesMan.getHouseKeepers().values()) {
			if (houseKeeper.getRoomsToClean().isEmpty()) {
				minCleaning = 0;
				houseKeeperId = houseKeeper.getId();
				break;
			} else if (this.getEmployeesMan().getNumberOfRoomsToClean(houseKeeper.getId(),
					LocalDate.now()) < minCleaning) {
				minCleaning = this.getEmployeesMan().getNumberOfRoomsToClean(houseKeeper.getId(), LocalDate.now());
				houseKeeperId = houseKeeper.getId();
			}
		}
		if (this.employeesMan.getHouseKeepers().get(houseKeeperId).getRoomsToClean().get(LocalDate.now()) == null) {
			this.employeesMan.getHouseKeepers().get(houseKeeperId).getRoomsToClean().put(LocalDate.now(),
					new ArrayList<>());
		}
		List<Room> roomsToClean = this.employeesMan.getHouseKeepers().get(houseKeeperId).getRoomsToClean()
				.get(LocalDate.now());
		roomsToClean.add(room);
	}

	public void cleanRoom(int idHK, int idRoom) {
		if (!this.employeesMan.getHouseKeepers().containsKey(idHK)) {
			System.out.println("Housekeeper sa id-jem " + idHK + " ne postoji.");
			return;
		}

		if (!this.roomsMan.getRooms().containsKey(idRoom)) {
			System.out.println("Soba sa id-jem " + idRoom + " ne postoji.");
			return;
		}

		Room room = this.roomsMan.getRooms().get(idRoom);
		room.setRoomStatus(RoomStatus.FREE);
	}

	public int numberOfAvailableRooms(RoomType roomType, LocalDate startDate, LocalDate endDate) {
		Map<Integer, Room> rooms = this.roomsMan.getRooms();
		int availableCount = 0;
		for (Room room : rooms.values()) {
			if (room.getRoomType().equals(roomType) && !this.roomsMan.isOccupied(room.getId(), startDate, endDate)) {
				availableCount++;
			}
		}
		System.out.println(availableCount);
		return availableCount;
	}

	public int numberOfReservations(RoomType roomType, LocalDate startDate, LocalDate endDate) {
		int count = 0;
		for (Reservation reservation : this.reservationsMan.getReservations().values()) {
			if (reservation.getCheckInDate() == null)
				continue;
			if (reservation.getRoomType().equals(roomType) && !startDate.isAfter(reservation.getCheckOutDate())
					&& !endDate.isBefore(reservation.getCheckInDate())) {
				count++;
			}
		}
		return count;
	}

	public void checkAvailableRoom(int id) {
		if (!this.reservationsMan.getReservations().containsKey(id)) {
			System.out.println("Rezervacija sa id-jem " + id + " ne postoji.");
			return;
		}

		Reservation reservation = this.reservationsMan.getReservations().get(id);
		int availableRooms = numberOfAvailableRooms(reservation.getRoomType(), reservation.getStartDate(),
				reservation.getEndDate());
		int reservations = numberOfReservations(reservation.getRoomType(), reservation.getStartDate(),
				reservation.getEndDate());

		if (availableRooms >= reservations) {
			reservation.setStatus(ReservationStatus.CONFIRMED);
		} else {
			reservation.setStatus(ReservationStatus.DENIED);
			reservation.setTotalPrice(0.0);
		}
	}

	public void assignRoomAtCheckIn(int id) {
		if (!this.reservationsMan.getReservations().containsKey(id)) {
			System.out.println("Rezervacija sa id-jem " + id + " ne postoji.");
			return;
		}

		Reservation reservation = this.reservationsMan.getReservations().get(id);
		if (reservation.getStatus() != ReservationStatus.CONFIRMED) {
			System.out.println("Rezervacija sa id-jem " + id + " nije potvrđena.");
			return;
		}

		Map<Integer, Room> rooms = this.roomsMan.getRooms();
		for (Room room : rooms.values()) {
			if (room.getRoomType().equals(reservation.getRoomType())
					&& !this.roomsMan.isOccupied(room.getId(), reservation.getStartDate(), reservation.getEndDate())) {
				room.setRoomStatus(RoomStatus.OCCUPIED);
				reservation.setRoom(room);
				room.setOccupiedDates(this);
				return;
			}
		}
	}

	public void calculatePrice(int id) {
		if (!this.reservationsMan.getReservations().containsKey(id)) {
			System.out.println("Rezervacija sa id-jem " + id + " ne postoji.");
			return;
		}

		Reservation reservation = this.reservationsMan.getReservations().get(id);
		LocalDate startDate = reservation.getStartDate();
		LocalDate endDate = reservation.getEndDate();
		RoomType roomType = reservation.getRoomType();
		List<AdditionalServices> additionalServices = reservation.getAdditionalServices();

		double totalPrice = 0;

		Map<Integer, Prices> prices = this.pricesMan.getPrices();

		for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
			Prices validPrices = null;
			LocalDate latestStartDate = LocalDate.MIN;

			for (Prices price : prices.values()) {
				if (!date.isBefore(price.getStartDate()) && !date.isAfter(price.getEndDate())) {
					if (price.getStartDate().isAfter(latestStartDate)) {
						latestStartDate = price.getStartDate();
						validPrices = price;
					}
				}
			}

			if (validPrices != null) {
				Double roomPrice = validPrices.getPricePerRoom().get(roomType);
				if (roomPrice != null) {
					totalPrice += roomPrice;
				}

				for (AdditionalServices service : additionalServices) {
					Double servicePrice = validPrices.getPricePerService().get(service);
					if (servicePrice != null) {
						totalPrice += servicePrice;
					}
				}
			}
		}
		totalPrice = Math.round(totalPrice * 100.0) / 100.0;
		reservation.setTotalPrice(totalPrice);
	}

	public List<String> getAdditionalServicesList(int id) {
		if (!this.reservationsMan.getReservations().containsKey(id)) {
			System.out.println("Rezervacija sa id-jem " + id + " ne postoji.");
			return null;
		}

		Reservation reservation = this.reservationsMan.getReservations().get(id);
		List<String> additionalServices = new ArrayList<>();
		for (AdditionalServices addSer : reservation.getAdditionalServices()) {
			additionalServices.add(addSer.getService());
		}
		return additionalServices;
	}

	public boolean checkRoomType(int id) {
		if (!this.roomTypesMan.getRoomTypes().containsKey(id)) {
			JOptionPane.showMessageDialog(null, "Tip sobe sa id-jem " + id + " ne postoji.", "Greška",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		for (Room room : this.roomsMan.getRooms().values()) {
			if (room.getRoomType().getId() == id) {
				JOptionPane.showMessageDialog(null, "Tip sobe sa id-jem " + id + " se koristi.", "Greška",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		return true;
	}

	public List<String> getRoomsSpecsList(int id) {
		if (!this.roomTypesMan.getRoomTypes().containsKey(id)) {
			JOptionPane.showMessageDialog(null, "Tip sobe sa id-jem " + id + " ne postoji.", "Greška",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}

		List<String> roomsSpec = new ArrayList<>();
		for (RoomSpecs spec : this.roomsMan.getRooms().get(id).getRoomSpecs()) {
			roomsSpec.add(spec.toString());
		}
		return roomsSpec;
	}

	public ArrayList<RoomSpecs> getRoomSpecsFromStrings(List<String> selectedSpecs) {
		ArrayList<RoomSpecs> specs = new ArrayList<>();
		for (String spec : selectedSpecs) {
			specs.add(RoomSpecs.valueOf(spec));
		}
		return specs;
	}
}