package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import controler.RoomControler;
import entity.Room;

public class RoomModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private RoomControler controler;
    private String[] columnNames = { "ID", "Sprat", "Broj sobe", "Status", "Tip sobe", "Zauzeti datumi", "Osobine" };
    
	public RoomModel(String username, LocalDate date) {
		this.controler = new RoomControler(username, date);
	}

    @Override
    public int getRowCount() {
        return controler.getRooms().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	List<Room> rooms = new ArrayList<>(controler.getRooms().values());
        Room room = rooms.get(rowIndex);
        if (room == null)
        	return null;
        switch (columnIndex) {
            case 0:
                return room.getId();
            case 1:
                return room.getFloor();
            case 2:
                return room.getRoomNumber();
            case 3:
                return room.getRoomStatus();
            case 4:
                return room.getRoomType().getType();
            case 5:
                return room.getOccupiedDates();
            case 6:
            	return room.getRoomSpecs();
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
        if (this.getValueAt(0, columnIndex) == null)
            return Object.class;
        return this.getValueAt(0, columnIndex).getClass();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 5 || column == 6;
    }
    
	public RoomControler getControler() {
		return controler;
	}
}
