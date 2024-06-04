package model;

import javax.swing.table.AbstractTableModel;

import controler.ReservationControler;
import entity.Reservation;
import manage.ManageHotel;

public class ReservationModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ReservationControler controler;
	private String[] columnNames = { "ID", "Room type", "Start date", "End date", "Additional services", "Status",
			"Room", "Guest", "Total price" };

	public ReservationModel(String username) {
		controler = new ReservationControler(username);
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
		int row = 0;
		for (Integer key : controler.getReservations().keySet()) {
			if (row == rowIndex) {
				Reservation reservation = controler.getReservations().get(key);
				if (reservation == null)
					return null;

				switch (columnIndex) {
				case 0:
					return key;
				case 1:
					if (reservation.getRoom() != null && reservation.getRoom().getRoomType() != null)
						return reservation.getRoom().getRoomType().getType();
					return null;
				case 2:
					return reservation.getStartDate();
				case 3:
					return reservation.getEndDate();
				case 4:
					return controler.getAdditionalServices(key);
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
			}
			row++;
		}
		return null;
	}

	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (this.getValueAt(0, columnIndex) == null)
			return Object.class;
		return this.getValueAt(0, columnIndex).getClass();
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
	    return column == 4;
	}
}
