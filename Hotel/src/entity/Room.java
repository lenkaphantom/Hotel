package entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import enumeracije.RoomStatus;
import manage.ManageHotel;

public class Room {
	private static int idCounter = 1;
	private int id;
	private int floor;
	private int roomNumber;
	private RoomStatus roomStatus;
	private RoomType roomType;
	private Map<LocalDate, LocalDate> occupiedDates =  new HashMap<>();

	// constructors
	public Room() {
		this.id = idCounter++;
	}

	public Room(int floor, int roomNumber, RoomStatus roomStatus, int idRoomType, ManageHotel hotel) {
		this.id = idCounter++;
		this.floor = floor;
		this.roomNumber = roomNumber;
		this.roomStatus = roomStatus;
		this.roomType = hotel.getRoomTypesMan().getRoomTypes().get(idRoomType);
		this.setOccupiedDates(hotel);
	}

	// copy constructor
	public Room(Room room) {
		this.id = idCounter++;
		this.floor = room.floor;
		this.roomNumber = room.roomNumber;
		this.roomStatus = room.roomStatus;
		this.roomType = room.roomType;
	}

	// getters
	public int getId() {
		return id;
	}

	public int getFloor() {
		return floor;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public RoomStatus getRoomStatus() {
		return roomStatus;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public Map<LocalDate, LocalDate> getOccupiedDates() {
		return occupiedDates;
	}

	// setters
	public void setFloor(int floor) {
		this.floor = floor;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setRoomStatus(RoomStatus roomStatus) {
		this.roomStatus = roomStatus;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public void setOccupiedDates(ManageHotel hotel) {
		Map<Integer, Reservation> reservations = hotel.getReservationsMan().getReservations();
		for (Reservation reservation : reservations.values()) {
			if (reservation.getRoom() == null)
				continue;
			if (reservation.getRoom().getId() == this.id) {
				this.occupiedDates.put(reservation.getStartDate(), reservation.getEndDate());
			}
		}
	}

	// methods
	@Override
	public String toString() {
		return "Soba " + this.id + ": " + this.floor + ". sprat, " + this.roomNumber + " | " + this.roomStatus + " | "
				+ this.roomType.getType() + "(" + this.roomType.getBeds() + ")";
	}

	public String toStringFile() {
		return this.id + " | " + this.floor + " | " + this.roomNumber + " | " + this.roomStatus + " | "
				+ this.roomType.getId();
	}
}
