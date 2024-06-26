package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import entity.Reservation;
import enumeracije.ReservationStatus;
import enumeracije.TypeOfRoom;
import manage.ManageHotel;
import manage.ManageReservations;

class ManageReservationsTest {
	private static ManageHotel manager = ManageHotel.getInstance();
	private static ManageReservations manageReservations;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		manageReservations = manager.getReservationsMan();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		manageReservations.getReservations().clear();
	}

	@Test
	void testAddReservation() {
		manager.getRoomTypesMan().addRoomType(TypeOfRoom.jednokrevetna, 0);
		Reservation reservation = new Reservation(manager.getRoomTypesMan().getRoomTypes().get(1), LocalDate.now(),
				LocalDate.now().plusDays(3), null, ReservationStatus.WAITING, LocalDate.now());
		int initialSize = manageReservations.getReservations().size();
		manageReservations.addReservation(reservation);
		assertEquals(initialSize + 1, manageReservations.getReservations().size());
	}

	@Test
	void testRemoveReservation() {
		manager.getRoomTypesMan().addRoomType(TypeOfRoom.jednokrevetna, 0);
		Reservation reservation = new Reservation(manager.getRoomTypesMan().getRoomTypes().get(1), LocalDate.now(),
				LocalDate.now().plusDays(3), null, ReservationStatus.WAITING, LocalDate.now());
		int initialSize = manageReservations.getReservations().size();
		manageReservations.addReservation(reservation);
		manageReservations.removeReservation(1);
		assertEquals(initialSize, manageReservations.getReservations().size());
	}

	@Test
	void testChangeReservation() {
		manager.getRoomTypesMan().addRoomType(TypeOfRoom.jednokrevetna, 0);
		Reservation reservation = new Reservation(manager.getRoomTypesMan().getRoomTypes().get(1), LocalDate.now(),
				LocalDate.now().plusDays(3), null, ReservationStatus.WAITING, LocalDate.now());
		manageReservations.addReservation(reservation);
		manageReservations.changeReservation(2, manager.getRoomTypesMan().getRoomTypes().get(1), null,
				ReservationStatus.CANCELLED, null);
		assertEquals(ReservationStatus.CANCELLED, manageReservations.getReservations().get(2).getStatus());
	}
}
