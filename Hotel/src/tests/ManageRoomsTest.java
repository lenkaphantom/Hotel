package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import enumeracije.RoomStatus;
import enumeracije.TypeOfRoom;
import manage.ManageHotel;
import manage.ManageRooms;

class ManageRoomsTest {
	private static ManageHotel manager = ManageHotel.getInstance();
	private static ManageRooms manageRooms;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		manageRooms = manager.getRoomsMan();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		manageRooms.getRooms().clear();
	}

	@Test
	void testAddRoom() {
		manager.getRoomTypesMan().addRoomType(TypeOfRoom.jednokrevetna, 0);
		int initialSize = manageRooms.getRooms().size();
		manageRooms.addRoom(1, 101, RoomStatus.FREE, 1, manager, null);
		assertEquals(initialSize + 1, manageRooms.getRooms().size());
	}

	@Test
	void testRemoveRoom() {
		int initialSize = manageRooms.getRooms().size();
		manageRooms.addRoom(1, 105, RoomStatus.FREE, 1, manager, null);
		manageRooms.removeRoom(2);
		assertEquals(initialSize, manageRooms.getRooms().size());
	}

	@Test
	void testChangeRoom() {
		manageRooms.changeRoom(1, 1, 103, manageRooms.getRooms().get(1).getRoomType(), null);
		assertEquals(103, manageRooms.getRooms().get(1).getRoomNumber());
	}

}
