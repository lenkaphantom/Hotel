package users;

import java.time.LocalDate;
import java.util.Map;

import enumeracije.Gender;
import enumeracije.Qualifications;
import enumeracije.RoomStatus;
import enumeracije.Type;
import manage.ManageRooms;
import reservations.Reservation;
import rooms.Room;

public class Receptionist extends Employee {
	private Reservation reservation;

	// constructors
	public Receptionist() {
		super();
	}

	public Receptionist(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password, Qualifications qualification, double salary, int yearsOfExperience) {
		super(firstName, lastName, gender, date, phone, address, username, password, qualification, salary,
				yearsOfExperience, Type.Receptionist);
	}

	// copy constructor
	public Receptionist(Receptionist receptionist) {
		super(receptionist);
	}

	// getters
	public Reservation getReservation() {
		return this.reservation;
	}

	// methods
	public void changeReservation() {
		Map<Integer, Room> rooms = ManageRooms.getRooms();
		for (Room room : rooms.values()) {
			if (!room.isOccupied(this.reservation.getStartDate(), this.reservation.getEndDate())) {
				if (room.getRoomType().equals(this.reservation.getRoomType())) {
					room.setRoomStatus(RoomStatus.RESERVED);
					this.reservation.setRoom(room);
					return;
				}
			}
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
