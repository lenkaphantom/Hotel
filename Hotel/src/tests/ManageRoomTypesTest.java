package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.RoomType;
import enumeracije.TypeOfRoom;
import manage.ManageHotel;
import manage.ManageRoomTypes;

class ManageRoomTypesTest {
    private static ManageHotel manager = ManageHotel.getInstance();
    private static ManageRoomTypes manageRoomTypes;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        manageRoomTypes = manager.getRoomTypesMan();
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        manageRoomTypes.getRoomTypes().clear();
    }

    @Test
    void testAddRoomType() {
        int initialSize = manageRoomTypes.getRoomTypes().size();
        manageRoomTypes.addRoomType(TypeOfRoom.dvokrevetna, 1);
        assertEquals(initialSize + 1, manageRoomTypes.getRoomTypes().size());
    }

    @Test
    void testRemoveRoomType() {
        manageRoomTypes.addRoomType(TypeOfRoom.cetvorokrevetna, 2);
        RoomType roomType = manageRoomTypes.getRoomTypeFromType("cetvorokrevetna");
        manageRoomTypes.removeRoomType(roomType.getId());
        assertNull(manageRoomTypes.getRoomTypeFromType("cetvorokrevetna"));
    }

    @Test
    void testChangeRoomType() {
        RoomType roomType = manageRoomTypes.getRoomTypeFromType("dvokrevetna");
        manageRoomTypes.changeRoomType(roomType.getId(), TypeOfRoom.trokrevetna, 1);
        RoomType changedRoomType = manageRoomTypes.getRoomTypes().get(roomType.getId());
        assertEquals(TypeOfRoom.trokrevetna, changedRoomType.getType());
        assertEquals("1+2", changedRoomType.getBeds());
    }

    @Test
    void testGetRoomTypeFromType() {
        RoomType roomType = manageRoomTypes.getRoomTypeFromType("trokrevetna");
        assertNotNull(roomType);
        assertEquals(TypeOfRoom.trokrevetna, roomType.getType());
    }

    @Test
    void testGetRoomTypeFromTypeAndBeds() {
        manageRoomTypes.addRoomType(TypeOfRoom.apartman, 0);
        RoomType roomType = manageRoomTypes.getRoomTypeFromTypeAndBeds(TypeOfRoom.apartman, "1+1+1+1");
        assertNotNull(roomType);
        assertEquals("1+1+1+1", roomType.getBeds());
        assertEquals(TypeOfRoom.apartman, roomType.getType());
    }

    @Test
    void testGetDefaultPrices() {
        manageRoomTypes.addRoomType(TypeOfRoom.jednokrevetna, 0);
        Map<RoomType, Double> defaultPrices = manageRoomTypes.getDefaultPrices();
        assertTrue(defaultPrices.size() > 0);
        assertEquals(0.0, defaultPrices.get(manageRoomTypes.getRoomTypes().values().iterator().next()));
    }
}
