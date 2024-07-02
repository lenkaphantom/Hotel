package controler;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;
import entity.HouseKeeper;
import entity.Reservation;
import entity.Room;
import enumeracije.ReservationStatus;
import manage.ManageHotel;

public abstract class ReportsControler {
	private static ManageHotel manager = ManageHotel.getInstance();

	public static double getRevenue(LocalDate startDate, LocalDate endDate) {
		double revenue = 0.0;
		for (Reservation reservation : manager.getReservationsMan().getReservations().values()) {
			LocalDate tsDate = startDate.minusDays(1);
			LocalDate teDate = endDate.plusDays(1);
			if (tsDate.isBefore(reservation.getCreationDate()) && teDate.isAfter(reservation.getCreationDate())) {
				revenue += reservation.getTotalPrice();
			}
		}

		return Math.round(revenue * 100.0) / 100.0;
	}

	public static double getExpenses(LocalDate startDate, LocalDate endDate) {
		int expenses = 0;
		Period period = Period.between(startDate, endDate);
		int numOfMonths = period.getMonths();

		for (Employee employee : manager.getEmployeesMan().getEmployees().values()) {
			expenses += employee.getSalary();
		}
		expenses *= (numOfMonths + 1);
		return Math.round(expenses * 100.0) / 100.0;
	}

	public static int getNumOfCleanedRooms(LocalDate startDate, LocalDate endDate, int id) {
		if (manager.getEmployeesMan().getEmployees().get(id) == null
				|| !(manager.getEmployeesMan().getEmployees().get(id) instanceof HouseKeeper)) {
			return -1;
		}

		int numOfCleanedRooms = 0;
		HouseKeeper houseKeeper = manager.getEmployeesMan().getHouseKeepers().get(id);

		for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
			if (houseKeeper.getRoomsToClean().get(date) == null)
				continue;
			numOfCleanedRooms += houseKeeper.getRoomsToClean().get(date).size();
		}

