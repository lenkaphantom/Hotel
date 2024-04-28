package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enumeracije.ReservationStatus;

public class Reservation {
	private static int idCounter = 1;
	private int id;
	private RoomType roomType;
	private LocalDate startDate;
	private LocalDate endDate;
	private List<AdditionalServices> additionalServices;
	private ReservationStatus status;
	private Room room;

	// constructors
	public Reservation() {
		this.id = idCounter++;
	}

	// constructor
	public Reservation(RoomType roomType, LocalDate startDate, LocalDate endDate,
			List<AdditionalServices> additionalServices, ReservationStatus status) {
		this.id = idCounter++;
		this.roomType = roomType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.additionalServices = new ArrayList<AdditionalServices>(additionalServices);
		this.status = status;
		this.room = null;
	}

	// copy constructor
	public Reservation(Reservation reservation) {
		this.id = idCounter++;
		this.roomType = reservation.roomType;
		this.additionalServices = new ArrayList<AdditionalServices>(reservation.additionalServices);
		this.status = reservation.status;
		this.room = reservation.room;
	}

	// getters
	public int getId() {
		return id;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public List<AdditionalServices> getAdditionalServices() {
		return additionalServices;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	public Room getRoom() {
		return room;
	}

	// setters
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setAdditionalServices(List<AdditionalServices> additionalServices) {
		this.additionalServices = new ArrayList<AdditionalServices>(additionalServices);
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	// methods
	@Override
	public String toString() {
		String services = "";
		for (AdditionalServices additionalService : this.additionalServices) {
			services += additionalService.getId() + "*";
		}
		if (services == "" && this.room == null)
			return this.id + " | " + this.roomType.getId() + " | " + this.startDate + " | " + this.endDate + " | "
					+ this.status;
		else if (services == "" && this.room != null)
			return this.id + " | " + this.roomType.getId() + " | " + this.startDate + " | " + this.endDate + " | "
					+ this.status + " | " + this.room.getId();
		else if (services != "" && this.room == null)
			return this.id + " | " + this.roomType.getId() + " | " + this.startDate + " | " + this.endDate + " | "
					+ services.substring(0, services.length() - 1) + " | " + this.status;
		else
			return this.id + " | " + this.roomType.getId() + " | " + this.startDate + " | " + this.endDate + " | "
					+ services.substring(0, services.length() - 1) + " | " + this.status + " | " + this.room.getId();
	}
}
