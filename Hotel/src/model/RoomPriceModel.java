package model;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

import entity.Prices;
import entity.RoomType;
import manage.ManageHotel;

public class RoomPriceModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private ManageHotel manager = ManageHotel.getInstance();
    private Prices prices;
    private String[] columnNames = { "Tip sobe", "Broj kreveta", "Cena" };

    public RoomPriceModel(int pricesId) {
        prices = manager.getPricesMan().getPrices().get(pricesId);
    }

    @Override
    public int getRowCount() {
        return prices.getPricePerRoom().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        @SuppressWarnings("unchecked")
		Map.Entry<RoomType, Double> entry = (Map.Entry<RoomType, Double>) prices.getPricePerRoom().entrySet().toArray()[rowIndex];
        RoomType roomType = entry.getKey();
        Double price = entry.getValue();

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
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 1:
                return Integer.class;
            case 2:
                return Double.class;
            default:
                return String.class;
        }
    }
}
