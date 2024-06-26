package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.AdditionalServices;
import manage.ManageAdditionalServices;
import manage.ManageHotel;

class ManageAdditionalServicesTest {
	private static ManageHotel manager = ManageHotel.getInstance();
	private static ManageAdditionalServices manageAdditionalServices;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		manageAdditionalServices = manager.getAdditionalServicesMan();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		manageAdditionalServices.getAdditionalServices().clear();
	}

	@Test
	void testAddAdditionalService() {
		int initialSize = manageAdditionalServices.getAdditionalServices().size();
		manageAdditionalServices.addAdditionalService("Parking");
		assertEquals(initialSize + 1, manageAdditionalServices.getAdditionalServices().size());
	}

	@Test
	void testRemoveAdditionalService() {
		int initialSize = manageAdditionalServices.getAdditionalServices().size();
		manageAdditionalServices.addAdditionalService("Dorucak");
		manageAdditionalServices.removeAdditionalService(1);
		assertEquals(initialSize, manageAdditionalServices.getAdditionalServices().size());
	}

	@Test
	void testChangeAdditionalService() {
		manageAdditionalServices.addAdditionalService("Parking");
		manageAdditionalServices.changeAdditionalService(1, "Dorucak");
		assertEquals("Dorucak", manageAdditionalServices.getAdditionalServices().get(1).getService());
	}

	@Test
	void testGetAdditionalServicesFromNames() {
		manageAdditionalServices.addAdditionalService("Parking");
		manageAdditionalServices.addAdditionalService("Dorucak");
		List<String> services = new ArrayList<>();
		services.add("Parking");
		services.add("Dorucak");
		List<AdditionalServices> additionalServices = manageAdditionalServices
				.getAdditionalServicesFromNames(services);
		assertEquals(2, additionalServices.size());
	}
}
