package controler;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import entity.Employee;
import entity.HouseKeeper;
import entity.Reservation;
import enumeracije.ReservationStatus;
import manage.ManageHotel;

public abstract class ReportsControler {
	private ManageHotel manager = ManageHotel.getInstance();

	public double getRevenue(LocalDate startDate, LocalDate endDate) {
		int revenue = 0;
		for (Reservation reservation : manager.getReservationsMan().getReservations().values()) {
			LocalDate tsDate = startDate.minusDays(1);
			LocalDate teDate = endDate.plusDays(1);
			if (tsDate.isBefore(reservation.getCreationDate()) && teDate.isAfter(reservation.getCreationDate())) {
				revenue += reservation.getTotalPrice();
			}
		}

		return revenue;
	}

	public double getExpenses(LocalDate startDate, LocalDate endDate) {
		int expenses = 0;
		Period period = Period.between(startDate, endDate);
		int numOfMonths = period.getMonths();

		for (Employee employee : manager.getEmployeesMan().getEmployees().values()) {
			expenses += employee.getSalary();
		}

		return numOfMonths * expenses;
	}

	public int getNumOfCleanedRooms(LocalDate startDate, LocalDate endDate, int id) {
		if (manager.getEmployeesMan().getEmployees().get(id) == null
				|| !(manager.getEmployeesMan().getEmployees().get(id) instanceof HouseKeeper)) {
			return -1;
		}

		int numOfCleanedRooms = 0;
		HouseKeeper houseKeeper = manager.getEmployeesMan().getHouseKeepers().get(id);

		for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
			numOfCleanedRooms += houseKeeper.getRoomsToClean().get(date).size();
		}

		return numOfCleanedRooms;
	}

	public int getNumOfConfirmedReservations(LocalDate startDate, LocalDate endDate) {
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

	public int getNumOfProcessedReservations(LocalDate startDate, LocalDate endDate) {
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

	public int getNumOfCancelledReservations(LocalDate startDate, LocalDate endDate) {
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

	public int getNumOfDeniedReservations(LocalDate startDate, LocalDate endDate) {
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

	public int getNumOfNights(LocalDate startDate, LocalDate endDate, int id) {
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

	public double getRevenueForRoom(int id, LocalDate startDate, LocalDate endDate) {
		if (manager.getRoomsMan().getRooms().get(id) == null) {
			return -1;
		}
		double revenue = 0;
		for (Reservation reservation : manager.getReservationsMan().getReservations().values()) {
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
		return revenue;
	}
}
