package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import com.toedter.calendar.JDateChooser;

import controler.ReportsControler;
import controler.ReservationControler;
import controler.RoomControler;
import entity.Prices;
import entity.Reservation;
import entity.Room;
import manage.ManageHotel;
import model.AdditionalServicesModel;
import model.EmployeeModel;
import model.GuestModel;
import model.ReservationModel;
import model.RoomModel;
import model.RoomPriceModel;
import model.RoomTypeModel;
import model.ServicePriceModel;
import net.miginfocom.swing.MigLayout;
import view.addedit.AddEditEmployeeDialog;
import view.addedit.AddEditGuestDialog;
import view.addedit.AddEditPricesDialog;
import view.addedit.AddEditRoomDialog;
import view.addedit.AddEditRoomTypeDialog;
import view.addedit.AddEditServicesDialog;
import view.cellrenderer.ServicesCellRenderer;
import view.popup.AdditionalServicesPopup;
import view.popup.OccupiedDatesPopup;
import view.popup.RoomSpecsPopup;

public class AdministratorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();
	private ReservationControler controler;
	private RoomControler roomControler;

	private static final Color BACKGROUND_COLOR = new Color(214, 204, 194);
	private static final Color FOREGROUND_COLOR = new Color(102, 0, 34);

	private JPanel contentPane;

	private JTable employeeTable;
	private JTable guestTable;
	private JTable roomTypeTable;
	private JTable reservationTable;
	private JTable roomTable;
	private JTable additionalServiceTable;
	private JTable roomPriceTable;
	private JTable servicePriceTable;

	private JTextField tfSearchEmployee;
	private JTextField tfSearchGuest;
	private JTextField tfSearchRoomType;
	private JTextField tfSearchReservation;
	private JTextField tfSearchRoom;
	private JTextField tfSearchAdditionalService;
	private JTextField tfSearchPrice;

	private TableRowSorter<AbstractTableModel> employeeTableSorter;
	private TableRowSorter<AbstractTableModel> guestTableSorter;
	private TableRowSorter<AbstractTableModel> roomTypeTableSorter;
	private TableRowSorter<AbstractTableModel> reservationTableSorter;
	private TableRowSorter<AbstractTableModel> roomTableSorter;
	private TableRowSorter<AbstractTableModel> additionalServiceTableSorter;
	private TableRowSorter<AbstractTableModel> priceTableSorter;

	private JButton addBtnEmployee = new JButton();
	private JButton editBtnEmployee = new JButton();
	private JButton removeBtnEmployee = new JButton();

	private JButton addBtnGuest = new JButton();
	private JButton editBtnGuest = new JButton();
	private JButton removeBtnGuest = new JButton();

	private JButton addBtnRoomType = new JButton();
	private JButton editBtnRoomType = new JButton();
	private JButton removeBtnRoomType = new JButton();

	private JButton addBtnRoom = new JButton();
	private JButton editBtnRoom = new JButton();
	private JButton removeBtnRoom = new JButton();

	private JButton addBtnAdditionalService = new JButton();
	private JButton editBtnAdditionalService = new JButton();
	private JButton removeBtnAdditionalService = new JButton();

	private JButton addBtnPrice = new JButton();
	
	private JButton generateRevenueExpenseReportBtn = new JButton();
	private JButton generateHouseKeepingReportBtn = new JButton();
	private JButton generateReservationReportBtn = new JButton();
	private JButton generateRoomReportBtn = new JButton();
	
	private JDateChooser startDateRevenue = new JDateChooser();
	private JDateChooser endDateRevenue = new JDateChooser();
	private JDateChooser startDateHouseKeeping = new JDateChooser();
	private JDateChooser endDateHouseKeeping = new JDateChooser();
	private JDateChooser startDateReservation = new JDateChooser();
	private JDateChooser endDateReservation = new JDateChooser();
	private JDateChooser startDateRoom = new JDateChooser();
	private JDateChooser endDateRoom = new JDateChooser();
	
	private JLabel revenueExpenseReportLabel = new JLabel();
	private JTextArea houseKeepingReportLabel = new JTextArea();
	private JLabel reservationReportLabel = new JLabel();
	private JLabel reservationConfirmedReportLabel = new JLabel();
	private JLabel reservationCancelledReportLabel = new JLabel();
	private JLabel reservationDeniedReportLabel = new JLabel();
	private JTextArea roomReportLabel = new JTextArea();

	Image img = new ImageIcon("img/add.png").getImage();
	Image newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);

	Image img1 = new ImageIcon("img/edit.png").getImage();
	Image newimg1 = img1.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);

	Image img2 = new ImageIcon("img/remove.png").getImage();
	Image newimg2 = img2.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministratorFrame frame = new AdministratorFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdministratorFrame() {
		setBackground(BACKGROUND_COLOR);
		setForeground(FOREGROUND_COLOR);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("img/hotel.png").getImage());
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[5px][grow]", "[5px,grow]"));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "cell 1 0,grow");

		addBtnEmployee.setIcon(new ImageIcon(newimg));
		editBtnEmployee.setIcon(new ImageIcon(newimg1));
		removeBtnEmployee.setIcon(new ImageIcon(newimg2));

		employeeTable = new JTable(new EmployeeModel());
		employeeTableSorter = new TableRowSorter<>((AbstractTableModel) employeeTable.getModel());
		employeeTable.setRowSorter(employeeTableSorter);

		JPanel employeePanel = createPanelWithTable(employeeTable, addBtnEmployee, editBtnEmployee, removeBtnEmployee,
				true);
		tabbedPane.addTab("Zaposleni", null, employeePanel, null);

		addBtnGuest.setIcon(new ImageIcon(newimg));
		editBtnGuest.setIcon(new ImageIcon(newimg1));
		removeBtnGuest.setIcon(new ImageIcon(newimg2));

		guestTable = new JTable(new GuestModel());
		guestTableSorter = new TableRowSorter<>((AbstractTableModel) guestTable.getModel());
		guestTable.setRowSorter(guestTableSorter);

		JPanel guestPanel = createPanelWithTable(guestTable, addBtnGuest, editBtnGuest, removeBtnGuest, false);
		tabbedPane.addTab("Gosti", null, guestPanel, null);

		addBtnRoomType.setIcon(new ImageIcon(newimg));
		editBtnRoomType.setIcon(new ImageIcon(newimg1));
		removeBtnRoomType.setIcon(new ImageIcon(newimg2));

		roomTypeTable = new JTable(new RoomTypeModel());
		roomTypeTableSorter = new TableRowSorter<>((AbstractTableModel) roomTypeTable.getModel());
		roomTypeTable.setRowSorter(roomTypeTableSorter);

		JPanel roomTypePanel = createRoomTypePanel(roomTypeTable, addBtnRoomType, editBtnRoomType, removeBtnRoomType);
		tabbedPane.addTab("Tipovi soba", null, roomTypePanel, null);

		ReservationModel reservationModel = new ReservationModel("");
		controler = reservationModel.getControler();

		reservationTable = new JTable(reservationModel);
		reservationTableSorter = new TableRowSorter<>((AbstractTableModel) reservationTable.getModel());
		reservationTable.setRowSorter(reservationTableSorter);
		
		reservationTable.getColumnModel().getColumn(4).setCellRenderer(new ServicesCellRenderer());

		reservationTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = reservationTable.rowAtPoint(e.getPoint());
				int column = reservationTable.columnAtPoint(e.getPoint());
				int modelRow = reservationTable.convertRowIndexToModel(row);

				if (column == 4) {
					Reservation reservation = manager.getReservationsMan().getReservations().get(modelRow + 1);
					new AdditionalServicesPopup(AdministratorFrame.this,
							manager.getAdditionalServicesList(reservation.getId())).setVisible(true);
				}
			}
		});

		JPanel reservationPanel = createReservationPanel(reservationTable);
		tabbedPane.addTab("Rezervacije", null, reservationPanel, null);

		addBtnRoom.setIcon(new ImageIcon(newimg));
		editBtnRoom.setIcon(new ImageIcon(newimg1));
		removeBtnRoom.setIcon(new ImageIcon(newimg2));

		RoomModel roomModel = new RoomModel("", null);
		roomControler = roomModel.getControler();
		roomTable = new JTable(roomModel);
		roomTableSorter = new TableRowSorter<>((AbstractTableModel) roomTable.getModel());
		roomTable.setRowSorter(roomTableSorter);

		roomTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = roomTable.rowAtPoint(e.getPoint());
				int column = roomTable.columnAtPoint(e.getPoint());
				
				int modelRow = roomTable.convertRowIndexToModel(row);

				if (column == 5) {
					List<LocalDate> occupiedDates = manager.getRoomsMan().getOccupiedDatesList(modelRow + 1);
					new OccupiedDatesPopup(AdministratorFrame.this, occupiedDates).setVisible(true);
				}

				if (column == 6) {
					Room room = manager.getRoomsMan().getRooms().get(row + 1);
					new RoomSpecsPopup(AdministratorFrame.this, room.getRoomSpecs()).setVisible(true);
				}
			}
		});

		JPanel roomPanel = createRoomPanel(roomTable, addBtnRoom, editBtnRoom, removeBtnRoom);
		tabbedPane.addTab("Sobe", null, roomPanel, null);

		addBtnAdditionalService.setIcon(new ImageIcon(newimg));
		editBtnAdditionalService.setIcon(new ImageIcon(newimg1));
		removeBtnAdditionalService.setIcon(new ImageIcon(newimg2));

		additionalServiceTable = new JTable(new AdditionalServicesModel());
		additionalServiceTableSorter = new TableRowSorter<>((AbstractTableModel) additionalServiceTable.getModel());
		additionalServiceTable.setRowSorter(additionalServiceTableSorter);

		JPanel additionalServicePanel = createServicesPanel(additionalServiceTable, addBtnAdditionalService,
				editBtnAdditionalService, removeBtnAdditionalService);
		tabbedPane.addTab("Dodatne usluge", null, additionalServicePanel, null);

		addBtnPrice.setIcon(new ImageIcon(newimg));

		JPanel pricePanel = createPricePanel();
		tabbedPane.addTab("Cenovnik", null, pricePanel, null);
		
		JPanel chartsPanel = createChartsPanel();
		tabbedPane.addTab("Grafikoni", null, chartsPanel, null);
		
		JPanel reportsPanel = createReportsPanel();
		tabbedPane.addTab("Izvestaji", null, reportsPanel, null);

		initActions(tabbedPane);
	}

	private JPanel createPanelWithTable(JTable table, JButton addButton, JButton editButton, JButton removeButton,
			boolean isEmployee) {
		JPanel panel = new JPanel(new MigLayout("", "[grow]", "[][][grow]"));

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.add(addButton);
		toolBar.add(editButton);
		toolBar.add(removeButton);
		panel.add(toolBar, "flowx,cell 0 0");

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setForeground(FOREGROUND_COLOR);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane, "cell 0 1 1 2,grow");

		int[] columnWidths = isEmployee ? new int[] { 50, 100, 100, 50, 150, 100, 150, 150, 100, 100, 100, 100, 150 }
				: new int[] { 50, 100, 100, 50, 150, 100, 150, 150, 100 };
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
					if (isEmployee) {
						employeeTableSorter.setRowFilter(null);
					} else {
						guestTableSorter.setRowFilter(null);
					}
				} else {
					if (isEmployee) {
						employeeTableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text.trim()));
					} else {
						guestTableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text.trim()));
					}
				}
			}
		});

		if (isEmployee) {
			this.tfSearchEmployee = searchField;
		} else {
			this.tfSearchGuest = searchField;
		}
		return panel;
	}

	private JPanel createRoomTypePanel(JTable table, JButton addButton, JButton editButton, JButton removeButton) {
		JPanel panel = new JPanel(new MigLayout("", "[grow]", "[][][grow]"));

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.add(addButton);
		toolBar.add(editButton);
		toolBar.add(removeButton);
		panel.add(toolBar, "flowx,cell 0 0");

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setForeground(FOREGROUND_COLOR);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane, "cell 0 1 1 2,grow");

		int[] columnWidths = new int[] { 50, 150, 100 };
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
					roomTypeTableSorter.setRowFilter(null);
				} else {
					roomTypeTableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text.trim()));
				}
			}
		});

		this.tfSearchRoomType = searchField;
		return panel;
	}

	private JPanel createReservationPanel(JTable table) {
		JPanel panel = new JPanel(new MigLayout("", "[grow]", "[][][grow]"));

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		panel.add(toolBar, "flowx,cell 0 0");

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setForeground(FOREGROUND_COLOR);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane, "cell 0 1 1 2,grow");

		int[] columnWidths = new int[] { 50, 100, 100, 100, 150, 100, 100, 100, 100, 100, 100 };
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

	private JPanel createRoomPanel(JTable table, JButton addButton, JButton editButton, JButton removeButton) {
		JPanel panel = new JPanel(new MigLayout("", "[grow]", "[][][grow]"));

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.add(addButton);
		toolBar.add(editButton);
		toolBar.add(removeButton);
		panel.add(toolBar, "flowx,cell 0 0");

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setForeground(FOREGROUND_COLOR);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane, "cell 0 1 1 2,grow");

		int[] columnWidths = new int[] { 50, 100, 100, 100, 150, 100 };
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
					roomTableSorter.setRowFilter(null);
				} else {
					roomTableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text.trim()));
				}
			}
		});

		this.tfSearchRoom = searchField;

		return panel;
	}

	private JPanel createServicesPanel(JTable table, JButton addButton, JButton editButton, JButton removeButton) {
		JPanel panel = new JPanel(new MigLayout("", "[grow]", "[][][grow]"));

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.add(addButton);
		toolBar.add(editButton);
		toolBar.add(removeButton);
		panel.add(toolBar, "flowx,cell 0 0");

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setForeground(FOREGROUND_COLOR);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane, "cell 0 1 1 2,grow");

		int[] columnWidths = new int[] { 50, 150 };
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
					additionalServiceTableSorter.setRowFilter(null);
				} else {
					additionalServiceTableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text.trim()));
				}
			}
		});

		this.tfSearchAdditionalService = searchField;
		return panel;
	}

	private JPanel createPricePanel() {
		JPanel panel = new JPanel(new MigLayout("", "[grow]", "[][][grow]"));

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.add(addBtnPrice);
		panel.add(toolBar, "flowx,cell 0 0");

		JTabbedPane priceTabbedPane = new JTabbedPane();

		for (Prices prices : manager.getPricesMan().getPrices().values()) {
			int pricesId = prices.getId();

			JPanel roomPricePanel = new JPanel(new MigLayout("", "[grow]", "[][grow]"));
			roomPriceTable = new JTable(new RoomPriceModel(pricesId));
			JScrollPane roomPriceScrollPane = new JScrollPane(roomPriceTable);
			roomPricePanel.add(roomPriceScrollPane, "grow");

			JPanel servicePricePanel = new JPanel(new MigLayout("", "[grow]", "[][grow]"));
			servicePriceTable = new JTable(new ServicePriceModel(pricesId));
			JScrollPane servicePriceScrollPane = new JScrollPane(servicePriceTable);
			servicePricePanel.add(servicePriceScrollPane, "grow");

			JPanel combinedPanel = new JPanel(new MigLayout("", "[grow][grow]", "[][grow]"));
			combinedPanel.add(roomPricePanel, "grow");
			combinedPanel.add(servicePricePanel, "grow");

			priceTabbedPane.addTab("Cenovnik " + pricesId, combinedPanel);
			priceTabbedPane.setToolTipTextAt(priceTabbedPane.getTabCount() - 1, String.valueOf(pricesId));
		}

		panel.add(priceTabbedPane, "cell 0 1, grow");

		return panel;
	}
	
	private JPanel createChartsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[grow]", "[][][grow]"));

		JTabbedPane chartTabbedPane = new JTabbedPane();
		
		JPanel roomTypeChartPanel = new JPanel(new MigLayout("", "[grow]", "[][grow]"));
		
		JPanel housekeepingChartPanel = new JPanel(new MigLayout("", "[grow]", "[][grow]"));
		
		JPanel reservationStatusChartPanel = new JPanel(new MigLayout("", "[grow]", "[][grow]"));
		
		chartTabbedPane.addTab("Grafikon tipova soba", roomTypeChartPanel);
		chartTabbedPane.addTab("Grafikon odrzavanja", housekeepingChartPanel);
		chartTabbedPane.addTab("Grafikon statusa rezervacija", reservationStatusChartPanel);
		
		panel.add(chartTabbedPane, "cell 0 1, grow");
		return panel;
	}
	
	private JPanel createReportsPanel() {
		JPanel panel = new JPanel(new MigLayout("", "[grow]", "[][][grow]"));

		JTabbedPane reportTabbedPane = new JTabbedPane();
		
		JPanel revenueExpenseReportPanel = new JPanel(new MigLayout("", "[grow]", "[][grow]"));
		revenueExpenseReportPanel.setBackground(BACKGROUND_COLOR);
		generateRevenueExpenseReportBtn.setText("Generisi izvestaj");
		revenueExpenseReportPanel.add(new JLabel("Datum od: "), "cell 0 0");
		revenueExpenseReportPanel.add(startDateRevenue, "cell 1 0, width 200!");
		revenueExpenseReportPanel.add(new JLabel("Datum do: "), "cell 0 1");
		revenueExpenseReportPanel.add(endDateRevenue, "cell 1 1, width 200!");
		revenueExpenseReportPanel.add(generateRevenueExpenseReportBtn, "cell 0 2");
		revenueExpenseReportPanel.add(revenueExpenseReportLabel, "cell 0 3, grow");
		
		JPanel houseKeepingReportPanel = new JPanel(new MigLayout("", "[grow]", "[][grow]"));
		houseKeepingReportPanel.setBackground(BACKGROUND_COLOR);
		generateHouseKeepingReportBtn.setText("Generisi izvestaj");
		houseKeepingReportPanel.add(new JLabel("Datum od: "), "cell 0 0");
		houseKeepingReportPanel.add(startDateHouseKeeping, "cell 1 0, width 200!");
		houseKeepingReportPanel.add(new JLabel("Datum do: "), "cell 0 1");
		houseKeepingReportPanel.add(endDateHouseKeeping, "cell 1 1, width 200!");
		houseKeepingReportPanel.add(generateHouseKeepingReportBtn, "cell 0 2");
		houseKeepingReportPanel.add(houseKeepingReportLabel, "cell 0 3, grow");
		
		JPanel reservationReportPanel = new JPanel(new MigLayout("", "[grow]", "[][grow]"));
		reservationReportPanel.setBackground(BACKGROUND_COLOR);
		generateReservationReportBtn.setText("Generisi izvestaj");
		reservationReportPanel.add(new JLabel("Datum od: "), "cell 0 0");
		reservationReportPanel.add(startDateReservation, "cell 1 0, width 200!");
		reservationReportPanel.add(new JLabel("Datum do: "), "cell 0 1");
		reservationReportPanel.add(endDateReservation, "cell 1 1, width 200!");
		reservationReportPanel.add(generateReservationReportBtn, "cell 0 2");
		reservationReportPanel.add(reservationReportLabel, "cell 0 3");
		reservationReportPanel.add(reservationConfirmedReportLabel, "cell 0 4");
		reservationReportPanel.add(reservationCancelledReportLabel, "cell 0 5");
		reservationReportPanel.add(reservationDeniedReportLabel, "cell 0 6");
		
		JPanel roomReportPanel = new JPanel(new MigLayout("", "[grow]", "[][grow]"));
		roomReportPanel.setBackground(BACKGROUND_COLOR);
		generateRoomReportBtn.setText("Generisi izvestaj");
		roomReportPanel.add(new JLabel("Datum od: "), "cell 0 0");
		roomReportPanel.add(startDateRoom, "cell 1 0, width 200!");
		roomReportPanel.add(new JLabel("Datum do: "), "cell 0 1");
		roomReportPanel.add(endDateRoom, "cell 1 1, width 200!");
		roomReportPanel.add(generateRoomReportBtn, "cell 0 2");
		roomReportPanel.add(roomReportLabel, "cell 0 3, grow");
		
		reportTabbedPane.addTab("Izvestaj o prihodima i troskovima", revenueExpenseReportPanel);
		reportTabbedPane.addTab("Izvestaj o odrzavanju", houseKeepingReportPanel);
		reportTabbedPane.addTab("Izvestaj o rezervacijama", reservationReportPanel);
		reportTabbedPane.addTab("Izvestaj o sobama", roomReportPanel);

		panel.add(reportTabbedPane, "cell 0 1, grow");
		return panel;
	}

	private void setTableColumnWidths(JTable table, int[] columnWidths) {
		for (int i = 0; i < columnWidths.length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
		}
	}

	private void initActions(JTabbedPane tabbedPane) {
		addBtnEmployee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditEmployeeDialog addEditEmployeeDialog = new AddEditEmployeeDialog(AdministratorFrame.this, 0);
				addEditEmployeeDialog.setVisible(true);
				refreshEmployeeTable();
				manager.getEmployeesMan().writeEmployees("data/employees.csv");
			}
		});

		editBtnEmployee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = employeeTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int modelRow = employeeTable.convertRowIndexToModel(row);
					AddEditEmployeeDialog addEditEmployeeDialog = new AddEditEmployeeDialog(AdministratorFrame.this,
							modelRow + 1);
					addEditEmployeeDialog.setVisible(true);
					refreshEmployeeTable();
					manager.getEmployeesMan().writeEmployees("data/employees.csv");
				}
			}
		});

		removeBtnEmployee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = employeeTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int izbor = JOptionPane.showConfirmDialog(null,
							"Da li ste sigurni da zelite da obrisete zaposlenog?", "Brisanje zaposlenog",
							JOptionPane.YES_NO_OPTION);
					if (izbor == JOptionPane.YES_OPTION) {
						int modelRow = employeeTable.convertRowIndexToModel(row);
						manager.getEmployeesMan().removeEmployee(modelRow + 1);
						manager.getEmployeesMan().writeEmployees("data/employees.csv");
					}
					refreshEmployeeTable();
				}
			}
		});

		addBtnGuest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditGuestDialog addEditGuestDialog = new AddEditGuestDialog(AdministratorFrame.this, 0);
				addEditGuestDialog.setVisible(true);
				refreshGuestTable();
				manager.getGuestsMan().writeGuests("data/guests.csv");
			}
		});

		editBtnGuest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = guestTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int modelRow = guestTable.convertRowIndexToModel(row);
					AddEditGuestDialog addEditGuestDialog = new AddEditGuestDialog(AdministratorFrame.this,
							modelRow + 1);
					addEditGuestDialog.setVisible(true);
					refreshGuestTable();
					manager.getGuestsMan().writeGuests("data/guests.csv");
				}
			}
		});

		removeBtnGuest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = guestTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete gosta?",
							"Brisanje gosta", JOptionPane.YES_NO_OPTION);
					if (izbor == JOptionPane.YES_OPTION) {
						int modelRow = guestTable.convertRowIndexToModel(row);
						manager.getGuestsMan().removeGuest(modelRow + 1);
						manager.getGuestsMan().writeGuests("data/guests.csv");
					}
					refreshGuestTable();
				}
			}
		});

		addBtnRoomType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditRoomTypeDialog addEditRoomTypeDialog = new AddEditRoomTypeDialog(AdministratorFrame.this, 0);
				addEditRoomTypeDialog.setVisible(true);
				refreshRoomTypeTable();
				refreshPriceTable(tabbedPane);
				manager.getRoomTypesMan().writeRoomTypes("data/room_types.csv");
			}
		});

		editBtnRoomType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = roomTypeTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int modelRow = roomTypeTable.convertRowIndexToModel(row);
					AddEditRoomTypeDialog addEditRoomTypeDialog = new AddEditRoomTypeDialog(AdministratorFrame.this,
							modelRow + 1);
					addEditRoomTypeDialog.setVisible(true);
					refreshRoomTypeTable();
					refreshPriceTable(tabbedPane);
					refreshRoomsTable();
					manager.getRoomTypesMan().writeRoomTypes("data/room_types.csv");
				}
			}
		});

		removeBtnRoomType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = roomTypeTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete tip sobe?",
							"Brisanje tipa sobe", JOptionPane.YES_NO_OPTION);
					if (izbor == JOptionPane.YES_OPTION) {
						int modelRow = roomTypeTable.convertRowIndexToModel(row);
						if (!manager.checkRoomType(modelRow + 1))
							return;
						manager.getRoomTypesMan().removeRoomType(modelRow + 1);
						manager.getPricesMan().removeRoomTypePrices(modelRow + 1);
						manager.getRoomTypesMan().writeRoomTypes("data/room_types.csv");
					}
					refreshRoomTypeTable();
				}
			}
		});

		addBtnRoom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditRoomDialog addEditRoomDialog = new AddEditRoomDialog(AdministratorFrame.this, 0, roomControler);
				addEditRoomDialog.setVisible(true);
				refreshRoomsTable();
				manager.getRoomsMan().writeRooms("data/rooms.csv");
			}
		});

		editBtnRoom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = roomTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int modelRow = roomTable.convertRowIndexToModel(row);
					AddEditRoomDialog addEditRoomDialog = new AddEditRoomDialog(AdministratorFrame.this, modelRow + 1,
							roomControler);
					addEditRoomDialog.setVisible(true);
					refreshRoomsTable();
					manager.getRoomsMan().writeRooms("data/rooms.csv");
				}
			}
		});

		removeBtnRoom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = roomTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete sobu?",
							"Brisanje sobe", JOptionPane.YES_NO_OPTION);
					if (izbor == JOptionPane.YES_OPTION) {
						int modelRow = roomTable.convertRowIndexToModel(row);
						manager.getRoomsMan().removeRoom(modelRow + 1);
						manager.getRoomsMan().writeRooms("data/rooms.csv");
					}
					refreshRoomsTable();
				}
			}
		});

		addBtnAdditionalService.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditServicesDialog addEditServicesDialog = new AddEditServicesDialog(AdministratorFrame.this, 0);
				addEditServicesDialog.setVisible(true);
				refreshAdditionalServicesTable();
				refreshPriceTable(tabbedPane);
				manager.getAdditionalServicesMan().writeAdditionalServices("data/additional_services.csv");
			}
		});

		editBtnAdditionalService.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = additionalServiceTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int modelRow = additionalServiceTable.convertRowIndexToModel(row);
					AddEditServicesDialog addEditServicesDialog = new AddEditServicesDialog(AdministratorFrame.this,
							modelRow + 1);
					addEditServicesDialog.setVisible(true);
					refreshAdditionalServicesTable();
					refreshPriceTable(tabbedPane);
					manager.getAdditionalServicesMan().writeAdditionalServices("data/additional_services.csv");
				}
			}
		});

		removeBtnAdditionalService.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = additionalServiceTable.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					int izbor = JOptionPane.showConfirmDialog(null,
							"Da li ste sigurni da zelite da obrisete dodatnu uslugu?", "Brisanje dodatne usluge",
							JOptionPane.YES_NO_OPTION);
					if (izbor == JOptionPane.YES_OPTION) {
						int modelRow = additionalServiceTable.convertRowIndexToModel(row);
						manager.getAdditionalServicesMan().removeAdditionalService(modelRow + 1);
						manager.getPricesMan().removeServicePrices(modelRow + 1);
						manager.getAdditionalServicesMan().writeAdditionalServices("data/additional_services.csv");
					}
					refreshAdditionalServicesTable();
					refreshPriceTable(tabbedPane);
				}
			}
		});

		addBtnPrice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditPricesDialog addEditPricesDialog = new AddEditPricesDialog(AdministratorFrame.this, 0);
				addEditPricesDialog.setVisible(true);
				refreshPriceTable(tabbedPane);
				manager.getPricesMan().writePrices("data/prices.csv");
			}
		});
		
		generateRevenueExpenseReportBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date startDateD = startDateRevenue.getDate();
				LocalDate startDate = startDateD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				Date endDateD = endDateRevenue.getDate();
				LocalDate endDate = endDateD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				double revenue = ReportsControler.getRevenue(startDate, endDate);
				double expense = ReportsControler.getExpenses(startDate, endDate);
				
				revenueExpenseReportLabel.setText("Prihodi: " + revenue * 117 + " Din, Rashodi: " + expense + " Din");
			}
		});
		
		generateHouseKeepingReportBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date startDateD = startDateHouseKeeping.getDate();
				LocalDate startDate = startDateD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				Date endDateD = endDateHouseKeeping.getDate();
				LocalDate endDate = endDateD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				houseKeepingReportLabel.setText(ReportsControler.getHouseKeeping(startDate, endDate));
			}
		});
		
		generateReservationReportBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date startDateD = startDateReservation.getDate();
				LocalDate startDate = startDateD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				Date endDateD = endDateReservation.getDate();
				LocalDate endDate = endDateD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				int processed = ReportsControler.getNumOfProcessedReservations(startDate, endDate);
				int confirmed = ReportsControler.getNumOfConfirmedReservations(startDate, endDate);
				int cancelled = ReportsControler.getNumOfCancelledReservations(startDate, endDate);
				int denied = ReportsControler.getNumOfDeniedReservations(startDate, endDate);
				
				reservationReportLabel.setText("Obradjene rezervacije: " + processed);
				reservationConfirmedReportLabel.setText("Potvrdjene rezervacije: " + confirmed);
				reservationCancelledReportLabel.setText("Otkazane rezervacije: " + cancelled);
				reservationDeniedReportLabel.setText("Odbijene rezervacije: " + denied);
			}
		});
		
		generateRoomReportBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date startDateD = startDateRoom.getDate();
				LocalDate startDate = startDateD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				Date endDateD = endDateRoom.getDate();
				LocalDate endDate = endDateD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				roomReportLabel.setText(ReportsControler.getRoomsReport(startDate, endDate));
			}
		});
	}

	public void refreshEmployeeTable() {
		((AbstractTableModel) employeeTable.getModel()).fireTableDataChanged();
	}

	public void refreshGuestTable() {
		((AbstractTableModel) guestTable.getModel()).fireTableDataChanged();
	}

	public void refreshRoomTypeTable() {
		((AbstractTableModel) roomTypeTable.getModel()).fireTableDataChanged();
	}

	public void refreshRoomsTable() {
		((AbstractTableModel) roomTable.getModel()).fireTableDataChanged();
	}

	public void refreshAdditionalServicesTable() {
		((AbstractTableModel) additionalServiceTable.getModel()).fireTableDataChanged();
	}

	public void refreshPriceTable(JTabbedPane tabbedPane) {
		tabbedPane.removeTabAt(tabbedPane.getTabCount() - 3);
		tabbedPane.removeTabAt(tabbedPane.getTabCount() - 2);
		tabbedPane.removeTabAt(tabbedPane.getTabCount() - 1);
		
		JPanel pricePanel = createPricePanel();
		tabbedPane.addTab("Cenovnik", null, pricePanel, null);
		
		JPanel chartsPanel = createChartsPanel();
		tabbedPane.addTab("Grafikoni", null, chartsPanel, null);
		
		JPanel reportsPanel = createReportsPanel();
		tabbedPane.addTab("Izvestaji", null, reportsPanel, null);
	}
}
