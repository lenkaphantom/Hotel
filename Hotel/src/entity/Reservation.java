package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enumeracije.ReservationStatus;
import manage.ManageHotel;

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
	private double totalPrice;
	private LocalDate creationDate;

	// constructors
	public Reservation() {
		this.id = idCounter++;
	}

	// constructor
	public Reservation(RoomType roomType, LocalDate startDate, LocalDate endDate,
			List<AdditionalServices> additionalServices, ReservationStatus status, LocalDate creationDate) {
		this.id = idCounter++;
		this.roomType = roomType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.additionalServices = additionalServices;
		this.status = status;
		this.room = null;
		this.guest = null;
		this.totalPrice = 0;
		this.creationDate = creationDate;
	}

	// copy constructor
	public Reservation(Reservation reservation) {
		this.id = idCounter++;
		this.roomType = reservation.roomType;
		this.additionalServices = new ArrayList<AdditionalServices>(reservation.additionalServices);
		this.status = reservation.status;
		this.room = reservation.room;
		this.guest = reservation.guest;
		this.totalPrice = reservation.totalPrice;
		this.startDate = reservation.startDate;
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

	public double getTotalPrice() {
		return totalPrice;
	}
	
	public LocalDate getCreationDate() {
		return creationDate;
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

	public void setGuest(int guestId, ManageHotel manager) {
		this.guest = manager.getGuestsMan().getGuests().get(guestId);
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
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
		return result + "Price: " + this.totalPrice + "\n-----------------------------\n";
	}

	public String toStringFile() {
		String services = "";
		if (this.additionalServices != null) {
			for (AdditionalServices additionalService : this.additionalServices) {
				services += additionalService.getId() + "*";
			}
		}
		String result = this.id + " | " + this.startDate + " | " + this.endDate + " | " + this.status + " | "
				+ this.roomType.getId() + " | " + services;
		if (services != "")
			result = result.substring(0, result.length() - 1);
		if (this.room != null)
			result += " | " + this.room.getId();
		else
			result += " | ";
		if (this.guest != null)
			result += " | " + this.guest.getId();
		else
			result += " | ";
		return result + " | " + this.totalPrice + " | " + this.creationDate;
	}
}
