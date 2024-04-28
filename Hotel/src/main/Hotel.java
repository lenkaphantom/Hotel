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
import enumeracije.TypeOfRoom;
import manage.ManageHotel;
import manage.ManageRooms;

public class Hotel {

	public static void main(String[] args) {
		ManageHotel manager = new ManageHotel();

		Administrator admin = new Administrator("Danijela", "Golubovic", Gender.FEMALE, LocalDate.of(1975, 8, 14),
				"061111111", "Karadjordjeva 1", "golubovicva", "daca123");
		
		Receptionist receptionist = new Receptionist("Rada", "Djurasinovic", Gender.FEMALE, LocalDate.of(1973, 12, 27),
				"062222222", "Kralja Petra 2", "radanatasa", "badovinac123", Qualifications.INTERMEDIATE, 60000, 12);
		Receptionist receptionist2 = new Receptionist("Vida", "Nikic", Gender.FEMALE, LocalDate.of(1977, 9, 18),
				"063333333", "Dimitrija Tucovica 3", "nikicV", "muzProgramer123", Qualifications.INTERMEDIATE, 6000,
				10);
		
		HouseKeeper houseKeeper = new HouseKeeper("Nada", "Kovacevic", Gender.FEMALE, LocalDate.of(1959, 2, 20),
				"066666666", "Pop Lukina 13", "nadaSoc", "bogBog1", Qualifications.INTERMEDIATE, 5000, 20);
		
		Guest guest = new Guest("Milena", "Petrovic", Gender.FEMALE, LocalDate.of(1974, 1, 24), "065555555",
				"Karadjordjeva 75", "milena5rovic@mail.rs", "111222333");
		Guest guest2 = new Guest("Predrag", "Jevtic", Gender.MALE, LocalDate.of(1975, 10, 21), "067777777",
				"Karadjordjeva 99", "predrage@mail.rs", "444555666");
		
		RoomType roomType = new RoomType(TypeOfRoom.jednokrevetna, 0);
		RoomType roomType2 = new RoomType(TypeOfRoom.dvokrevetna, 0);
		RoomType roomType3 = new RoomType(TypeOfRoom.dvokrevetna, 1);
		RoomType roomType4 = new RoomType(TypeOfRoom.trokrevetna, 0);
		RoomType roomType5 = new RoomType(TypeOfRoom.trokrevetna, 1);
		RoomType roomType6 = new RoomType(TypeOfRoom.trokrevetna, 2);
		
		Room room = new Room(1, 101, RoomStatus.FREE, roomType, manager);
		Room room2 = new Room(1, 102, RoomStatus.FREE, roomType2, manager);
		Room room3 = new Room(2, 201, RoomStatus.FREE, roomType3, manager);
		Room room4 = new Room(2, 202, RoomStatus.FREE, roomType4, manager);
		Room room5 = new Room(3, 301, RoomStatus.FREE, roomType5, manager);
		Room room6 = new Room(3, 302, RoomStatus.FREE, roomType6, manager);
		
		AdditionalServices additionalServices = new AdditionalServices("Dorucak");
		AdditionalServices additionalServices2 = new AdditionalServices("Rucak");
		AdditionalServices additionalServices3 = new AdditionalServices("Vecera");
		AdditionalServices additionalServices4 = new AdditionalServices("Parking");
		AdditionalServices additionalServices5 = new AdditionalServices("Bazen");
		AdditionalServices additionalServices6 = new AdditionalServices("Spa centar");
		AdditionalServices additionalServices7 = new AdditionalServices("Masaza");
		
		Map<RoomType, Double> pricePerRoom = new HashMap<>();
		pricePerRoom.put(roomType, 20.0);
		pricePerRoom.put(roomType2, 30.0);
		pricePerRoom.put(roomType3, 25.0);
		pricePerRoom.put(roomType4, 40.0);
		pricePerRoom.put(roomType5, 35.0);
		pricePerRoom.put(roomType6, 30.0);
		
		Map<AdditionalServices, Double> pricePerService = new HashMap<>();
		pricePerService.put(additionalServices, 5.0);
		pricePerService.put(additionalServices2, 15.0);
		pricePerService.put(additionalServices3, 10.0);
		pricePerService.put(additionalServices4, 3.0);
		pricePerService.put(additionalServices5, 7.0);
		pricePerService.put(additionalServices7, 20.0);
		
		Prices prices = new Prices(pricePerRoom, pricePerService, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));

