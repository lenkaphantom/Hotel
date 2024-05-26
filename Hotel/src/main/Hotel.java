package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.AdditionalServices;
import entity.Reservation;
import entity.Room;
import entity.RoomType;
import enumeracije.Gender;
import enumeracije.Qualifications;
import enumeracije.RoomStatus;
import enumeracije.Type;
import enumeracije.TypeOfRoom;
import manage.ManageHotel;
import manage.ManageRooms;

public class Hotel {

	public static void main(String[] args) {
		ManageHotel manager = ManageHotel.getInstance();
		
//		manager.getAdditionalServicesMan().writeAdditionalServices("data/additional_services.csv");
//		manager.getAdministratorsMan().writeAdministrators("data/administrators.csv");
//		manager.getEmployeesMan().writeEmployees("data/employees.csv");
//		manager.getGuestsMan().writeGuests("data/guests.csv");
//		manager.getRoomsMan().writeRooms("data/rooms.csv");
//		manager.getRoomTypesMan().writeRoomTypes("data/room_types.csv");
//		manager.getPricesMan().writePrices("data/prices.csv");
//		manager.getReservationsMan().writeReservations("data/reservations.csv");
		
		manager.getAdditionalServicesMan().loadAdditionalServices("data/additional_services.csv");
		manager.getAdministratorsMan().loadAdministrators("data/administrators.csv");
		manager.getEmployeesMan().loadEmployees("data/employees.csv");
		manager.getGuestsMan().loadGuests("data/guests.csv");
		manager.getRoomTypesMan().loadRoomTypes("data/room_types.csv");
		manager.getRoomsMan().loadRooms("data/rooms.csv", manager);
		manager.getPricesMan().loadPrices("data/prices.csv", manager);
		manager.getReservationsMan().loadReservations("data/reservations.csv", manager);
		
		manager.getAdditionalServicesMan().printAdditionalServices();
		manager.getAdministratorsMan().printAdministrators();
		manager.getEmployeesMan().printEmployees();
		manager.getGuestsMan().printGuests();
		manager.getRoomTypesMan().printRoomTypes();
		manager.getRoomsMan().printRooms();
		manager.getPricesMan().printPrices();
		manager.getReservationsMan().printReservations();
	}
}
