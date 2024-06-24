package view.comboBoxRenderer;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class AdditionalServicesCellRenderer extends JTextField implements TableCellRenderer {
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (value != null) {
			setText(value.toString());
		} else {
			setText("");
		}
		return this;
	}
}
