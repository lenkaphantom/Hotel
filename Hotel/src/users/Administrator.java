package users;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import enumeracije.Gender;
import enumeracije.ReservationStatus;

import manage.ManageAdditionalServices;
import manage.ManageEmployees;
import manage.ManageGuests;
import manage.ManagePrices;
import manage.ManageReservations;
import manage.ManageRoomTypes;
import manage.ManageRooms;

import reservations.AdditionalServices;
import reservations.Prices;
import reservations.Reservation;

import rooms.Room;
import rooms.RoomType;

public class Administrator extends User {
	private static int idCounter = 1;
	private int id;

	// constructors
	public Administrator() {
		super();
		this.id = idCounter++;
	}

	public Administrator(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password) {
		super(firstName, lastName, gender, date, phone, address, username, password);
		this.id = idCounter++;
	}

	// copy constructor
	public Administrator(User user) {
		super(user);
		this.id = idCounter++;
	}

	// getters
	public int getId() {
		return this.id;
	}

	// methods for managing guests
	public static void addGuest(Guest guest) {
		ManageGuests.addGuest(guest);
	}

	public static void removeGuest(Guest guest) {
		ManageGuests.removeGuest(guest);
	}

	public static void changeGuest(int id, String firstName, String lastName, String phone, String address) {
		ManageGuests.changeGuest(id, firstName, lastName, phone, address);
	}

	public static void printGuests() {
		ManageGuests.printGuests();
	}

	// methods for managing employees
	public static void addEmployee(Employee employee) {
		ManageEmployees.addEmployee(employee);
	}

	public static void removeEmployee(Employee employee) {
		ManageEmployees.removeEmployee(employee);
	}

	public static void changeEmployee(int id, String firstName, String lastName, String phone, String address,
			String username, String password) {
		ManageEmployees.changeEmployee(id, firstName, lastName, phone, address, username, password);
	}

	public static void printEmployees() {
		ManageEmployees.printEmployees();
	}

	// methods for managing room types
	public static void addRoomType(RoomType roomType) {
		ManageRoomTypes.addRoomType(roomType);
	}

	public static void removeRoomType(RoomType roomType) {
		ManageRoomTypes.removeRoomType(roomType);
	}

	public static void changeRoomType(int id, String type) {
		ManageRoomTypes.changeRoomType(id, type);
	}

	public static void printRoomTypes() {
		ManageRoomTypes.printRoomTypes();
	}

	// methods for managing rooms
	public static void addRoom(Room room) {
		ManageRooms.addRoom(room);
	}

	public static void removeRoom(Room room) {
		ManageRooms.removeRoom(room);
	}

	public static void changeRoom(int id, int floor, int roomNumber) {
		ManageRooms.changeRoom(id, floor, roomNumber);
	}

	public static void printRooms() {
		ManageRooms.printRooms();
	}

	// methods for managing reservations
	public static void addReservation(Reservation reservation) {
		ManageReservations.addReservation(reservation);
	}

	public static void removeReservation(Reservation reservation) {
		ManageReservations.removeReservation(reservation);
	}

	public static void changeReservation(int id, RoomType roomType, List<AdditionalServices> additionalServices,
			ReservationStatus status, Room room) {
		ManageReservations.changeReservation(id, roomType, additionalServices, status, room);
	}

	public static void printReservations() {
		ManageReservations.printReservations();
	}

	// methods for managing additional services
	public static void addAdditionalService(AdditionalServices service) {
		ManageAdditionalServices.addAdditionalService(service);
	}

	public static void removeAdditionalService(AdditionalServices service) {
		ManageAdditionalServices.removeAdditionalService(service);
	}

	public static void changeAdditionalService(int id, String name) {
		ManageAdditionalServices.changeAdditionalService(id, name);
	}

	public static void printAdditionalServices() {
		ManageAdditionalServices.printAdditionalServices();
	}

	// methods for managing prices
	public static void addPrices(Prices price) {
		ManagePrices.addPrices(price);
	}

	public static void removePrices(Prices price) {
		ManagePrices.removePrices(price);
	}

	public static void changePrices(int id, Map<RoomType, Double> pricePerRoom,
			Map<AdditionalServices, Double> pricePerService, LocalDate startDate, LocalDate endDate) {
		ManagePrices.changePrices(id, pricePerRoom, pricePerService, startDate, endDate);
	}

	public static void printPrices() {
		ManagePrices.printPrices();
	}
}
