package controler;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class DateRanges {
	
	public static void main(String[] args) {
		LocalDate endDate = LocalDate.now();
		LocalDate startDate = endDate.minusYears(1);

		List<LocalDate[]> monthlyIntervals = getMonthlyIntervals(startDate, endDate);

		for (LocalDate[] interval : monthlyIntervals) {
			System.out.println("Start: " + interval[0] + ", End: " + interval[1]);
		}
	}

	public static List<LocalDate[]> getMonthlyIntervals(LocalDate startDate, LocalDate endDate) {
		List<LocalDate[]> intervals = new ArrayList<>();

		YearMonth endMonth = YearMonth.from(endDate);
		YearMonth startMonth = YearMonth.from(startDate);

		while (intervals.size() < 12) {
			LocalDate firstDayOfMonth = endMonth.atDay(1);
			LocalDate lastDayOfMonth = endMonth.atEndOfMonth();

			if (firstDayOfMonth.isBefore(startDate)) {
				firstDayOfMonth = startDate;
			}
			if (lastDayOfMonth.isAfter(endDate)) {
				lastDayOfMonth = endDate;
			}

			intervals.add(new LocalDate[] { firstDayOfMonth, lastDayOfMonth });
			endMonth = endMonth.minusMonths(1);
		}

		List<LocalDate[]> reversedIntervals = new ArrayList<>();
		for (int i = intervals.size() - 1; i >= 0; i--) {
			reversedIntervals.add(intervals.get(i));
		}

		return reversedIntervals;
	}
}
