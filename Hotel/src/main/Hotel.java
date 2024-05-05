package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.AdditionalServices;
import entity.Administrator;
import entity.Guest;
import entity.HouseKeeper;
import entity.Prices;
import entity.Receptionist;
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
		ManageHotel manager = new ManageHotel();

		manager.getAdministratorsMan().addAdministrator("Danijela", "Golubovic", Gender.FEMALE,
				LocalDate.of(1975, 8, 14), "061111111", "Karadjordjeva 1", "golubovicva", "daca123");
		manager.getAdministratorsMan().printAdministrators();

		manager.getEmployeesMan().addEmployee("Rada", "Djurasinovic", Gender.FEMALE, LocalDate.of(1973, 12, 27),
				"062222222", "Kralja Petra 2", "radanatasa", "badovinac123", Qualifications.INTERMEDIATE, 60000, 12,
				Type.Receptionist);
		manager.getEmployeesMan().addEmployee("Vida", "Nikic", Gender.FEMALE, LocalDate.of(1977, 9, 18), "063333333",
				"Dimitrija Tucovica 3", "nikicV", "muzProgramer123", Qualifications.INTERMEDIATE, 6000, 10,
				Type.Receptionist);
		manager.getEmployeesMan().addEmployee("Nada", "Kovacevic", Gender.FEMALE, LocalDate.of(1959, 2, 20),
				"066666666", "Pop Lukina 13", "nadaSoc", "bogBog1", Qualifications.INTERMEDIATE, 5000, 20,
				Type.HouseKeeper);
		manager.getEmployeesMan().removeEmployee(2);
		manager.getEmployeesMan().printEmployees();

		manager.getGuestsMan().addGuest("Milena", "Petrovic", Gender.FEMALE, LocalDate.of(1974, 1, 24), "065555555",
				"Karadjordjeva 75", "milena5rovic@mail.rs", "111222333");
		manager.getGuestsMan().addGuest("Predrag", "Jevtic", Gender.MALE, LocalDate.of(1975, 10, 21), "067777777",
				"Karadjordjeva 99", "predrage@mail.rs", "444555666");
		manager.getGuestsMan().printGuests();

		manager.getRoomTypesMan().addRoomType(TypeOfRoom.jednokrevetna, 0);
		manager.getRoomTypesMan().addRoomType(TypeOfRoom.dvokrevetna, 0);
		manager.getRoomTypesMan().addRoomType(TypeOfRoom.dvokrevetna, 1);
		manager.getRoomTypesMan().addRoomType(TypeOfRoom.trokrevetna, 0);
		manager.getRoomTypesMan().addRoomType(TypeOfRoom.trokrevetna, 1);
		manager.getRoomTypesMan().addRoomType(TypeOfRoom.trokrevetna, 2);
		manager.getRoomTypesMan().printRoomTypes();

		System.out.println();

		manager.getRoomsMan().addRoom(1, 101, RoomStatus.FREE, 1, manager);
		manager.getRoomsMan().addRoom(1, 102, RoomStatus.FREE, 2, manager);
		manager.getRoomsMan().addRoom(2, 201, RoomStatus.FREE, 3, manager);
		manager.getRoomsMan().addRoom(2, 202, RoomStatus.FREE, 4, manager);
		manager.getRoomsMan().addRoom(3, 301, RoomStatus.FREE, 5, manager);
		manager.getRoomsMan().addRoom(3, 302, RoomStatus.FREE, 6, manager);
		manager.getRoomsMan().printRooms();

		System.out.println();

		manager.getRoomsMan().changeRoom(3, 2, 201, manager.getRoomTypesMan().getRoomTypes().get(5));
		manager.getRoomsMan().printRooms();

		System.out.println();

		manager.getAdditionalServicesMan().addAdditionalService("Dorucak");
		manager.getAdditionalServicesMan().addAdditionalService("Rucak");
		manager.getAdditionalServicesMan().addAdditionalService("Vecera");
		manager.getAdditionalServicesMan().addAdditionalService("Parking");
		manager.getAdditionalServicesMan().addAdditionalService("Bazen");
		manager.getAdditionalServicesMan().addAdditionalService("Spa centar");
		manager.getAdditionalServicesMan().addAdditionalService("Masaza");
		manager.getAdditionalServicesMan().printAdditionalServices();

		System.out.println();

		manager.getAdditionalServicesMan().removeAdditionalService(6);
		manager.getAdditionalServicesMan().printAdditionalServices();

		System.out.println();
		
		Map<RoomType, Double> pricePerRoom = new HashMap<>();
		pricePerRoom.put(manager.getRoomTypesMan().getRoomTypes().get(1), 20.0);
		pricePerRoom.put(manager.getRoomTypesMan().getRoomTypes().get(2), 30.0);
		pricePerRoom.put(manager.getRoomTypesMan().getRoomTypes().get(3), 25.0);
		pricePerRoom.put(manager.getRoomTypesMan().getRoomTypes().get(4), 40.0);
		pricePerRoom.put(manager.getRoomTypesMan().getRoomTypes().get(5), 35.0);
		pricePerRoom.put(manager.getRoomTypesMan().getRoomTypes().get(6), 30.0);

		Map<AdditionalServices, Double> pricePerService = new HashMap<>();
		pricePerService.put(manager.getAdditionalServicesMan().getAdditionalServices().get(1), 5.0);
		pricePerService.put(manager.getAdditionalServicesMan().getAdditionalServices().get(2), 15.0);
		pricePerService.put(manager.getAdditionalServicesMan().getAdditionalServices().get(3), 10.0);
		pricePerService.put(manager.getAdditionalServicesMan().getAdditionalServices().get(4), 3.0);
		pricePerService.put(manager.getAdditionalServicesMan().getAdditionalServices().get(5), 7.0);

		manager.getPricesMan().addPrices(pricePerRoom, pricePerService, LocalDate.of(2024, 1, 1),
				LocalDate.of(2024, 12, 31));
		manager.getPricesMan().printPrices();

		System.out.println();

		pricePerService.put(manager.getAdditionalServicesMan().getAdditionalServices().get(1), 7.0);
		manager.getPricesMan().changePrices(1, pricePerRoom, pricePerService, LocalDate.of(2024, 1, 1),
				LocalDate.of(2024, 12, 31));
		manager.getPricesMan().printPrices();

		ManageRooms roomsMan = manager.getRoomsMan();
		List<Integer> roomTypesId = new ArrayList<>();
		for (Map.Entry<Integer, Room> entry : roomsMan.getRooms().entrySet()) {
			if (!entry.getValue().isOccupied(LocalDate.of(2024, 8, 1), LocalDate.of(2024, 8, 31))) {
				if (!roomTypesId.contains(entry.getValue().getRoomType().getId()))
					roomTypesId.add(entry.getValue().getRoomType().getId());
			}
		}

		for (Integer id : roomTypesId) {
			System.out.println(manager.getRoomTypesMan().getRoomTypes().get(id));
		}

		roomTypesId.clear();
		System.out.println();

		List<AdditionalServices> guestServices = new ArrayList<>();
		guestServices.add(manager.getAdditionalServicesMan().getAdditionalServices().get(1));
		guestServices.add(manager.getAdditionalServicesMan().getAdditionalServices().get(3));
		Reservation reservation = manager.getGuestsMan().getGuests().get(1).makeReservation(
				manager.getRoomTypesMan().getRoomTypes().get(5), LocalDate.of(2024, 8, 13), LocalDate.of(2024, 8, 23),
				guestServices);

		for (Map.Entry<Integer, Room> entry : roomsMan.getRooms().entrySet()) {
			if (!entry.getValue().isOccupied(LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 30))) {
				if (!roomTypesId.contains(entry.getValue().getRoomType().getId()))
					roomTypesId.add(entry.getValue().getRoomType().getId());
			}
		}

		for (Integer id : roomTypesId) {
			System.out.println(manager.getRoomTypesMan().getRoomTypes().get(id));
		}

		roomTypesId.clear();
		System.out.println();

		Reservation reservation2 = manager.getGuestsMan().getGuests().get(2).makeReservation(
				manager.getRoomTypesMan().getRoomTypes().get(2), LocalDate.of(2024, 6, 6), LocalDate.of(2024, 6, 12),
				new ArrayList<>());

		manager.getReservationsMan().addReservation(reservation);
		manager.getReservationsMan().addReservation(reservation2);

		manager.getEmployeesMan().getReceptionists().get(1).setReservation(reservation);
		manager.getEmployeesMan().getReceptionists().get(1).addRoomToReservation(manager);

		for (Map.Entry<Integer, Reservation> entry : manager.getReservationsMan().getReservations().entrySet()) {
			if (entry.getValue().getGuest().getUsername().equals("milena5rovic@mail.rs")) {
				System.out.println(entry.getValue());
			}
		}
		
		manager.getAdditionalServicesMan().writeAdditionalServices("data/additional_services.csv");
		manager.getAdministratorsMan().writeAdministrators("data/administrators.csv");
		manager.getEmployeesMan().writeEmployees("data/employees.csv");
	}
}
