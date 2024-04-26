package manage;

public class ManageHotel {
	private ManageAdditionalServices additionalServicesMan;
	private ManageAdministrators administratorsMan;
	private ManageEmployees employeesMan;
	private ManageGuests guestsMan;
	private ManagePrices pricesMan;
	private ManageReservations reservationsMan;
	private ManageRooms roomsMan;
	private ManageRoomTypes roomTypesMan;
	
	// constructor
	public ManageHotel() {
		this.additionalServicesMan = new ManageAdditionalServices();
		this.administratorsMan = new ManageAdministrators();
		this.employeesMan = new ManageEmployees();
		this.guestsMan = new ManageGuests();
		this.pricesMan = new ManagePrices();
		this.reservationsMan = new ManageReservations();
		this.roomsMan = new ManageRooms();
		this.roomTypesMan = new ManageRoomTypes();
	}
	
	// getters
	public ManageAdditionalServices getAdditionalServicesMan() {
		return additionalServicesMan;
	}

	public ManageAdministrators getAdministratorsMan() {
		return administratorsMan;
	}

	public ManageEmployees getEmployeesMan() {
		return employeesMan;
	}

	public ManageGuests getGuestsMan() {
		return guestsMan;
	}

	public ManagePrices getPricesMan() {
		return pricesMan;
	}

	public ManageReservations getReservationsMan() {
		return reservationsMan;
	}

	public ManageRooms getRoomsMan() {
		return roomsMan;
	}

	public ManageRoomTypes getRoomTypesMan() {
		return roomTypesMan;
	}
}
