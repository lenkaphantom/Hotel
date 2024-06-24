package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controler.ReservationControler;
import entity.Reservation;

public class ReservationModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ReservationControler controler;
	private String[] columnNames = { "ID", "Tip sobe", "Check-in datum", "Check-out datum", "Dodatne usluge", "Status",
			"Soba", "Gost", "Ukupna cena" };

	public ReservationModel(String username) {
		controler = new ReservationControler(username);
	}

	public ReservationControler getControler() {
		return this.controler;
	}

	@Override
	public int getRowCount() {
		return controler.getReservations().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		List<Reservation> reservations = new ArrayList<>(controler.getReservations().values());

		if (rowIndex >= reservations.size()) {
			return null;
		}

		Reservation reservation = reservations.get(rowIndex);

		if (reservation == null)
			return null;

		switch (columnIndex) {
		case 0:
			return reservation.getId();
		case 1:
			return reservation.getRoomType().getType();
		case 2:
			return reservation.getStartDate();
		case 3:
			return reservation.getEndDate();
		case 4:
			return "Usluge";
		case 5:
			return reservation.getStatus();
		case 6:
			if (reservation.getRoom() != null)
				return reservation.getRoom().getRoomNumber();
			return null;
		case 7:
			if (reservation.getGuest() != null)
				return reservation.getGuest().getUsername();
			return null;
		case 8:
			return reservation.getTotalPrice();
		}
		return null;
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
