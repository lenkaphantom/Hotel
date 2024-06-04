package view.comboBoxRenderer;

import java.awt.Component;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class AdditionalServicesEditor extends AbstractCellEditor implements TableCellEditor {
    private static final long serialVersionUID = 1L;
    private JComboBox<String> comboBox;

    public AdditionalServicesEditor(String[] additionalServices) {
        comboBox = new JComboBox<>(additionalServices);
    }

    @Override
    public Object getCellEditorValue() {
        return comboBox.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        comboBox.removeAllItems();
        if (value != null) {
            if (value instanceof List<?>) {
                List<?> services = (List<?>) value;
                for (Object service : services) {
                    if (service instanceof String) {
                        comboBox.addItem((String) service);
                    }
                }
                // Select the first service by default if there are any services
                if (!services.isEmpty() && services.get(0) instanceof String) {
                    comboBox.setSelectedItem(services.get(0));
                }
            }
        }
        return comboBox;
    }
}
