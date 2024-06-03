package view.comboBoxRenderer;

import java.awt.Component;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class AdditionalServicesCellRenderer extends JComboBox<String> implements TableCellRenderer {
	private static final long serialVersionUID = 1L;
	
	public AdditionalServicesCellRenderer(List<String> services) {
		super(services.toArray(new String[0]));
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setSelectedItem(value);
		return this;
	}

}
