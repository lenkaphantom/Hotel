package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controler.DailyReportControler;
import entity.Reservation;

public class DailyCheckInModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private DailyReportControler controler = new DailyReportControler();
	private String[] columnNames = { "ID", "Tip sobe", "Početak rezervacije", "Kraj rezervacije", "Dodatne usluge",
			"Status", "Soba", "Gost", "Ukupna cena", "Check-in datum", "Check-out datum" };

	@Override
	public int getRowCount() {
		return controler.getCheckIns().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		List<Reservation> checkIns = new ArrayList<>(controler.getCheckIns().values());

		if (rowIndex >= checkIns.size()) {
			return null;
		}

		Reservation checkIn = checkIns.get(rowIndex);

		if (checkIn == null)
			return null;

		switch (columnIndex) {
		case 0:
			return checkIn.getId();
		case 1:
			return checkIn.getRoomType().getType();
		case 2:
			return checkIn.getStartDate();
		case 3:
			return checkIn.getEndDate();
		case 4:
			return checkIn.getAdditionalServices();
		case 5:
			return checkIn.getStatus().toString();
		case 6:
			if (checkIn.getRoom() == null) {
				return null;
			}
			return checkIn.getRoom().getRoomNumber();
		case 7:
			if (checkIn.getGuest() == null) {
				return null;
			}
			return checkIn.getGuest().getUsername();
		case 8:
			return checkIn.getTotalPrice();
		case 9:
			return checkIn.getCheckInDate();
		case 10:
			return checkIn.getCheckOutDate();
		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (getRowCount() == 0) {
			return Object.class;
		}
		Object value = this.getValueAt(0, columnIndex);
		return value != null ? value.getClass() : Object.class;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 4;
	}
}
