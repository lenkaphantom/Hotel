package charts;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import controler.DateRanges;
import controler.ReportsControler;
import entity.RoomType;
import manage.ManageHotel;
import net.miginfocom.swing.MigLayout;

public class HotelRevenueChart {
	private ManageHotel manager = ManageHotel.getInstance();
	private Map<String, List<Double>> roomTypeRevenue = new HashMap<>();
	private List<Date> monthNames = new ArrayList<>();

	public HotelRevenueChart() {
		List<LocalDate[]> monthlyIntervals = DateRanges.getMonthlyIntervals(LocalDate.now().minusYears(1),
				LocalDate.now());

		for (LocalDate[] interval : monthlyIntervals) {
			monthNames.add(Date.from(interval[0].atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		}

		for (RoomType type : manager.getRoomTypesMan().getRoomTypes().values()) {
			String roomType = type.getType().toString() + "(" + type.getBeds() + ")";
			List<Double> revenue = new ArrayList<>();
			for (LocalDate[] interval : monthlyIntervals) {
				revenue.add(ReportsControler.getRoomTypeRevenueByMonth(interval[0], interval[1], type.getId()));
			}
			roomTypeRevenue.put(roomType, revenue);
		}
	}

	private JPanel createChartsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[grow]", "[][][grow]"));

		JTabbedPane chartTabbedPane = new JTabbedPane();

		JPanel hotelRevenueChartPanel = new JPanel(new MigLayout("", "[grow]", "[][grow]"));
		chartTabbedPane.addTab("Grafikon tipova soba", hotelRevenueChartPanel);
		chartTabbedPane.addTab("Grafikon održavanja", new JPanel());
		chartTabbedPane.addTab("Grafikon statusa rezervacija", new JPanel());

		hotelRevenueChartPanel.add(createChartPanel(), "cell 0 1, grow");

		panel.add(chartTabbedPane, "cell 0 1, grow");
		return panel;
	}

	public JPanel createChartPanel() {
	    List<Double> totalRevenue = new ArrayList<>(Collections.nCopies(monthNames.size(), 0.0));

	    for (List<Double> revenue : roomTypeRevenue.values()) {
	        for (int i = 0; i < revenue.size(); i++) {
	            totalRevenue.set(i, totalRevenue.get(i) + revenue.get(i));
	        }
	    }

	    XYChart chart = new XYChartBuilder().width(800).height(600).title("Hotel Revenue by Room Type")
	            .xAxisTitle("Month").yAxisTitle("Revenue").build();

	    chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
	    chart.getStyler().setYAxisGroupPosition(0, Styler.YAxisPosition.Left);

	    chart.addSeries("Total Revenue", monthNames, totalRevenue);
	    for (String roomType : roomTypeRevenue.keySet()) {
	        chart.addSeries(roomType, monthNames, roomTypeRevenue.get(roomType));
	    }

	    return new XChartPanel<>(chart);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Hotel Revenue Charts");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(new HotelRevenueChart().createChartsPanel());
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		});
	}
}
