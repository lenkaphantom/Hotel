package view.comboBoxRenderer;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.time.LocalDate;
import java.util.Map;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class RoomCellEditor extends AbstractCellEditor implements TableCellEditor {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboBox;

    public RoomCellEditor() {
        comboBox = new JComboBox<>();
        comboBox.setMaximumRowCount(10);

        comboBox.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                comboBox.showPopup();
            }
        });
    }

    @Override
    public Object getCellEditorValue() {
        return comboBox.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        comboBox.removeAllItems();
        if (value != null && value instanceof Map<?, ?>) {
            Map<?, ?> occupiedDates = (Map<?, ?>) value;
            for (Map.Entry<?, ?> entry : occupiedDates.entrySet()) {
                if (entry.getKey() instanceof LocalDate && entry.getValue() instanceof LocalDate) {
                    LocalDate startDate = (LocalDate) entry.getKey();
                    LocalDate endDate = (LocalDate) entry.getValue();
                    comboBox.addItem(startDate + " - " + endDate);
                }
            }
        }
        return comboBox;
    }
}
