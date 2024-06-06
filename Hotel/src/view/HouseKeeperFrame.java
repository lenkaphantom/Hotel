package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import entity.Room;
import enumeracije.RoomStatus;
import manage.ManageHotel;
import model.RoomModel;
import net.miginfocom.swing.MigLayout;
import view.comboBoxRenderer.RoomCellEditor;
import view.comboBoxRenderer.RoomCellRenderer;

public class HouseKeeperFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();

	private static final Color BACKGROUND_COLOR = new Color(214, 204, 194);
	private static final Color FOREGROUND_COLOR = new Color(102, 0, 34);

	private JPanel contentPane;
	private JTable roomTable;
	private JTextField tfSearchRoom;
	private TableRowSorter<AbstractTableModel> roomTableSorter;
	private JButton cleanBtn;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HouseKeeperFrame frame = new HouseKeeperFrame("", null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HouseKeeperFrame(String username, LocalDate date) {
		setBackground(BACKGROUND_COLOR);
		setForeground(FOREGROUND_COLOR);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("img/hotel.png").getImage());
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[5px][grow]", "[5px,grow]"));
		
		cleanBtn = new JButton("Očisti");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, "cell 1 0,grow");

		roomTable = new JTable(new RoomModel(username, date));
		roomTableSorter = new TableRowSorter<>((AbstractTableModel) roomTable.getModel());
		roomTable.setRowSorter(roomTableSorter);

		roomTable.getColumnModel().getColumn(5).setCellRenderer(new RoomCellRenderer());
		roomTable.getColumnModel().getColumn(5).setCellEditor(new RoomCellEditor());

		roomTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = roomTable.rowAtPoint(e.getPoint());
				int column = roomTable.columnAtPoint(e.getPoint());

				if (column == 5) {
					roomTable.editCellAt(row, column);
				}
			}
		});

		JPanel roomPanel = createRoomPanel(roomTable);
		tabbedPane.addTab("Sobe", null, roomPanel, null);

		initActions();
	}

	

	private JPanel createRoomPanel(JTable table) {
		JPanel panel = new JPanel(new MigLayout("", "[grow]", "[][][grow]"));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.add(cleanBtn);
		panel.add(toolBar, "flowx,cell 0 0");

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setForeground(FOREGROUND_COLOR);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.add(scrollPane, "cell 0 1 1 2,grow");

		int[] columnWidths = new int[] { 50, 100, 100, 100, 150, 200 };
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

	

	private void setTableColumnWidths(JTable table, int[] columnWidths) {
		for (int i = 0; i < columnWidths.length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
		}
	}

	private void initActions() {
		cleanBtn.addActionListener(e -> {
			int row = roomTable.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(null, "Morate selektovati sobu koju želite da očistite.", "Greška",
						JOptionPane.ERROR_MESSAGE);
			} else {
				Room room = manager.getRoomsMan().getRooms().get(roomTable.getValueAt(row, 0));
				if (room.getRoomStatus().equals(RoomStatus.FREE))
					JOptionPane.showMessageDialog(null, "Soba je već čista.", "Greška", JOptionPane.ERROR_MESSAGE);
				else {
					room.setRoomStatus(RoomStatus.FREE);
					manager.getRoomsMan().writeRooms("data/rooms.csv");
					refreshRoomsTable();
				}
			}
		});
	}
	
	public void refreshRoomsTable() {
		((AbstractTableModel) roomTable.getModel()).fireTableDataChanged();
	}
}