		return numOfCleanedRooms;
	}

	public static String getHouseKeeping(LocalDate startDate, LocalDate endDate) {
		String houseKeeping = "";
		String room = "";
		int numOfCleanedRooms = 0;
		for (HouseKeeper houseKeeper : manager.getEmployeesMan().getHouseKeepers().values()) {
			houseKeeping += "Housekeeper: " + houseKeeper.getUsername();
			numOfCleanedRooms = getNumOfCleanedRooms(startDate, endDate, houseKeeper.getId());
			if (numOfCleanedRooms >= 2 && numOfCleanedRooms <= 4)
				room = "sobe";
			else room = "soba";
			houseKeeping += " | " + numOfCleanedRooms + room + "\n";
		}
		return houseKeeping;
	}

	public static int getNumOfConfirmedReservations(LocalDate startDate, LocalDate endDate) {
		int numOfConfirmedReservations = 0;
		for (Reservation reservation : manager.getReservationsMan().getReservations().values()) {
			if (!reservation.getStatus().equals(ReservationStatus.CONFIRMED))
				continue;
			LocalDate tsDate = startDate.minusDays(1);
			LocalDate teDate = endDate.plusDays(1);
			if (tsDate.isBefore(reservation.getCreationDate()) && teDate.isAfter(reservation.getCreationDate())) {
				numOfConfirmedReservations++;
			}
		}

		return numOfConfirmedReservations;
	}

	public static int getNumOfProcessedReservations(LocalDate startDate, LocalDate endDate) {
		int numOfProcessedReservations = 0;
		for (Reservation reservation : manager.getReservationsMan().getReservations().values()) {
			if (reservation.getStatus().equals(ReservationStatus.WAITING))
				continue;
			LocalDate tsDate = startDate.minusDays(1);
			LocalDate teDate = endDate.plusDays(1);
			if (tsDate.isBefore(reservation.getCreationDate()) && teDate.isAfter(reservation.getCreationDate())) {
				numOfProcessedReservations++;
			}
		}

		return numOfProcessedReservations;
	}

	public static int getNumOfWaitingReservations(LocalDate startDate, LocalDate endDate) {
		int numOfWaitingReservations = 0;
		for (Reservation reservation : manager.getReservationsMan().getReservations().values()) {
			if (!reservation.getStatus().equals(ReservationStatus.WAITING))
				continue;
			LocalDate tsDate = startDate.minusDays(1);
			LocalDate teDate = endDate.plusDays(1);
			if (tsDate.isBefore(reservation.getCreationDate()) && teDate.isAfter(reservation.getCreationDate())) {
				numOfWaitingReservations++;
			}
		}

		return numOfWaitingReservations;
	}

	public static int getNumOfCancelledReservations(LocalDate startDate, LocalDate endDate) {
		int numOfCancelledReservations = 0;
		for (Reservation reservation : manager.getReservationsMan().getReservations().values()) {
			if (!reservation.getStatus().equals(ReservationStatus.CANCELLED))
				continue;
			LocalDate tsDate = startDate.minusDays(1);
			LocalDate teDate = endDate.plusDays(1);
			if (tsDate.isBefore(reservation.getCreationDate()) && teDate.isAfter(reservation.getCreationDate())) {
				numOfCancelledReservations++;
			}
		}

		return numOfCancelledReservations;
	}

	public static int getNumOfDeniedReservations(LocalDate startDate, LocalDate endDate) {
		int numOfDeniedReservations = 0;
		for (Reservation reservation : manager.getReservationsMan().getReservations().values()) {
			if (!reservation.getStatus().equals(ReservationStatus.DENIED))
				continue;
			LocalDate tsDate = startDate.minusDays(1);
			LocalDate teDate = endDate.plusDays(1);
			if (tsDate.isBefore(reservation.getCreationDate()) && teDate.isAfter(reservation.getCreationDate())) {
				numOfDeniedReservations++;
			}
		}

		return numOfDeniedReservations;
	}

	public static int getNumOfExpiredReservations(LocalDate startDate, LocalDate endDate) {
		int numOfExpiredReservations = 0;
		for (Reservation reservation : manager.getReservationsMan().getReservations().values()) {
			if (!reservation.getStatus().equals(ReservationStatus.EXPIRED))
				continue;
			LocalDate tsDate = startDate.minusDays(1);
			LocalDate teDate = endDate.plusDays(1);
			if (tsDate.isBefore(reservation.getCreationDate()) && teDate.isAfter(reservation.getCreationDate())) {
				numOfExpiredReservations++;
			}
		}

		return numOfExpiredReservations;
	}

	public static int getNumOfNights(LocalDate startDate, LocalDate endDate, int id) {
		if (manager.getRoomsMan().getRooms().get(id) == null) {
			return -1;
		}
		int numOfNights = 0;
		List<LocalDate> dates = manager.getRoomsMan().getOccupiedDatesList(id);
		for (LocalDate date : dates) {
			LocalDate tsDate = startDate.minusDays(1);
			LocalDate teDate = endDate.plusDays(1);
			if (tsDate.isBefore(date) && teDate.isAfter(date)) {
				numOfNights++;
			}
		}
		return numOfNights;
	}

	public static double getRevenueForRoom(int id, LocalDate startDate, LocalDate endDate) {
		if (manager.getRoomsMan().getRooms().get(id) == null) {
			return -1;
		}
		double revenue = 0;
		for (Reservation reservation : manager.getReservationsMan().getReservations().values()) {
			if (reservation.getRoom() == null)
				continue;
			if (reservation.getRoom().getId() == id) {
				LocalDate tsDate = startDate.minusDays(1);
				LocalDate teDate = endDate.plusDays(1);
				if (tsDate.isBefore(reservation.getStartDate())) {
					if (teDate.isAfter(reservation.getEndDate())) {
						revenue += reservation.getTotalPrice();
					} else {
						int nights = getNumOfNights(reservation.getStartDate(), endDate, id);
						int totalNights = Period.between(reservation.getStartDate(), reservation.getEndDate())
								.getDays();
						revenue += reservation.getTotalPrice() * nights / totalNights;
					}
				} else if (teDate.isAfter(reservation.getEndDate()) && tsDate.isBefore(reservation.getEndDate())) {
					int nights = getNumOfNights(startDate, reservation.getEndDate(), id);
					int totalNights = Period.between(reservation.getStartDate(), reservation.getEndDate()).getDays();
					revenue += reservation.getTotalPrice() * nights / totalNights;
				} else if (tsDate.isAfter(reservation.getStartDate()) && teDate.isBefore(reservation.getEndDate())) {
					int nights = getNumOfNights(startDate, endDate, id);
					int totalNights = Period.between(reservation.getStartDate(), reservation.getEndDate()).getDays();
					revenue += reservation.getTotalPrice() * nights / totalNights;
				}
			}
		}
		return Math.round(revenue * 100.0) / 100.0;
	}

	public static String getRoomsReport(LocalDate startDate, LocalDate endDate) {
		String roomsReport = "";
		int numOfNights = 0;
		double revenue = 0;
		int id = 0;
		int roomNumber = 0;
		for (Room romm : manager.getRoomsMan().getRooms().values()) {
			id = romm.getId();
			numOfNights = getNumOfNights(startDate, endDate, id);
			revenue = getRevenueForRoom(id, startDate, endDate);
			roomNumber = romm.getRoomNumber();
			roomsReport += "Soba: " + roomNumber + " | Broj nocenja: " + numOfNights + " | Prihod: " + revenue + "\n";
		}
		return roomsReport;
	}

	public static double getRoomTypeRevenueByMonth(LocalDate startDate, LocalDate endDate, int id) {
		double revenue = 0;
		for (Room room : manager.getRoomsMan().getRooms().values()) {
			if (room.getRoomType().getId() == id) {
				revenue += getRevenueForRoom(room.getId(), startDate, endDate);
			}
		}
		return Math.round(revenue * 100.0) / 100.0;
	}

	private static List<Room> getOccupiedRooms() {
		LocalDate today = LocalDate.now();
		List<Room> occupiedRooms = new ArrayList<>();
		for (Room room : manager.getRoomsMan().getRooms().values()) {
			if (manager.getRoomsMan().getOccupiedDatesList(room.getId()).contains(today)) {
				occupiedRooms.add(room);
			}
		}
		return occupiedRooms;
	}

	public static String getOccupiedRoomsReport() {
		String occupiedRoomsReport = "";
		List<Room> occupiedRooms = getOccupiedRooms();
		for (Room room : occupiedRooms) {
			occupiedRoomsReport += "Soba: " + room.getRoomNumber() + " | Tip sobe: " + room.getRoomType().getType()
					+ "(" + room.getRoomType().getBeds() + ")\n";
		}
		return occupiedRoomsReport;
	}
}
