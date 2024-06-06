package model;

import javax.swing.table.AbstractTableModel;

import entity.AdditionalServices;
import manage.ManageHotel;

public class AdditionalServicesModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();
	private String[] columnNames = { "ID", "Usluga" };

	public AdditionalServicesModel() {
	}

	@Override
	public int getRowCount() {
		return manager.getAdditionalServicesMan().getAdditionalServices().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		AdditionalServices additionalServices = this.manager.getAdditionalServicesMan().getAdditionalServices()
				.get(rowIndex + 1);

		switch (columnIndex) {
		case 0:
			return additionalServices.getId();
		case 1:
			return additionalServices.getService();
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
