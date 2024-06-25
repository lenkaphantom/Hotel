package view.search;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controler.ReservationControler;
import controler.RoomTypeControler;
import enumeracije.RoomSpecs;
import manage.ManageHotel;
import net.miginfocom.swing.MigLayout;
import view.addedit.AddEditReservationDialog;

public class RoomSearchDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();
	private JFrame parent;
	private ReservationControler controler;
	private JList<String> listRoomSpec;
	private JButton btnSearch;

	public RoomSearchDialog(JFrame parent, ReservationControler controler) {
		super(parent, true);
		this.parent = parent;
		this.controler = controler;
		setTitle("Pretraga soba");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		pack();
	}

	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][grow]", "[]10[]20[]");
		setLayout(layout);

		JLabel lblRoomSpecs = new JLabel("Specifikacije sobe");
		add(lblRoomSpecs);

		DefaultListModel<String> roomSpecModel = new DefaultListModel<>();
		for (RoomSpecs spec : RoomSpecs.values()) {
			roomSpecModel.addElement(spec.toString());
		}
		listRoomSpec = new JList<>(roomSpecModel);
		listRoomSpec.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollPaneRoomSpec = new JScrollPane(listRoomSpec);
		add(scrollPaneRoomSpec, "span, growx");

		btnSearch = new JButton("Pretraži");
		add(btnSearch, "span, growx");

		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchRooms();
			}
		});
	}

	private void searchRooms() {
		List<String> selectedSpecs = listRoomSpec.getSelectedValuesList();
		RoomTypeControler roomTypeControler = new RoomTypeControler(selectedSpecs);

		if (roomTypeControler.getRoomTypes().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nema soba sa izabranim specifikacijama", "Greška",
					JOptionPane.ERROR_MESSAGE);
			dispose();
			return;
		} else {
			dispose();
			AddEditReservationDialog dialog = new AddEditReservationDialog(this.parent, 0, this.controler,
					roomTypeControler);
			dialog.setVisible(true);
		}
	}

	public static void main(String[] args) {
		try {
			RoomSearchDialog dialog = new RoomSearchDialog(new JFrame(), null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
