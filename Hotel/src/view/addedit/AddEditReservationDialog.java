package view.addedit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;

import controler.ReservationControler;
import entity.AdditionalServices;
import entity.Guest;
import entity.Reservation;
import entity.RoomType;
import enumeracije.ReservationStatus;
import enumeracije.TypeOfRoom;
import manage.ManageHotel;
import net.miginfocom.swing.MigLayout;
import view.GuestFrame;

public class AddEditReservationDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();
	private ReservationControler controler;
	private Reservation editR;
	private JFrame parent;

	private JComboBox<TypeOfRoom> cbTipSobe;
	private JComboBox<String> cbRasporedKreveta;
	private JDateChooser dcCheckIn;
	private JDateChooser dcCheckOut;
	private JList<String> listAdditionalServices;
	
	private Guest guest;

	public AddEditReservationDialog(JFrame parent, int id, String guestUsername) {
		super(parent, true);
		this.parent = parent;
		this.controler = new ReservationControler(guestUsername);
		this.guest = manager.getGuestsMan().getGuestFromUsername(guestUsername);

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

		cbTipSobe = new JComboBox<>(TypeOfRoom.values());
	    cbRasporedKreveta = new JComboBox<>();

	    cbTipSobe.addActionListener(e -> {
	        TypeOfRoom selectedType = (TypeOfRoom) cbTipSobe.getSelectedItem();
	        String[] bedLayouts = selectedType.getBedLayouts();
	        cbRasporedKreveta.removeAllItems();
	        for (String layoutB : bedLayouts) {
	            cbRasporedKreveta.addItem(layoutB);
	        }
	        if (cbRasporedKreveta.getItemCount() > 0) {
	            cbRasporedKreveta.setSelectedIndex(0);
	        }
	    });
	    add(cbTipSobe, "growx");

	    if (cbTipSobe.getItemCount() > 0) {
	        cbTipSobe.setSelectedIndex(0);
	    }
	    
	    JLabel lblTipSobe = new JLabel("Tip sobe");
	    add(lblTipSobe);
	    add(cbTipSobe, "growx");

	    JLabel lblRasporedKreveta = new JLabel("Raspored kreveta");
	    add(lblRasporedKreveta);
	    add(cbRasporedKreveta, "growx");

		JLabel lblCheckIn = new JLabel("Check-in datum");
		add(lblCheckIn);
		dcCheckIn = new JDateChooser();
		add(dcCheckIn, "growx");

		JLabel lblCheckOut = new JLabel("Check-out datum");
		add(lblCheckOut);
		dcCheckOut = new JDateChooser();
		add(dcCheckOut, "growx");

		JLabel lblAdditionalServices = new JLabel("Dodatne usluge");
		add(lblAdditionalServices);
		DefaultListModel<String> model = new DefaultListModel<>();
		for (String service : manager.getAdditionalServicesMan().getAdditionalServicesList()) {
			model.addElement(service);
		}
		JList<String> listAdditionalServices = new JList<>(model);
		listAdditionalServices.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollPane = new JScrollPane(listAdditionalServices);
		add(scrollPane, "span, growx");

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);

		if (editR != null) {
			List<String> additionalServices = manager.getAdditionalServicesList(editR.getId());
			int[] selectedIndices = additionalServices.stream().mapToInt(service -> model.indexOf(service)).toArray();
			listAdditionalServices.setSelectedIndices(selectedIndices);
		}

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateFields()) {
					TypeOfRoom selectedType = (TypeOfRoom) cbTipSobe.getSelectedItem();
					String bedLayout = (String) cbRasporedKreveta.getSelectedItem();
					Date checkInDate = dcCheckIn.getDate();
					Date checkOutDate = dcCheckOut.getDate();
					List<String> selectedServices = listAdditionalServices.getSelectedValuesList();
					List<AdditionalServices> additionalServices = manager.getAdditionalServicesMan()
							.getAdditionalServicesFromNames(selectedServices);

					LocalDate localCheckInDate = checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					LocalDate localCheckOutDate = checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					RoomType roomType = manager.getRoomTypesMan().getRoomTypeFromTypeAndBeds(selectedType, bedLayout);

					if (editR != null) {
						editR.setRoomType(roomType);
						editR.setStartDate(localCheckInDate);
						editR.setEndDate(localCheckOutDate);
						editR.setAdditionalServices(additionalServices);
						manager.getReservationsMan().changeReservation(editR.getId(), editR.getRoomType(),
								editR.getAdditionalServices(), editR.getStatus(), editR.getRoom());
						manager.calculatePrice(editR.getId());
					} else {
						Reservation newReservation = new Reservation(roomType, localCheckInDate, localCheckOutDate,
								additionalServices, ReservationStatus.WAITING);
						newReservation.setGuest(guest.getId(), manager);
						manager.getReservationsMan().addReservation(newReservation);
						manager.calculatePrice(newReservation.getId());
					}
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
		if (cbTipSobe.getSelectedItem() == null || cbRasporedKreveta.getSelectedItem() == null
				|| dcCheckIn.getDate() == null || dcCheckOut.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Morate izabrati tip sobe.", "Greška", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		try {
			AddEditReservationDialog dialog = new AddEditReservationDialog(new JFrame(), 0, "");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}