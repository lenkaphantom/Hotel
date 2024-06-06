package model;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

import entity.AdditionalServices;
import entity.Prices;
import manage.ManageHotel;

public class ServicePriceModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private ManageHotel manager = ManageHotel.getInstance();
    private Prices prices;
    private String[] columnNames = { "Naziv usluge", "Cena" };

    public ServicePriceModel(int pricesId) {
        prices = manager.getPricesMan().getPrices().get(pricesId);
    }

    @Override
    public int getRowCount() {
        return prices.getPricePerService().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        @SuppressWarnings("unchecked")
		Map.Entry<AdditionalServices, Double> entry = (Map.Entry<AdditionalServices, Double>) prices.getPricePerService().entrySet().toArray()[rowIndex];
        AdditionalServices service = entry.getKey();
        Double price = entry.getValue();

        switch (columnIndex) {
            case 0:
                return service.getService();
            case 1:
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
        return (columnIndex == 1) ? Double.class : String.class;
    }
}
