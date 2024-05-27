package manage;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import entity.AdditionalServices;
import entity.HouseKeeper;
import entity.Prices;
import entity.Reservation;
import entity.Room;
import entity.RoomType;
import enumeracije.ReservationStatus;
import enumeracije.RoomStatus;

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
		this.getEmployeesMan().loadEmployees("data/employees.csv");
		this.getGuestsMan().loadGuests("data/guests.csv");
		this.getRoomTypesMan().loadRoomTypes("data/room_types.csv");
		this.getRoomsMan().loadRooms("data/rooms.csv", this);
		this.getPricesMan().loadPrices("data/prices.csv", this);
		this.getReservationsMan().loadReservations("data/reservations.csv", this);
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
				ReservationStatus.WAITING);
		reservation.setGuest(guestID, this);

		this.reservationsMan.addReservation(reservation);
	}

	public void checkIn(int id, List<AdditionalServices> additionalServices) {
		if (!this.reservationsMan.getReservations().containsKey(id)) {
			System.out.println("Rezervacija sa id-jem " + id + " ne postoji.");
			return;
		}

		Reservation reservation = this.reservationsMan.getReservations().get(id);

		reservation.getRoom().setRoomStatus(RoomStatus.OCCUPIED);
		if (additionalServices != null) {
			reservation.getAdditionalServices().addAll(additionalServices);
		}
		this.calculatePrice(id);
	}

	public void checkOut(int id) {
		if (!this.reservationsMan.getReservations().containsKey(id)) {
			System.out.println("Rezervacija sa id-jem " + id + " ne postoji.");
			return;
		}

		Reservation reservation = this.reservationsMan.getReservations().get(id);

		reservation.getRoom().setRoomStatus(RoomStatus.CLEANING);
		addRoomToHouseKeeper(reservation.getRoom().getId());
	}

	public void addRoomToHouseKeeper(int id) {
		if (!this.roomsMan.getRooms().containsKey(id)) {
			System.out.println("Soba sa id-jem " + id + " ne postoji.");
			return;
		}

		Room room = this.roomsMan.getRooms().get(id);

		int houseKeeperId = 2;
		int minCleaning;
		if (this.employeesMan.getHouseKeepers().get(houseKeeperId).getRoomsToClean().isEmpty()) {
			minCleaning = Integer.MAX_VALUE;
		} else {
			minCleaning = this.employeesMan.getHouseKeepers().get(houseKeeperId).getRoomsToClean().size();
		}
		for (HouseKeeper houseKeeper : this.employeesMan.getHouseKeepers().values()) {
			if (houseKeeper.isDeleted()) {
				continue;
			}
			if (houseKeeper.getRoomsToClean().isEmpty()) {
				minCleaning = 0;
				houseKeeperId = houseKeeper.getId();
				break;
			} else if (houseKeeper.getRoomsToClean().size() < minCleaning) {
				minCleaning = houseKeeper.getRoomsToClean().size();
				houseKeeperId = houseKeeper.getId();
			}
		}

		this.employeesMan.getHouseKeepers().get(houseKeeperId).getRoomsToClean().add(room);
	}

	public void cleanRooms(int id) {
		if (!this.employeesMan.getHouseKeepers().containsKey(id)) {
			System.out.println("Housekeeper sa id-jem " + id + " ne postoji.");
			return;
		}

		HouseKeeper houseKeeper = this.employeesMan.getHouseKeepers().get(id);

		for (Room room : houseKeeper.getRoomsToClean()) {
			room.setRoomStatus(RoomStatus.FREE);
		}

		houseKeeper.getRoomsToClean().clear();
	}

	public void addRoomToReservation(int id) {
		if (!this.reservationsMan.getReservations().containsKey(id)) {
			System.out.println("Rezervacija sa id-jem " + id + " ne postoji.");
			return;
		}

		Reservation reservation = this.reservationsMan.getReservations().get(id);
		Map<Integer, Room> rooms = this.roomsMan.getRooms();
		for (Room room : rooms.values()) {
			if (!this.roomsMan.isOccupied(room.getId(), reservation.getStartDate(), reservation.getEndDate())) {
				if (room.getRoomType().equals(reservation.getRoomType())) {
					room.setRoomStatus(RoomStatus.OCCUPIED);
					reservation.setRoom(room);
					reservation.setStatus(ReservationStatus.CONFIRMED);
					room.setOccupiedDates(this);
					return;
				}
			}
		}
		reservation.setStatus(ReservationStatus.DENIED);
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
				if (!price.isDeleted() && !date.isBefore(price.getStartDate()) && !date.isAfter(price.getEndDate())) {
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

		reservation.setTotalPrice(totalPrice);
	}
}