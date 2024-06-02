package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import manage.ManageHotel;
import model.EmployeeModel;
import model.GuestModel;
import net.miginfocom.swing.MigLayout;
import view.addedit.AddEditEmployeeDialog;
import view.addedit.AddEditGuestDialog;

public class AdministratorFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();

	private static final Color BACKGROUND_COLOR = new Color(214, 204, 194);
	private static final Color FOREGROUND_COLOR = new Color(102, 0, 34);

	private JPanel contentPane;
	private JTable employeeTable;
	private JTable guestTable;
	private JTextField tfSearchEmployee;
	private JTextField tfSearchGuest;
	private TableRowSorter<AbstractTableModel> employeeTableSorter;
	private TableRowSorter<AbstractTableModel> guestTableSorter;

	private JButton addBtnEmployee = new JButton();
	private JButton editBtnEmployee = new JButton();
	private JButton removeBtnEmployee = new JButton();

	private JButton addBtnGuest = new JButton();
	private JButton editBtnGuest = new JButton();
	private JButton removeBtnGuest = new JButton();

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

		initActions();
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

	private void setTableColumnWidths(JTable table, int[] columnWidths) {
        for (int i = 0; i < columnWidths.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }
    }

	private void initActions() {
        addBtnEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEditEmployeeDialog addEditEmployeeDialog = new AddEditEmployeeDialog(AdministratorFrame.this, 0);
                addEditEmployeeDialog.setVisible(true);
                refreshEmployeeTable();
                manager.getEmployeesMan().writeEmployees("data/employees.csv");;
            }
        });

        editBtnEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = employeeTable.getSelectedRow();
                if (row == -1) {
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                    AddEditEmployeeDialog addEditEmployeeDialog = new AddEditEmployeeDialog(AdministratorFrame.this, row + 1);
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
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                	int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete zaposlenog?", "Brisanje zaposlenog", JOptionPane.YES_NO_OPTION);
                	if (izbor == JOptionPane.YES_OPTION) {
                        manager.getEmployeesMan().removeEmployee(row + 1);
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
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                	AddEditGuestDialog addEditGuestDialog = new AddEditGuestDialog(AdministratorFrame.this, row + 1);
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
                    JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                	int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete gosta?", "Brisanje gosta", JOptionPane.YES_NO_OPTION);
                	if (izbor == JOptionPane.YES_OPTION) {
                        manager.getGuestsMan().removeGuest(row + 1);
                        manager.getGuestsMan().writeGuests("data/guests.csv");
                	}
                    refreshGuestTable();
                }
            }
        });
    }

    public void refreshEmployeeTable() {
        ((AbstractTableModel) employeeTable.getModel()).fireTableDataChanged();
    }

    public void refreshGuestTable() {
        ((AbstractTableModel) guestTable.getModel()).fireTableDataChanged();
    }
}
