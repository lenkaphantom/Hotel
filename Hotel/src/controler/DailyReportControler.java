package controler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import entity.Reservation;
import manage.ManageHotel;

public class DailyReportControler {
	private ManageHotel manager = ManageHotel.getInstance();
	private Map<Integer, Reservation> checkIns = new HashMap<>();
	private Map<Integer, Reservation> checkOuts = new HashMap<>();
	
	public DailyReportControler() {
		setCheckIns();
		setCheckOuts();
	}
	
	public void setCheckIns() {
		for (Map.Entry<Integer, Reservation> entry : manager.getReservationsMan().getReservations().entrySet()) {
			if (entry.getValue().getCheckInDate() == null) {
				continue;
			}
			
			if (entry.getValue().getCheckInDate().equals(LocalDate.now())) {
				checkIns.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public void setCheckOuts() {
		for (Map.Entry<Integer, Reservation> entry : manager.getReservationsMan().getReservations().entrySet()) {
			if (entry.getValue().getCheckOutDate() == null) {
				continue;
			}
			
			if (entry.getValue().getCheckOutDate().equals(LocalDate.now())) {
				checkOuts.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public Map<Integer, Reservation> getCheckIns() {
		return checkIns;
	}
	
	public Map<Integer, Reservation> getCheckOuts() {
		return checkOuts;
	}
	
	public void updateCheckIns() {
		this.checkIns.clear();
		this.setCheckIns();
	}
	
	public void updateCheckOuts() {
		this.checkOuts.clear();
		this.setCheckOuts();
	}
	
	public void update() {
		this.updateCheckIns();
		this.updateCheckOuts();
	}
}
