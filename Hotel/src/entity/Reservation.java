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
	private Guest guest;

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
		this.guest = null;
	}

	// copy constructor
	public Reservation(Reservation reservation) {
		this.id = idCounter++;
		this.roomType = reservation.roomType;
		this.additionalServices = new ArrayList<AdditionalServices>(reservation.additionalServices);
		this.status = reservation.status;
		this.room = reservation.room;
		this.guest = reservation.guest;
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

	public Guest getGuest() {
		return guest;
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

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	// methods
	@Override
	public String toString() {
		String services = "";
		for (AdditionalServices additionalService : this.additionalServices) {
			services += additionalService.getService() + ", ";
		}
		String result = "-------Reservation " + this.id + "-------\nStart date: " + this.startDate + "\nEnd date: "
				+ this.endDate + "\nStatus: " + this.status + "\n" + this.roomType.toString() + "\n";
		if (services != "")
			result += "Additional services: " + services.substring(0, services.length() - 2) + "\n";
		if (this.room != null)
			result += "Room: " + this.room.getRoomNumber() + "\n";
		if (this.guest != null)
			result += "Guest: " + this.guest.getUsername() + "\n";
		return result;
	}

	public String toStringFile() {
		String services = "";
		for (AdditionalServices additionalService : this.additionalServices) {
			services += additionalService.getId() + "*";
		}
		String result = this.id + " | " + this.startDate + " | " + this.endDate + " | " + this.status + " | "
				+ this.roomType.getId() + " | " + services;
		if (services != "")
			result = result.substring(0, result.length() - 1);
		if (this.room != null)
			result += " | " + this.room.getId();
		if (this.guest != null)
			result += " | " + this.guest.getId();
		return result;
	}
}
