package charts;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import controler.ReportsControler;
import entity.HouseKeeper;
import manage.ManageHotel;

public class HouseKeepersChart {
	private ManageHotel manager = ManageHotel.getInstance();
	private Map<String, Integer> houseKeepers = new HashMap<>();

	public HouseKeepersChart() {
		for (HouseKeeper hKeepr : manager.getEmployeesMan().getHouseKeepers().values()) {
			houseKeepers.put(hKeepr.getUsername(), ReportsControler.getNumOfCleanedRooms(LocalDate.now().minusYears(1),
					LocalDate.now(), hKeepr.getId()));
		}
	}

	public JPanel createPieChartPanel() {
		PieChart chart = new PieChartBuilder().width(800).height(600).title("Odnos broja očišćenih soba").build();
		
		for (Map.Entry<String, Integer> entry : houseKeepers.entrySet()) {
			chart.addSeries(entry.getKey(), entry.getValue());
		}
		
		return new XChartPanel<>(chart);
	}
}
