package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controler.DailyReportControler;
import entity.Reservation;

public class DailyCheckOutModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private DailyReportControler controler = new DailyReportControler();
	private String[] columnNames = { "ID", "Tip sobe", "Početak rezervacije", "Kraj rezervacije", "Dodatne usluge",
			"Status", "Soba", "Gost", "Ukupna cena", "Check-in datum", "Check-out datum" };

	@Override
	public int getRowCount() {
		return controler.getCheckOuts().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		List<Reservation> checkOuts = new ArrayList<>(controler.getCheckOuts().values());

		if (rowIndex >= checkOuts.size()) {
			return null;
		}

		Reservation checkOut = checkOuts.get(rowIndex);

		if (checkOut == null)
			return null;

		switch (columnIndex) {
		case 0:
			return checkOut.getId();
		case 1:
			return checkOut.getRoomType().getType();
		case 2:
			return checkOut.getStartDate();
		case 3:
			return checkOut.getEndDate();
		case 4:
			return checkOut.getAdditionalServices();
		case 5:
			return checkOut.getStatus().toString();
		case 6:
			return checkOut.getRoom().getRoomNumber();
		case 7:
			return checkOut.getGuest().getUsername();
		case 8:
			return checkOut.getTotalPrice();
		case 9:
			return checkOut.getCheckInDate();
		case 10:
			return checkOut.getCheckOutDate();
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
