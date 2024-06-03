package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import controler.ReservationControler;
import entity.Guest;

public class ReservationModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private ReservationControler controler;
    private String[] columnNames = { "ID", "Room type", "Start date", "End date", "Additional services", "Status", "Room", "Guest", "Total price" };
    private List<String> additionalServicesList;

    public ReservationModel(Guest guest) {
        this.controler = new ReservationControler(guest);
    }

    @Override
    public int getRowCount() {
        if (this.controler.getReservations() == null) {
            return 0;
        }
        return this.controler.getReservations().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        entity.Reservation reservation = this.controler.getReservations().get(rowIndex);
        switch (columnIndex) {
        case 0:
            return reservation.getId();
        case 1:
            return reservation.getRoomType().getType();
        case 2:
            return reservation.getStartDate();
        case 3:
            return reservation.getEndDate();
        case 4:
            return reservation.getAdditionalServices().toArray(new String[0]);
        case 5:
            return reservation.getStatus();
        case 6:
            if (reservation.getRoom() != null)
                return reservation.getRoom().getRoomNumber();
            else
                return null;
        case 7:
            if (reservation.getGuest() != null)
                return reservation.getGuest().getUsername();
            else
                return null;
        case 8:
            return reservation.getTotalPrice();
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

    public List<String> getAdditionalServicesList() {
        return additionalServicesList;
    }
}
