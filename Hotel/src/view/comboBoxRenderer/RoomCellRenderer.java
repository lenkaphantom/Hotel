package view.comboBoxRenderer;

import java.awt.Component;
import java.time.LocalDate;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RoomCellRenderer extends JComboBox<String> implements TableCellRenderer {
    private static final long serialVersionUID = 1L;

    public RoomCellRenderer() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        removeAllItems();
        if (value != null && value instanceof Map<?, ?>) {
            Map<?, ?> occupiedDates = (Map<?, ?>) value;
            for (Map.Entry<?, ?> entry : occupiedDates.entrySet()) {
                if (entry.getKey() instanceof LocalDate && entry.getValue() instanceof LocalDate) {
                    LocalDate startDate = (LocalDate) entry.getKey();
                    LocalDate endDate = (LocalDate) entry.getValue();
                    addItem(startDate + " - " + endDate);
                }
            }
        }
        return this;
    }
}
