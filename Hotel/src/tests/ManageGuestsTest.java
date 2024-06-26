package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.Guest;
import enumeracije.Gender;
import manage.ManageGuests;
import manage.ManageHotel;

class ManageGuestsTest {
	private static ManageHotel manager = ManageHotel.getInstance();
	private static ManageGuests manageGuests;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		manageGuests = manager.getGuestsMan();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		manageGuests.getGuests().clear();
	}

	@Test
	void testAddGuest() {
		int initialSize = manageGuests.getGuests().size();
		manageGuests.addGuest("Jovan", "Jovanovic", Gender.OTHER, LocalDate.now(), "0601234567", "Adresa 1", "ljovan",
				"jovan");
		assertEquals(initialSize + 1, manageGuests.getGuests().size());
	}
	
	@Test
	void testRemoveGuest() {
		int initialSize = manageGuests.getGuests().size();
		manageGuests.addGuest("Jovan", "Jovanovic", Gender.OTHER, LocalDate.now(), "0601234567", "Adresa 1", "jovan",
				"jovan");
		manageGuests.removeGuest(1);
		assertEquals(initialSize, manageGuests.getGuests().size());
	}
	
	@Test
	void testEditGuest() {
		manageGuests.addGuest("Jovan", "Jovanovic", Gender.OTHER, LocalDate.now(), "0601234567", "Adresa 1", "jovan",
				"jovan");
		manageGuests.changeGuest(1, "Ljubomir", "Jovanovic", Gender.OTHER, "0601234567", "Adresa 1");
		assertEquals("Ljubomir", manageGuests.getGuests().get(1).getFirstName());
	}
	
	@Test
	void testGetGuestFromUsername() {
		Guest guest = manageGuests.getGuestFromUsername("ljovan");
		assertNotNull(guest);
	}

}
