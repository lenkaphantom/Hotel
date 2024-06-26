package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import enumeracije.Gender;
import manage.ManageAdministrators;
import manage.ManageHotel;

class ManageAdministratorsTest {
	private static ManageHotel manager = ManageHotel.getInstance();
	private static ManageAdministrators manageAdministrators;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		manageAdministrators = manager.getAdministratorsMan();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		manageAdministrators.getAdministrators().clear();
	}

	@Test
	void testAddAdministrator() {
		int initialSize = manageAdministrators.getAdministrators().size();
		manageAdministrators.addAdministrator("Pera", "Peric", Gender.MALE, LocalDate.of(1997, 9, 27), "123456789",
				"Adresa 1", "pera", "pera");
		assertEquals(initialSize + 1, manageAdministrators.getAdministrators().size());
	}
	
	@Test
	void testRemoveAdministrator() {
		int initialSize = manageAdministrators.getAdministrators().size();
		manageAdministrators.addAdministrator("Pera", "Peric", Gender.MALE, LocalDate.of(1997, 9, 27), "123456789",
				"Adresa 1", "pera", "pera");
		manageAdministrators.removeAdministrator(1);
		assertEquals(initialSize, manageAdministrators.getAdministrators().size());
	}
	
	@Test
	void testChangeAdministrator() {
		manageAdministrators.addAdministrator("Pera", "Peric", Gender.MALE, LocalDate.of(1997, 9, 27), "123456789",
				"Adresa 1", "pera", "pera");
		manager.getAdministratorsMan().changeAdministrator(1, "Mika", "Mikic", "987654321", "Adresa 2", "mika", "mika");
		assertEquals("Mika", manageAdministrators.getAdministrators().get(1).getFirstName());
		assertEquals("Mikic", manageAdministrators.getAdministrators().get(1).getLastName());
	}
}
