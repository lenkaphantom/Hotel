package model;

import javax.swing.table.AbstractTableModel;

import entity.RoomType;
import manage.ManageHotel;

public class RoomTypeModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();
	private String[] columnNames = { "ID", "Tip sobe", "Raspored kreveta" };

	public RoomTypeModel() {
	}

	@Override
	public int getRowCount() {
		return manager.getRoomTypesMan().getRoomTypes().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		RoomType roomType = this.manager.getRoomTypesMan().getRoomTypes().get(rowIndex + 1);
		switch (columnIndex) {
		case 0:
			return roomType.getId();
		case 1:
			return roomType.getType();
		case 2:
			return roomType.getBeds();
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
