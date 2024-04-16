package reservations;

import rooms.RoomType;

enum ReservationStatus {
	WAITING, CONFIRMED, CANCELLED, DENIED
}

public class Reservation {
	private RoomType roomType;
	private AdditionalServices additionalServices;
	private ReservationStatus status;

	// constructors
	public Reservation() {
	}

	public Reservation(RoomType roomType, AdditionalServices additionalServices, ReservationStatus status) {
		this.roomType = new RoomType(roomType);
		this.additionalServices = new AdditionalServices(additionalServices);
		this.status = status;
	}

	// copy constructor
	public Reservation(Reservation reservation) {
		this.roomType = new RoomType(reservation.roomType);
		this.additionalServices = new AdditionalServices(reservation.additionalServices);
		this.status = reservation.status;
	}

	// getters
	public RoomType getRoomType() {
		return roomType;
	}

	public AdditionalServices getAdditionalServices() {
		return additionalServices;
	}

	public ReservationStatus getStatus() {
		return status;
	}

	// setters
	public void setRoomType(RoomType roomType) {
		this.roomType = new RoomType(roomType);
	}

	public void setAdditionalServices(AdditionalServices additionalServices) {
		this.additionalServices = new AdditionalServices(additionalServices);
	}

	public void setStatus(ReservationStatus status) {
		this.status = status;
	}
}
