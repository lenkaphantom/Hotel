package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entity.Administrator;
import entity.Guest;
import entity.HouseKeeper;
import entity.Receptionist;
import manage.ManageHotel;
import net.miginfocom.swing.MigLayout;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = -8030073041799867465L;
    
    private ManageHotel manageHotel = ManageHotel.getInstance();

    private static final Color BACKGROUND_COLOR = new Color(214, 204, 194);
    private static final Color BUTTON_BACKGROUND_COLOR = new Color(227, 213, 202);
    private static final Color BUTTON_FOREGROUND_COLOR = new Color(102, 0, 34);
    private static final Color LABEL_FOREGROUND_COLOR = BUTTON_FOREGROUND_COLOR;

    public MainFrame() {
        loginDialog();
    }

    public void loginDialog() {
        JDialog loginDialog = new JDialog(this, "Login", true);
        loginDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        loginDialog.setLocationRelativeTo(null);
        loginDialog.setResizable(false);

        loginDialog.getContentPane().setBackground(BACKGROUND_COLOR);

        initLoginDialog(loginDialog);
        loginDialog.pack();
        loginDialog.setVisible(true);
    }

    private void initLoginDialog(JDialog loginDialog) {
        MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
        loginDialog.setLayout(layout);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");
        loginButton.setBackground(BUTTON_BACKGROUND_COLOR);
        loginButton.setForeground(BUTTON_FOREGROUND_COLOR);
        cancelButton.setBackground(BUTTON_BACKGROUND_COLOR);
        cancelButton.setForeground(BUTTON_FOREGROUND_COLOR);

        // Postavljanje boje teksta za JLabel komponente
        usernameLabel.setForeground(LABEL_FOREGROUND_COLOR);
        passwordLabel.setForeground(LABEL_FOREGROUND_COLOR);

        loginDialog.getRootPane().setDefaultButton(loginButton);

        loginDialog.add(usernameLabel, "cell 0 0");
        loginDialog.add(usernameField, "cell 1 0");
        loginDialog.add(passwordLabel, "cell 0 1");
        loginDialog.add(passwordField, "cell 1 1");
        loginDialog.add(loginButton, "cell 0 2");
        loginDialog.add(cancelButton, "cell 1 2");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(loginDialog, "Niste uneli sve podatke.", "Greska",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int userType = validateLogin(username, password);
                if (userType != -1) {
                    loginDialog.dispose();
                    mainFrame(userType);
                } else {
                    JOptionPane.showMessageDialog(loginDialog, "Pogresno korisnicko ime ili lozinka.", "Greska",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        loginDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private int validateLogin(String username, String password) {
        for (Map.Entry<Integer, Guest> entry : this.manageHotel.getGuestsMan().getGuests().entrySet()) {
            if (entry.getValue().getUsername().equals(username) && entry.getValue().getPassword().equals(password)) {
                return 1;
            }
        }
        
        for (Map.Entry<Integer, Administrator> entry : this.manageHotel.getAdministratorsMan().getAdministrators().entrySet()) {
            if (entry.getValue().getUsername().equals(username) && entry.getValue().getPassword().equals(password)) {
                return 2;
            }
        }
        
        for (Map.Entry<Integer, Receptionist> entry : this.manageHotel.getEmployeesMan().getReceptionists().entrySet()) {
            if (entry.getValue().getUsername().equals(username) && entry.getValue().getPassword().equals(password)) {
                return 3;
            }
        }
        
        for (Map.Entry<Integer, HouseKeeper> entry : this.manageHotel.getEmployeesMan().getHouseKeepers().entrySet()) {
            if (entry.getValue().getUsername().equals(username) && entry.getValue().getPassword().equals(password)) {
                return 4;
            }
        }
        
        return -1;
    }

    private void mainFrame(int userType) {
        this.setTitle("Hotel");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setIconImage(new ImageIcon("img/hotel.png").getImage());

        getContentPane().setBackground(BACKGROUND_COLOR);

        initMainFrame(userType);
        
        this.setVisible(true);
    }

    private void initMainFrame(int userType) {
        JMenuBar menuBar = new JMenuBar();

        switch (userType) {
            case 1:
                createGuestMenu(menuBar);
                break;
            case 2:
                createAdminMenu(menuBar);
                break;
            case 3:
                createReceptionistMenu(menuBar);
                break;
            case 4:
                createHouseKeeperMenu(menuBar);
                break;
        }

        this.setJMenuBar(menuBar);
    }

    private void createGuestMenu(JMenuBar menuBar) {
        JMenu guestMenu = new JMenu("Guest");
        JMenuItem viewBookings = new JMenuItem("View Bookings");
        JMenuItem updateProfile = new JMenuItem("Update Profile");
        guestMenu.add(viewBookings);
        guestMenu.add(updateProfile);
        menuBar.add(guestMenu);
    }

    private void createAdminMenu(JMenuBar menuBar) {
        JMenu adminMenu = new JMenu("Admin");
        JMenuItem manageUsers = new JMenuItem("Manage Users");
        JMenuItem viewReports = new JMenuItem("View Reports");
        adminMenu.add(manageUsers);
        adminMenu.add(viewReports);
        menuBar.add(adminMenu);
    }

    private void createReceptionistMenu(JMenuBar menuBar) {
        JMenu receptionistMenu = new JMenu("Receptionist");
        JMenuItem checkIn = new JMenuItem("Check In");
        JMenuItem checkOut = new JMenuItem("Check Out");
        receptionistMenu.add(checkIn);
        receptionistMenu.add(checkOut);
        menuBar.add(receptionistMenu);
    }

    private void createHouseKeeperMenu(JMenuBar menuBar) {
        JMenu houseKeeperMenu = new JMenu("HouseKeeper");
        JMenuItem viewTasks = new JMenuItem("View Tasks");
        JMenuItem updateStatus = new JMenuItem("Update Status");
        houseKeeperMenu.add(viewTasks);
        houseKeeperMenu.add(updateStatus);
        menuBar.add(houseKeeperMenu);
    }
}
