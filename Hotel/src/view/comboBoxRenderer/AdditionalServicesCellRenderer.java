package view.comboBoxRenderer;

import java.awt.Component;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class AdditionalServicesCellRenderer extends JComboBox<String> implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	public AdditionalServicesCellRenderer() {
		super();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		removeAllItems();
		if (value != null && value instanceof List<?>) {
			List<?> services = (List<?>) value;
			for (Object service : services) {
				if (service instanceof String) {
					addItem((String) service);
				}
			}
			if (!services.isEmpty() && services.get(0) instanceof String) {
				setSelectedItem(services.get(0));
			}
		}
		return this;
	}
}
