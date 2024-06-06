package view.addedit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import entity.RoomType;
import enumeracije.TypeOfRoom;
import manage.ManageHotel;
import net.miginfocom.swing.MigLayout;
import view.AdministratorFrame;

public class AddEditRoomTypeDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();
	private RoomType editRT;
	private JFrame parent;

	private JComboBox<TypeOfRoom> cbTipSobe;
	private JComboBox<String> cbRasporedKreveta;

	public static void main(String[] args) {
		try {
			AddEditRoomTypeDialog dialog = new AddEditRoomTypeDialog(new JFrame(), 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AddEditRoomTypeDialog(JFrame parent, int id) {
		super(parent, true);
		this.parent = parent;
		if (id != 0) {
			setTitle("Izmena tipa sobe");
		} else {
			setTitle("Dodavanje tipa sobe");
		}
		this.editRT = manager.getRoomTypesMan().getRoomTypes().get(id);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		pack();
	}

	private void initGUI() {
		MigLayout ml = new MigLayout("wrap 2", "[][grow]", "[]20[]20[]");
		setLayout(ml);

		JLabel lblTipSobe = new JLabel("Tip sobe");
		add(lblTipSobe);
		cbTipSobe = new JComboBox<>(TypeOfRoom.values());
		cbTipSobe.addActionListener(e -> {
			TypeOfRoom selectedType = (TypeOfRoom) cbTipSobe.getSelectedItem();
			String[] bedLayouts = selectedType.getBedLayouts();
			cbRasporedKreveta.removeAllItems();
			for (String layout : bedLayouts) {
				cbRasporedKreveta.addItem(layout);
			}
		});
		add(cbTipSobe, "growx");

		JLabel lblRasporedKreveta = new JLabel("Raspored kreveta");
		add(lblRasporedKreveta);
		cbRasporedKreveta = new JComboBox<>();
		add(cbRasporedKreveta, "growx");

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOk = new JButton("OK");
		add(btnOk);

		if (editRT != null) {
			cbTipSobe.setSelectedItem(editRT.getType());
			String bedLayout = editRT.getBeds();
			TypeOfRoom selectedType = (TypeOfRoom) cbTipSobe.getSelectedItem();
			String[] bedLayouts = selectedType.getBedLayouts();
			for (String layout : bedLayouts) {
				cbRasporedKreveta.addItem(layout);
			}
			cbRasporedKreveta.setSelectedItem(bedLayout);
		}

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!validateFields()) {
					return;
				}
				TypeOfRoom selectedType = (TypeOfRoom) cbTipSobe.getSelectedItem();
				String bedLayout = (String) cbRasporedKreveta.getSelectedItem();
				if (editRT != null) {
					manager.getRoomTypesMan().changeRoomType(editRT.getId(), selectedType,
							findIndexInBedLayouts(bedLayout, selectedType.getBedLayouts()));
				} else {
					;
					manager.getRoomTypesMan().addRoomType(selectedType,
							findIndexInBedLayouts(bedLayout, selectedType.getBedLayouts()));
				}
				((AdministratorFrame) parent).refreshRoomTypeTable();
				manager.writeData();
				dispose();
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private boolean validateFields() {
	    TypeOfRoom selectedType = (TypeOfRoom) cbTipSobe.getSelectedItem();
	    String bedLayout = (String) cbRasporedKreveta.getSelectedItem();

	    if (selectedType == null) {
	        JOptionPane.showMessageDialog(this, "Morate izabrati tip sobe.", "Greška", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (bedLayout == null) {
	        JOptionPane.showMessageDialog(this, "Morate izabrati raspored kreveta.", "Greška", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    if (isRoomTypeAndBedLayoutAlreadyExists(selectedType, bedLayout, editRT != null ? editRT.getId() : -1)) {
	        JOptionPane.showMessageDialog(this,
	            "Već postoji kombinacija ovog tipa sobe i rasporeda kreveta.", "Greška", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    return true;
	}
	
	private boolean isRoomTypeAndBedLayoutAlreadyExists(TypeOfRoom selectedType, String bedLayout, int excludeId) {
	    for (RoomType roomType : manager.getRoomTypesMan().getRoomTypes().values()) {
	        if (roomType.getType().equals(selectedType) &&
	            roomType.getBeds().equals(bedLayout) &&
	            roomType.getId() != excludeId) {
	            return true;
	        }
	    }
	    return false;
	}

	private int findIndexInBedLayouts(String layout, String[] bedLayouts) {
		for (int i = 0; i < bedLayouts.length; i++) {
			if (bedLayouts[i].equals(layout)) {
				return i;
			}
		}
		return -1;
	}
}
