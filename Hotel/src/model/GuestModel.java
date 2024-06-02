package model;

import javax.swing.table.AbstractTableModel;

import entity.Guest;
import manage.ManageHotel;

public class GuestModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();
	private String[] columnNames = { "ID", "Ime", "Prezime", "Pol", "Datum rodjenja", "Broj telefona", "Adresa",
			"Korisnicko ime", "Lozinka" };

	public GuestModel() {
	}

	@Override
	public int getRowCount() {
		return manager.getGuestsMan().getGuests().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Guest guest = this.manager.getGuestsMan().getGuests().get(rowIndex + 1);
		switch (columnIndex) {
		case 0:
			return guest.getId();
		case 1:
			return guest.getFirstName();
		case 2:
			return guest.getLastName();
		case 3:
			return guest.getGender();
		case 4:
			return guest.getDate();
		case 5:
			return guest.getPhone();
		case 6:
			return guest.getAddress();
		case 7:
			return guest.getUsername();
		case 8:
			return guest.getPassword();
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
		return this.getValueAt(0, columnIndex).getClass();
	}
}
