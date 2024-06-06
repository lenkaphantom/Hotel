package view.addedit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.Room;
import entity.RoomType;
import enumeracije.RoomStatus;
import enumeracije.TypeOfRoom;
import manage.ManageHotel;
import net.miginfocom.swing.MigLayout;
import validation.Validation;
import view.AdministratorFrame;

public class AddEditRoomDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ManageHotel manager = ManageHotel.getInstance();
	private Room editR;
	private JFrame parent;

	private JTextField tfFloor;
	private JTextField tfRoomNumber;
	private JComboBox<TypeOfRoom> cbRoomType;
	private JComboBox<String> cbBedLayout;

	public static void main(String[] args) {
		try {
			AddEditRoomDialog dialog = new AddEditRoomDialog(null, 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AddEditRoomDialog(JFrame parent, int id) {
		super(parent, true);
		this.parent = parent;
		if (id != 0) {
			setTitle("Edit Room");
		} else {
			setTitle("Add Room");
		}
		this.editR = manager.getRoomsMan().getRooms().get(id);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		pack();
	}

	public void initGUI() {
		MigLayout migLayout = new MigLayout("wrap 2", "[][]", "[]10[]10[]10[]10[]20[]");
		setLayout(migLayout);

		JLabel lblFloor = new JLabel("Floor:");
		add(lblFloor, "cell 0 0,alignx trailing");

		tfFloor = new JTextField();
		add(tfFloor, "cell 1 0,growx");
		tfFloor.setColumns(10);

		JLabel lblRoomNumber = new JLabel("Room Number:");
		add(lblRoomNumber, "cell 0 1,alignx trailing");

		tfRoomNumber = new JTextField();
		add(tfRoomNumber, "cell 1 1,growx");
		tfRoomNumber.setColumns(10);

		JLabel lblRoomType = new JLabel("Room Type:");
		add(lblRoomType, "cell 0 2,alignx trailing");

		List<TypeOfRoom> availableRoomTypes = manager.getRoomTypesMan().getRoomTypesListEnum();
		cbRoomType = new JComboBox<>(availableRoomTypes.toArray(new TypeOfRoom[0]));
		add(cbRoomType, "cell 1 2,growx");

		JLabel lblBedLayout = new JLabel("Bed Layout:");
		add(lblBedLayout, "cell 0 3,alignx trailing");

		cbBedLayout = new JComboBox<>();
		add(cbBedLayout, "cell 1 3,growx");

		cbRoomType.addActionListener(e -> {
			TypeOfRoom selectedType = (TypeOfRoom) cbRoomType.getSelectedItem();
			if (selectedType != null) {
				Map<TypeOfRoom, List<String>> bedLayouts = manager.getRoomTypesMan().getBedLayouts();
				cbBedLayout.removeAllItems();
				List<String> layouts = bedLayouts.get(selectedType);
				if (layouts != null) {
					for (String layout : layouts) {
						cbBedLayout.addItem(layout);
					}
				}
				if (cbBedLayout.getItemCount() > 0) {
					cbBedLayout.setSelectedIndex(0);
				}
			}
		});
		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);

		if (editR != null) {
			tfFloor.setText(String.valueOf(editR.getFloor()));
			tfRoomNumber.setText(String.valueOf(editR.getRoomNumber()));
			cbRoomType.setSelectedItem(editR.getRoomType().getType());
			cbBedLayout.setSelectedItem(editR.getRoomType().getBeds());
		}
		
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateFields()) {
					int floor = Integer.parseInt(tfFloor.getText());
					int roomNumber = Integer.parseInt(tfRoomNumber.getText());
					TypeOfRoom selectedType = (TypeOfRoom) cbRoomType.getSelectedItem();
					String bedLayout = (String) cbBedLayout.getSelectedItem();

					RoomType roomType = manager.getRoomTypesMan().getRoomTypeFromTypeAndBeds(selectedType, bedLayout);
					
					if (editR != null) {
						manager.getRoomsMan().changeRoom(editR.getId(), floor, roomNumber, roomType);
					}
					else {
						manager.getRoomsMan().addRoom(floor, roomNumber, RoomStatus.FREE, roomType.getId(), manager);
					}
					((AdministratorFrame) parent).refreshRoomsTable();
					AddEditRoomDialog.this.dispose();
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditRoomDialog.this.dispose();
			}
		});
	}

	private boolean validateFields() {
		if (Validation.isStringEmpty(tfFloor.getText()) || Validation.isStringEmpty(tfRoomNumber.getText())
				|| cbRoomType.getSelectedIndex() == -1 || cbBedLayout.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try {
			Integer.parseInt(tfFloor.getText());
			Integer.parseInt(tfRoomNumber.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Sprat i broj sobe moraju biti celi brojevi.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (Validation.isNumberNegative(Integer.parseInt(tfFloor.getText()))
				|| Validation.isNumberNegative(Integer.parseInt(tfRoomNumber.getText()))) {
			JOptionPane.showMessageDialog(this, "Sprat i broj sobe moraju biti pozitivni brojevi.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (Integer.parseInt(tfRoomNumber.getText()) > 999 || Integer.parseInt(tfRoomNumber.getText()) < 101) {
			JOptionPane.showMessageDialog(this, "Broj sobe ne sme biti veci od 999.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
