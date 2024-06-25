package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import javax.swing.table.TableRowSorter;

import controler.ReservationControler;
import entity.Reservation;
import enumeracije.ReservationStatus;
import manage.ManageHotel;
import model.ReservationModel;
import net.miginfocom.swing.MigLayout;
import view.popup.AdditionalServicesPopup;
import view.search.RoomSearchDialog;

public class GuestFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private ManageHotel manager = ManageHotel.getInstance();
    private ReservationControler controler;

    private static final Color BACKGROUND_COLOR = new Color(214, 204, 194);
    private static final Color FOREGROUND_COLOR = new Color(102, 0, 34);

    private JPanel contentPane;
    private JTable reservationTable;
    private JTextField tfSearchReservation;
    private TableRowSorter<AbstractTableModel> reservationTableSorter;
    private JButton addBtnReservation = new JButton();
    private JButton removeBtnReservation = new JButton();
    private String guestUsername; 

    private JLabel totalCostLabel = new JLabel();

    Image img = new ImageIcon("img/add.png").getImage();
    Image newimg = img.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);

    Image img2 = new ImageIcon("img/remove.png").getImage();
    Image newimg2 = img2.getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH);

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GuestFrame frame = new GuestFrame("guestUsername");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GuestFrame(String guestUsername) {
        this.guestUsername = guestUsername;

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

        ReservationModel reservationModel = new ReservationModel(guestUsername);
        controler = reservationModel.getControler();

        reservationTable = new JTable(reservationModel);
        reservationTableSorter = new TableRowSorter<>((AbstractTableModel) reservationTable.getModel());
        reservationTable.setRowSorter(reservationTableSorter);

        reservationTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = reservationTable.rowAtPoint(e.getPoint());
                int column = reservationTable.columnAtPoint(e.getPoint());
                int modelRow = reservationTable.convertRowIndexToModel(row);

                if (column == 4) {
                    Reservation reservation = manager.getReservationsMan().getReservations().get(modelRow + 1);
                    new AdditionalServicesPopup(GuestFrame.this,
                            manager.getAdditionalServicesList(reservation.getId())).setVisible(true);
                }
            }
        });

        JPanel reservationPanel = createPanelWithTable(reservationTable, addBtnReservation, removeBtnReservation);
        contentPane.add(reservationPanel, "cell 0 0,grow");

        updateTotalCostLabel();

        initActions();
    }

    private JPanel createPanelWithTable(JTable table, JButton addButton, JButton removeButton) {
        JPanel panel = new JPanel(new MigLayout("", "[grow]", "[][][grow][]"));

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
        panel.add(totalCostLabel, "cell 0 3, grow");

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
                RoomSearchDialog roomSearchDialog = new RoomSearchDialog(GuestFrame.this, controler);
                roomSearchDialog.setVisible(true);
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
                    	int modelRow = reservationTable.convertRowIndexToModel(row);
                        manager.getReservationsMan().getReservations().get(reservationTable.getValueAt(modelRow, 0)).setStatus(ReservationStatus.CANCELLED);
                        refreshReservationTable();
                        manager.getReservationsMan().writeReservations("data/reservations.csv");
                    }
                }
            }
        });
    }

    public void refreshReservationTable() {
        ((AbstractTableModel) reservationTable.getModel()).fireTableDataChanged();
        updateTotalCostLabel();
    }

    private void updateTotalCostLabel() {
        double totalCost = manager.getReservationsMan().getTotalCostForGuest(guestUsername);
        totalCostLabel.setText("Ukupna cena svih rezervacija: " + totalCost + " RSD");
    }
}