		manager.getAdministratorsMan().addAdministrator(admin);
		manager.getAdministratorsMan().printAdministrators();

		manager.getEmployeesMan().addEmployee(receptionist);
		manager.getEmployeesMan().addEmployee(receptionist2);
		manager.getEmployeesMan().addEmployee(houseKeeper);
		manager.getEmployeesMan().removeEmployee(receptionist2);
		manager.getEmployeesMan().printEmployees();

		manager.getGuestsMan().addGuest(guest);
		manager.getGuestsMan().addGuest(guest2);
		manager.getGuestsMan().printGuests();
		
		manager.getRoomTypesMan().addRoomType(roomType);
		manager.getRoomTypesMan().addRoomType(roomType2);
		manager.getRoomTypesMan().addRoomType(roomType3);
		manager.getRoomTypesMan().addRoomType(roomType4);
		manager.getRoomTypesMan().addRoomType(roomType5);
		manager.getRoomTypesMan().addRoomType(roomType6);
		manager.getRoomTypesMan().printRoomTypes();
		
		System.out.println();
		
		manager.getRoomsMan().addRoom(room);
		manager.getRoomsMan().addRoom(room2);
		manager.getRoomsMan().addRoom(room3);
		manager.getRoomsMan().addRoom(room4);
		manager.getRoomsMan().addRoom(room5);
		manager.getRoomsMan().addRoom(room6);
		manager.getRoomsMan().printRooms();
		
		System.out.println();
		
		manager.getRoomsMan().changeRoom(3, 2, 201, roomType5);
		manager.getRoomsMan().printRooms();
		
		System.out.println();
		
		manager.getAdditionalServicesMan().addAdditionalService(additionalServices);
		manager.getAdditionalServicesMan().addAdditionalService(additionalServices2);
		manager.getAdditionalServicesMan().addAdditionalService(additionalServices3);
		manager.getAdditionalServicesMan().addAdditionalService(additionalServices4);
		manager.getAdditionalServicesMan().addAdditionalService(additionalServices5);
		manager.getAdditionalServicesMan().addAdditionalService(additionalServices6);
		manager.getAdditionalServicesMan().addAdditionalService(additionalServices7);
		manager.getAdditionalServicesMan().printAdditionalServices();
		
		System.out.println();
		
		manager.getAdditionalServicesMan().removeAdditionalService(additionalServices6);
		manager.getAdditionalServicesMan().printAdditionalServices();
		
		System.out.println();
		
		manager.getPricesMan().addPrices(prices);
		manager.getPricesMan().printPrices();
		
		System.out.println();
		
		pricePerService.remove(additionalServices);
		pricePerService.put(additionalServices, 7.0);
		manager.getPricesMan().changePrices(1, pricePerRoom, pricePerService, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));
		manager.getPricesMan().printPrices();
		
		ManageRooms roomsMan = manager.getRoomsMan();
		for (Map.Entry<Integer, Room> entry : roomsMan.getRooms().entrySet()) {
			if (!entry.getValue().isOccupied(LocalDate.of(2024, 8, 1), LocalDate.of(2024, 8, 31)))
			{
				System.out.println(entry.getValue().getRoomType());
			}
		}
	
		List<AdditionalServices> guestServices = new ArrayList<>();
		guestServices.add(additionalServices);
		guestServices.add(additionalServices3);
		Reservation reservation = guest.makeReservation(roomType5, LocalDate.of(2024, 8, 13), LocalDate.of(2024, 8, 23), guestServices);
		
		for (Map.Entry<Integer, Room> entry : roomsMan.getRooms().entrySet()) {
			if (!entry.getValue().isOccupied(LocalDate.of(2024, 6, 1), LocalDate.of(2024, 6, 30)))
			{
				System.out.println(entry.getValue().getRoomType());
			}
		}
		
		Reservation reservation2 = guest2.makeReservation(roomType2, LocalDate.of(2024, 6, 6), LocalDate.of(2024, 6, 12), new ArrayList<>());
		
		manager.getReservationsMan().addReservation(reservation);
		manager.getReservationsMan().addReservation(reservation2);
		manager.getReservationsMan().printReservations();
		
		receptionist.setReservation(reservation);
		receptionist.addRoomToReservation(manager);
		manager.getReservationsMan().printReservations();
	}
}
