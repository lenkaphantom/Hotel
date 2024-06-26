package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.HouseKeeper;
import enumeracije.Gender;
import enumeracije.Qualifications;
import enumeracije.Type;
import manage.ManageEmployees;
import manage.ManageHotel;

class ManageEmployeesTest {
	private static ManageHotel manager = ManageHotel.getInstance();
	private static ManageEmployees manageEmployees;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		manageEmployees = manager.getEmployeesMan();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		manageEmployees.getEmployees().clear();
	}

	@Test
	void testAddEmployee() {
		int initialSize = manageEmployees.getEmployees().size();
		manageEmployees.addEmployee("Jovan", "Jovanovic", Gender.MALE, LocalDate.now(), "0601234567", "Adresa 1",
				"ljovan", "jovan", Qualifications.BASIC, 5, Type.HouseKeeper);
		assertEquals(initialSize + 1, manageEmployees.getEmployees().size());
	}

	@Test
	void testRemoveEmployee() {
		int initialSize = manageEmployees.getEmployees().size();
		manageEmployees.addEmployee("Jovan", "Jovanovic", Gender.MALE, LocalDate.now(), "0601234567", "Adresa 1",
				"jovan", "jovan", Qualifications.BASIC, 5, Type.HouseKeeper);
		manageEmployees.removeEmployee(1);
		assertEquals(initialSize, manageEmployees.getEmployees().size());
	}

	@Test
	void testChangeEmployee() {
		manageEmployees.addEmployee("Jovan", "Jovanovic", Gender.MALE, LocalDate.now(), "0601234567", "Adresa 1",
				"jovan", "jovan", Qualifications.BASIC, 5, Type.HouseKeeper);
		manageEmployees.changeEmployee(2, "Momir", "Jovanovic", Gender.MALE, "0601234567", "Adresa 1", "jovan",
				"jovan", Qualifications.BASIC, 5);
		assertEquals("Momir", manageEmployees.getEmployees().get(2).getFirstName());
	}

	@Test
	void testGetHouseKeeperFromUsername() {
		manageEmployees.addEmployee("Jovan", "Jovanovic", Gender.MALE, LocalDate.now(), "0601234567", "Adresa 1",
                "hjovan", "jovan", Qualifications.BASIC, 5, Type.HouseKeeper);
		HouseKeeper houseKeeper = manageEmployees.getHouseKeeperFromUsername("hjovan");
		assertEquals("hjovan", houseKeeper.getUsername());
		assertEquals("jovan", houseKeeper.getPassword());
		assertEquals("Jovan", houseKeeper.getFirstName());
		assertEquals("Jovanovic", houseKeeper.getLastName());
		assertEquals(Gender.MALE, houseKeeper.getGender());
		assertEquals(LocalDate.now(), houseKeeper.getDate());
		assertEquals("0601234567", houseKeeper.getPhone());
		assertEquals("Adresa 1", houseKeeper.getAddress());
		assertEquals(Qualifications.BASIC, houseKeeper.getQualification());
		assertEquals(5, houseKeeper.getYearsOfExperience());
		assertEquals(Type.HouseKeeper, houseKeeper.getType());
	}
}
