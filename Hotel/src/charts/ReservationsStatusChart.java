package charts;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import controler.ReportsControler;
import enumeracije.ReservationStatus;
import manage.ManageHotel;

public class ReservationsStatusChart {
	private ManageHotel manager = ManageHotel.getInstance();
	private Map<ReservationStatus, Integer> reservationsStatus = new HashMap<>();

	public ReservationsStatusChart() {
		LocalDate startDate = LocalDate.now().minusDays(30);
		LocalDate endDate = LocalDate.now();
		for (ReservationStatus status : ReservationStatus.values()) {
			if (status.equals(ReservationStatus.WAITING))
				reservationsStatus.put(status, ReportsControler.getNumOfWaitingReservations(startDate, endDate));
			else if (status.equals(ReservationStatus.CANCELLED))
				reservationsStatus.put(status, ReportsControler.getNumOfCancelledReservations(startDate, endDate));
			else if (status.equals(ReservationStatus.CONFIRMED))
				reservationsStatus.put(status, ReportsControler.getNumOfConfirmedReservations(startDate, endDate));
			else if (status.equals(ReservationStatus.DENIED))
				reservationsStatus.put(status, ReportsControler.getNumOfDeniedReservations(startDate, endDate));
			else if (status.equals(ReservationStatus.EXPIRED))
				reservationsStatus.put(status, ReportsControler.getNumOfExpiredReservations(startDate, endDate));
		}
	}

	public JPanel createPieChartPanel() {
		PieChart chart = new PieChartBuilder().width(800).height(600).title("Odnos broja rezervacija po statusu")
				.build();

		for (Map.Entry<ReservationStatus, Integer> entry : reservationsStatus.entrySet()) {
			chart.addSeries(entry.getKey().toString(), entry.getValue());
		}

		return new XChartPanel<>(chart);
	}
}
