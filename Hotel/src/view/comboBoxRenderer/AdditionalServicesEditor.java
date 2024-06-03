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
	
	public AdditionalServicesEditor(List<String> additionalServices) {
		comboBox = new JComboBox<>(additionalServices.toArray(new String[0]));
	}

	@Override
	public Object getCellEditorValue() {
		return comboBox.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		comboBox.setSelectedItem(value);
		return comboBox;
	}

}
