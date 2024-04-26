package main;

import java.time.LocalDate;

import entity.Administrator;
import entity.Guest;
import entity.HouseKeeper;
import entity.Receptionist;
import enumeracije.Gender;
import enumeracije.Qualifications;
import manage.ManageHotel;

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

		manager.getAdministratorsMan().addAdministrator(admin);
		manager.getAdministratorsMan().printAdministrators();

		System.out.println();

		manager.getEmployeesMan().addEmployee(receptionist);
		manager.getEmployeesMan().addEmployee(receptionist2);
		manager.getEmployeesMan().addEmployee(houseKeeper);
		manager.getEmployeesMan().removeEmployee(receptionist2);
		manager.getEmployeesMan().printEmployees();

		System.out.println();

		manager.getGuestsMan().addGuest(guest);
		manager.getGuestsMan().addGuest(guest2);
		manager.getGuestsMan().printGuests();
	}
}
