package main;

import java.time.LocalDate;

import enumeracije.Gender;
import enumeracije.Qualifications;

import users.Administrator;
import users.Receptionist;

public class Hotel {

	public static void main(String[] args) {
		Administrator admin = new Administrator("Danijela", "Golubovic", Gender.FEMALE, LocalDate.of(1975, 8, 14),
				"061111111", "Karadjordjeva 1", "golubovicva", "daca123");
		Receptionist receptionist = new Receptionist("Rada", "Djurasinovic", Gender.FEMALE, LocalDate.of(1973, 12, 27),
				"062222222", "Kralja Petra 2", "radanatasa", "badovinac123", Qualifications.INTERMEDIATE, 60000, 5);
		
		admin.addEmployee(receptionist);
		admin.printEmployees();
	}

}
