package reservations;

import java.util.ArrayList;
import java.util.List;

import rooms.RoomType;

enum ReservationStatus {
	WAITING, CONFIRMED, CANCELLED, DENIED
}

public class Reservation {
	private RoomType roomType;
	private List<AdditionalServices> additionalServices;
	private ReservationStatus status;

	// constructors
	public Reservation() {
	}

	public Reservation(RoomType roomType, List<AdditionalServices> additionalServices, ReservationStatus status) {
		this.roomType = new RoomType(roomType);
		this.additionalServices = new ArrayList<AdditionalServices>(additionalServices);
		this.status = status;
	}

	// copy constructor
	public Reservation(Reservation reservation) {
		this.roomType = new RoomType(reservation.roomType);
		this.additionalServices = new ArrayList<AdditionalServices>(reservation.additionalServices);
		this.status = reservation.status;
	}

	// getters
	public RoomType getRoomType() {
		return roomType;
	}

	public List<AdditionalServices> getAdditionalServices() {
		return additionalServices;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	// setters
	public void setRoomType(RoomType roomType) {
		this.roomType = new RoomType(roomType);
	}

	public void setAdditionalServices(List<AdditionalServices> additionalServices) {
		this.additionalServices = new ArrayList<AdditionalServices>(additionalServices);
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}
}
