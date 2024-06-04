package view.addedit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import entity.AdditionalServices;
import entity.Reservation;
import enumeracije.ReservationStatus;
import manage.ManageHotel;
import net.miginfocom.swing.MigLayout;
import view.GuestFrame;

public class AddEditReservationDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();
	private Reservation editR;
	private JFrame parent;

	private JComboBox<String> cbRoomType;
	private JDateChooser dcCheckIn;
	private JDateChooser dcCheckOut;
	private JCheckBox cbAdditionalServices;

	public AddEditReservationDialog(JFrame parent, int id) {
		super(parent, true);
		this.parent = parent;

		if (id != 0) {
			setTitle("Izmena rezervacije");
		} else {
			setTitle("Nova rezervacija");
		}

		this.editR = manager.getReservationsMan().getReservations().get(id);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		pack();
	}

	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][grow]", "[]10[]10[]10[]10[]20[]");
		setLayout(layout);

		JLabel lblRoomType = new JLabel("Room Type");
		add(lblRoomType);
		cbRoomType = new JComboBox<>(manager.getRoomTypesMan().getRoomTypesList());
		add(cbRoomType, "growx");

		JLabel lblCheckIn = new JLabel("Check-in Date");
		add(lblCheckIn);
		dcCheckIn = new JDateChooser();
		add(dcCheckIn, "growx");

		JLabel lblCheckOut = new JLabel("Check-out Date");
		add(lblCheckOut);
		dcCheckOut = new JDateChooser();
		add(dcCheckOut, "growx");

		JLabel lblAdditionalServices = new JLabel("Additional Services");
		add(lblAdditionalServices);
		cbAdditionalServices = new JCheckBox("Select additional services");
		add(cbAdditionalServices, "span");

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);

		if (editR != null) {
			cbRoomType.setSelectedItem(editR.getRoomType().getType());
			dcCheckIn.setDate(java.sql.Date.valueOf(editR.getStartDate()));
			dcCheckOut.setDate(java.sql.Date.valueOf(editR.getEndDate()));
			cbAdditionalServices.setSelected(true);
		}

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateFields()) {
					String roomType = cbRoomType.getSelectedItem().toString();
					Date checkInDate = dcCheckIn.getDate();
					Date checkOutDate = dcCheckOut.getDate();
					List<AdditionalServices> additionalServices = manager.getAdditionalServicesMan()
							.getAdditionalServicesFromNames(
									manager.getAdditionalServicesMan().getAdditionalServicesList());

					LocalDate localCheckInDate = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate localCheckOutDate = checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

					if (editR != null) {
						editR.setRoomType(manager.getRoomTypesMan().getRoomTypeFromType(roomType));
						editR.setStartDate(localCheckInDate);
						editR.setEndDate(localCheckOutDate);
						editR.setAdditionalServices(additionalServices);
						manager.getReservationsMan().changeReservation(editR.getId(), editR.getRoomType(),
								editR.getAdditionalServices(), editR.getStatus(), editR.getRoom());
					} else {
						Reservation newReservation = new Reservation(
								manager.getRoomTypesMan().getRoomTypeFromType(roomType), localCheckInDate,
								localCheckOutDate, additionalServices, ReservationStatus.WAITING);
						manager.getReservationsMan().addReservation(newReservation);
					}
					manager.getReservationsMan().writeReservations("data/reservations.csv");
					((GuestFrame) parent).refreshReservationTable();
					AddEditReservationDialog.this.dispose();
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditReservationDialog.this.dispose();
			}
		});
	}

	private boolean validateFields() {
		if (cbRoomType.getSelectedItem() == null || dcCheckIn.getDate() == null || dcCheckOut.getDate() == null) {
			JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		try {
			AddEditReservationDialog dialog = new AddEditReservationDialog(new JFrame(), 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}