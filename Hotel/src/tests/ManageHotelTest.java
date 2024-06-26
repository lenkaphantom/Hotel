package tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import enumeracije.RoomSpecs;
import manage.ManageHotel;

class ManageHotelTest {
	private ManageHotel manager = ManageHotel.getInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testGetRoomSpecsFromStrings() {
		List<String> specs = new ArrayList<String>();
		specs.add("TV");
		specs.add("WIFI");
		specs.add("MINIBAR");
		
		List<RoomSpecs> roomSpecs = manager.getRoomSpecsFromStrings(specs);
		assertNotNull(roomSpecs);
		assert(roomSpecs.contains(RoomSpecs.TV));
		assert(roomSpecs.contains(RoomSpecs.WIFI));
		assert(roomSpecs.contains(RoomSpecs.MINIBAR));
	}

}
