package model;

import java.util.Map;

import javax.swing.table.AbstractTableModel;

import entity.AdditionalServices;
import entity.Prices;
import manage.ManageHotel;

public class ServicePriceModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private ManageHotel manager = ManageHotel.getInstance();
    private Map<AdditionalServices, Double> pricePerService;
    private String[] columnNames = { "Naziv usluge", "Cena" };

    public ServicePriceModel(int pricesId) {
        if (pricesId != -1) {
            Prices prices = manager.getPricesMan().getPrices().get(pricesId);
            this.pricePerService = prices.getPricePerService();
        } else {
            this.pricePerService = manager.getAdditionalServicesMan().getDefaultPrices();
        }
    }

    @Override
    public int getRowCount() {
        return pricePerService.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AdditionalServices service = (AdditionalServices) pricePerService.keySet().toArray()[rowIndex];
        Double price = pricePerService.get(service);

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
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        AdditionalServices service = (AdditionalServices) pricePerService.keySet().toArray()[rowIndex];
        if (columnIndex == 1) {
            pricePerService.put(service, (Double) aValue);
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return (columnIndex == 1) ? Double.class : String.class;
    }

    public Map<AdditionalServices, Double> getPrices() {
        return pricePerService;
    }

    public void setPrices(Map<AdditionalServices, Double> prices) {
        this.pricePerService = prices;
        fireTableDataChanged();
    }
}
