package model;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

import entity.Prices;
import entity.RoomType;
import manage.ManageHotel;

public class RoomPriceModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private ManageHotel manager = ManageHotel.getInstance();
    private Map<RoomType, Double> pricePerRoom;
    private String[] columnNames = { "Tip sobe", "Broj kreveta", "Cena" };

    public RoomPriceModel(int pricesId) {
        if (pricesId != -1) {
            Prices prices = manager.getPricesMan().getPrices().get(pricesId);
            this.pricePerRoom = prices.getPricePerRoom();
			for (RoomType roomType : manager.getRoomTypesMan().getRoomTypes().values()) {
				if (!this.pricePerRoom.containsKey(roomType)) {
					this.pricePerRoom.put(roomType, 0.0);
				}
			}
        } else {
            this.pricePerRoom = manager.getRoomTypesMan().getDefaultPrices();
        }
    }

    @Override
    public int getRowCount() {
        return pricePerRoom.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        RoomType roomType = (RoomType) pricePerRoom.keySet().toArray()[rowIndex];
        Double price = pricePerRoom.get(roomType);

        switch (columnIndex) {
            case 0:
                return roomType.getType();
            case 1:
                return roomType.getBeds();
            case 2:
                return price;
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        RoomType roomType = (RoomType) pricePerRoom.keySet().toArray()[rowIndex];
        if (columnIndex == 2) {
            pricePerRoom.put(roomType, (Double) aValue);
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return (columnIndex == 2) ? Double.class : String.class;
    }

    public Map<RoomType, Double> getPrices() {
        return pricePerRoom;
    }

    public void setPrices(Map<RoomType, Double> prices) {
        this.pricePerRoom = prices;
        fireTableDataChanged();
    }
}
