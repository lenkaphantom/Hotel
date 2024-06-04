package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import enumeracije.ReservationStatus;
import manage.ManageHotel;
import model.ReservationModel;
import net.miginfocom.swing.MigLayout;
import view.addedit.AddEditReservationDialog;
import view.comboBoxRenderer.AdditionalServicesCellRenderer;
import view.comboBoxRenderer.AdditionalServicesEditor;

public class ReceptionistFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();

	private static final Color BACKGROUND_COLOR = new Color(214, 204, 194);
	private static final Color FOREGROUND_COLOR = new Color(102, 0, 34);

	private JPanel contentPane;
	private JTable reservationTable;
	private JTextField tfSearchReservation;
	private TableRowSorter<AbstractTableModel> reservationTableSorter;
	private JButton addBtnReservation = new JButton();
	private JButton removeBtnReservation = new JButton();

	Image img = new ImageIcon("img/add.png").getImage();
	Image newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);

	Image img2 = new ImageIcon("img/remove.png").getImage();
	Image newimg2 = img2.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReceptionistFrame frame = new ReceptionistFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ReceptionistFrame() {
		setBackground(BACKGROUND_COLOR);
		setForeground(FOREGROUND_COLOR);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("img/hotel.png").getImage());
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));

		addBtnReservation.setIcon(new ImageIcon(newimg));
		removeBtnReservation.setIcon(new ImageIcon(newimg2));

		List<String> additionalServicesList = manager.getAdditionalServicesMan().getAdditionalServicesList();
		ReservationModel reservationModel = new ReservationModel("");

		reservationTable = new JTable(reservationModel);
		reservationTableSorter = new TableRowSorter<>((AbstractTableModel) reservationTable.getModel());
		reservationTable.setRowSorter(reservationTableSorter);

		TableCellRenderer additionalServicesRenderer = new AdditionalServicesCellRenderer();
		TableCellEditor additionalServicesEditor = new AdditionalServicesEditor(
				additionalServicesList.toArray(new String[0]));
		reservationTable.getColumnModel().getColumn(4).setCellRenderer(additionalServicesRenderer);
		reservationTable.getColumnModel().getColumn(4).setCellEditor(additionalServicesEditor);

		reservationTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = reservationTable.rowAtPoint(e.getPoint());
				int column = reservationTable.columnAtPoint(e.getPoint());

				if (column == 4) {
					reservationTable.editCellAt(row, column);
				}
			}
		});

		JPanel reservationPanel = createPanelWithTable(reservationTable, addBtnReservation, removeBtnReservation);
		contentPane.add(reservationPanel, "cell 0 0,grow");

		initActions();
	}

	private JPanel createPanelWithTable(JTable table, JButton addButton, JButton removeButton) {
		JPanel panel = new JPanel(new MigLayout("", "[grow]", "[][][grow]"));

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.add(addButton);
		toolBar.add(removeButton);
		panel.add(toolBar, "flowx,cell 0 0");

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setForeground(FOREGROUND_COLOR);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane, "cell 0 1 1 2,grow");

		int[] columnWidths = new int[] { 50, 100, 100, 100, 150, 100, 100, 100, 100 };
		setTableColumnWidths(table, columnWidths);

		JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		searchPanel.setBackground(BACKGROUND_COLOR);
		JTextField searchField = new JTextField(20);
		searchPanel.add(new JLabel("Pretraga:"));
		searchPanel.add(searchField);

		panel.add(searchPanel, "cell 0 0, grow");

		searchField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filterTable();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterTable();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filterTable();
			}

			private void filterTable() {
				String text = searchField.getText();
				if (text.trim().length() == 0) {
					reservationTableSorter.setRowFilter(null);
				} else {
					reservationTableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text.trim()));
				}
			}
		});

		this.tfSearchReservation = searchField;
		return panel;
	}

	private void setTableColumnWidths(JTable table, int[] columnWidths) {
		for (int i = 0; i < columnWidths.length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
		}
	}

	private void initActions() {
		addBtnReservation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditReservationDialog addEditReservationDialog = new AddEditReservationDialog(ReceptionistFrame.this,
						0, "");
				addEditReservationDialog.setVisible(true);
				refreshReservationTable();
				manager.getReservationsMan().writeReservations("data/reservations.csv");
			}
		});

		removeBtnReservation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = reservationTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int izbor = JOptionPane.showConfirmDialog(null,
							"Da li ste sigurni da zelite da otkazete rezervaciju?", "Otkazivanje rezervacije",
							JOptionPane.YES_NO_OPTION);
					if (izbor == JOptionPane.YES_OPTION) {
						manager.getReservationsMan().getReservations().get(reservationTable.getValueAt(row, 0))
								.setStatus(ReservationStatus.CANCELLED);
						refreshReservationTable();
						manager.getReservationsMan().writeReservations("data/reservations.csv");
					}
				}
			}
		});
	}

	public void refreshReservationTable() {
		((AbstractTableModel) reservationTable.getModel()).fireTableDataChanged();
	}
}
