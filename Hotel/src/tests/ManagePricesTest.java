package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.AdditionalServices;
import entity.RoomType;
import enumeracije.TypeOfRoom;
import manage.ManageHotel;
import manage.ManagePrices;

class ManagePricesTest {
	private static ManageHotel manager = ManageHotel.getInstance();
	private static ManagePrices managePrices;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		managePrices = manager.getPricesMan();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		managePrices.getPrices().clear();
	}

	@Test
	void testAddPrices() {
		int initialSize = managePrices.getPrices().size();

		manager.getRoomTypesMan().addRoomType(TypeOfRoom.jednokrevetna, 0);
		Map<RoomType, Double> pricePerRoom = new HashMap<RoomType, Double>();
		pricePerRoom.put(manager.getRoomTypesMan().getRoomTypes().get(1), 20.0);

		manager.getAdditionalServicesMan().addAdditionalService("Dorucak");
		Map<AdditionalServices, Double> pricePerService = new HashMap<AdditionalServices, Double>();
		pricePerService.put(manager.getAdditionalServicesMan().getAdditionalServices().get(1), 5.0);

		managePrices.addPrices(pricePerRoom, pricePerService, LocalDate.now(), LocalDate.now().plusDays(5));
		assertEquals(initialSize + 1, managePrices.getPrices().size());
	}

	@Test
	void testRemovePrices() {
		int initialSize = managePrices.getPrices().size();

		manager.getRoomTypesMan().addRoomType(TypeOfRoom.jednokrevetna, 0);
		Map<RoomType, Double> pricePerRoom = new HashMap<RoomType, Double>();
		pricePerRoom.put(manager.getRoomTypesMan().getRoomTypes().get(1), 20.0);

		manager.getAdditionalServicesMan().addAdditionalService("Dorucak");
		Map<AdditionalServices, Double> pricePerService = new HashMap<AdditionalServices, Double>();
		pricePerService.put(manager.getAdditionalServicesMan().getAdditionalServices().get(1), 5.0);

		managePrices.addPrices(pricePerRoom, pricePerService, LocalDate.now(), LocalDate.now().plusDays(5));
		managePrices.removePrices(1);
		assertEquals(initialSize, managePrices.getPrices().size());
	}

	@Test
	void testChangePrices() {
		manager.getRoomTypesMan().addRoomType(TypeOfRoom.jednokrevetna, 0);
		Map<RoomType, Double> pricePerRoom = new HashMap<RoomType, Double>();
		pricePerRoom.put(manager.getRoomTypesMan().getRoomTypes().get(1), 20.0);

		manager.getAdditionalServicesMan().addAdditionalService("Dorucak");
		Map<AdditionalServices, Double> pricePerService = new HashMap<AdditionalServices, Double>();
		pricePerService.put(manager.getAdditionalServicesMan().getAdditionalServices().get(1), 5.0);

		managePrices.addPrices(pricePerRoom, pricePerService, LocalDate.now(), LocalDate.now().plusDays(5));

		pricePerRoom.put(manager.getRoomTypesMan().getRoomTypes().get(1), 25.0);
		managePrices.changePrices(2, pricePerRoom, pricePerService, LocalDate.now(), LocalDate.now().plusDays(5));
		assertEquals(25.0,
				managePrices.getPrices().get(2).getPricePerRoom().get(manager.getRoomTypesMan().getRoomTypes().get(1)));
	}
}
