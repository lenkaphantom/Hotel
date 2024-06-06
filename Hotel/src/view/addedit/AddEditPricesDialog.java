package view.addedit;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import entity.AdditionalServices;
import entity.Prices;
import entity.RoomType;
import manage.ManageHotel;
import model.AdditionalServicesModel;
import model.RoomTypeModel;
import net.miginfocom.swing.MigLayout;
import view.AdministratorFrame;

public class AddEditPricesDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();
	private Prices editPrices;
	private JFrame parent;

	private JTable servicesTable;
	private JTable roomTypesTable;
	private JTextField txtServicePrice;
	private JTextField txtRoomTypePrice;
	private JTextField txtStartDate;
	private JTextField txtEndDate;

	public AddEditPricesDialog(JFrame parent, int id) {
		super(parent, true);
		this.parent = parent;
		if (id != 0) {
			setTitle("Izmena cenovnika");
			this.editPrices = manager.getPricesMan().getPrices().get(id);
		} else {
			setTitle("Dodavanje cenovnika");
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		pack();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		getContentPane().add(mainPanel);

		JPanel centerPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		JPanel servicesPanel = new JPanel(new BorderLayout());
		centerPanel.add(servicesPanel);

		servicesTable = new JTable(new AdditionalServicesModel());
		servicesPanel.add(new JScrollPane(servicesTable), BorderLayout.CENTER);

		JPanel servicePricePanel = new JPanel(new BorderLayout());
		servicesPanel.add(servicePricePanel, BorderLayout.SOUTH);
		JLabel lblServicePrice = new JLabel("Cena usluge:");
		servicePricePanel.add(lblServicePrice, BorderLayout.WEST);
		txtServicePrice = new JTextField(10);
		servicePricePanel.add(txtServicePrice, BorderLayout.CENTER);

		JPanel roomTypesPanel = new JPanel(new BorderLayout());
		centerPanel.add(roomTypesPanel);

		roomTypesTable = new JTable(new RoomTypeModel());
		roomTypesPanel.add(new JScrollPane(roomTypesTable), BorderLayout.CENTER);

		JPanel roomTypePricePanel = new JPanel(new BorderLayout());
		roomTypesPanel.add(roomTypePricePanel, BorderLayout.SOUTH);
		JLabel lblRoomTypePrice = new JLabel("Cena sobe:");
		roomTypePricePanel.add(lblRoomTypePrice, BorderLayout.WEST);
		txtRoomTypePrice = new JTextField(10);
		roomTypePricePanel.add(txtRoomTypePrice, BorderLayout.CENTER);

		JPanel datePanel = new JPanel(new MigLayout());
		mainPanel.add(datePanel, BorderLayout.NORTH);
		datePanel.add(new JLabel("Datum početka:"));
		txtStartDate = new JTextField(10);
		datePanel.add(txtStartDate, "wrap");
		datePanel.add(new JLabel("Datum kraja:"));
		txtEndDate = new JTextField(10);
		datePanel.add(txtEndDate, "wrap");

		JPanel buttonPanel = new JPanel();
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");

		buttonPanel.add(btnOk);
		buttonPanel.add(btnCancel);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateFields()) {
					savePrices();
					((AdministratorFrame) parent).refreshPriceTable();
					AddEditPricesDialog.this.dispose();
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditPricesDialog.this.dispose();
			}
		});

		if (editPrices != null) {
			fillFields();
		}
	}

	private boolean validateFields() {
		if (txtStartDate.getText().trim().isEmpty() || txtEndDate.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Morate uneti datume.", "Greška", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		try {
			LocalDate.parse(txtStartDate.getText().trim());
			LocalDate.parse(txtEndDate.getText().trim());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Datum mora biti u formatu yyyy-MM-dd.", "Greška",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (servicesTable.getSelectedRow() == -1 || txtServicePrice.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Morate uneti cenu za odabranu uslugu.", "Greška",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (roomTypesTable.getSelectedRow() == -1 || txtRoomTypePrice.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Morate uneti cenu za odabrani tip sobe.", "Greška",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		try {
			Double.parseDouble(txtServicePrice.getText().trim());
			Double.parseDouble(txtRoomTypePrice.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Cena mora biti broj.", "Greška", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		return true;
	}

	private void savePrices() {
		LocalDate startDate = LocalDate.parse(txtStartDate.getText().trim());
		LocalDate endDate = LocalDate.parse(txtEndDate.getText().trim());

		Map<RoomType, Double> pricePerRoom = new HashMap<>();
		Map<AdditionalServices, Double> pricePerService = new HashMap<>();

		RoomType selectedRoomType = manager.getRoomTypesMan().getRoomTypes().get(roomTypesTable.getSelectedRow() + 1);
		Double roomPrice = Double.parseDouble(txtRoomTypePrice.getText().trim());
		pricePerRoom.put(selectedRoomType, roomPrice);

		AdditionalServices selectedService = manager.getAdditionalServicesMan().getAdditionalServices()
				.get(servicesTable.getSelectedRow() + 1);
		Double servicePrice = Double.parseDouble(txtServicePrice.getText().trim());
		pricePerService.put(selectedService, servicePrice);

		if (editPrices != null) {
			editPrices.setStartDate(startDate);
			editPrices.setEndDate(endDate);
			editPrices.setPricePerRoom(pricePerRoom);
			editPrices.setPricePerService(pricePerService);
		} else {
			manager.getPricesMan().addPrices(pricePerRoom, pricePerService, startDate, endDate);
		}

		manager.getPricesMan().writePrices("data/prices.csv");
	}

	private void fillFields() {
		txtStartDate.setText(editPrices.getStartDate().toString());
		txtEndDate.setText(editPrices.getEndDate().toString());
	}
}
