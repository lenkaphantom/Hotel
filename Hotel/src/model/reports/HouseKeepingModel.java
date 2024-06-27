package model.reports;

import java.time.LocalDate;

import javax.swing.table.AbstractTableModel;

import controler.ReportsControler;
import entity.HouseKeeper;
import manage.ManageHotel;

public class HouseKeepingModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();
	private String[] columnNames = { "ID", "Korisnicko ime", "Broj soba" };
	private LocalDate startDate;
	private LocalDate endDate;

	public HouseKeepingModel(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	@Override
	public int getRowCount() {
		return manager.getEmployeesMan().getHouseKeepers().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		HouseKeeper houseKeeper = manager.getEmployeesMan().getHouseKeepers().get(rowIndex + 1);
		switch (columnIndex) {
		case 0:
			return houseKeeper.getId();
		case 1:
			return houseKeeper.getUsername();
		case 2:
			return ReportsControler.getNumOfCleanedRooms(startDate, endDate, rowIndex + 1);
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
