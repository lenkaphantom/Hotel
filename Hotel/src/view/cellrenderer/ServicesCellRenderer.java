package view.cellrenderer;

import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import entity.AdditionalServices;

public class ServicesCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value instanceof List) {
            @SuppressWarnings("unchecked")
            List<AdditionalServices> services = (List<AdditionalServices>) value;
            if (!services.isEmpty()) {
                setText("Usluge");
            } else {
                setText("");
            }
        }

        return this;
    }
}
