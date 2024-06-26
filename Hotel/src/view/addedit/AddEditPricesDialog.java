package view.addedit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.toedter.calendar.JDateChooser;

import entity.AdditionalServices;
import entity.Prices;
import entity.RoomType;
import manage.ManageHotel;
import model.RoomPriceModel;
import model.ServicePriceModel;
import net.miginfocom.swing.MigLayout;
import view.AdministratorFrame;

public class AddEditPricesDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private ManageHotel manager = ManageHotel.getInstance();
    private Prices editPrices;
    private JFrame parent;

    private JTable servicesTable;
    private JTable roomTypesTable;
    private JDateChooser startDateChooser;
    private JDateChooser endDateChooser;
    private JButton btnOK;
    private JButton btnCancel;

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
        JPanel mainPanel = new JPanel(new MigLayout("wrap 2", "[][grow]", "[][]20[][][grow]20[]"));
        setContentPane(mainPanel);

        mainPanel.add(new JLabel("Datum početka:"), "align label");
        startDateChooser = new JDateChooser();
        mainPanel.add(startDateChooser, "growx");

        mainPanel.add(new JLabel("Datum kraja:"), "align label");
        endDateChooser = new JDateChooser();
        mainPanel.add(endDateChooser, "growx");

        mainPanel.add(new JLabel("Usluge:"), "align label");
        mainPanel.add(new JLabel("Tipovi soba:"), "align label");

        servicesTable = new JTable(new ServicePriceModel(editPrices != null ? editPrices.getId() : -1));
        roomTypesTable = new JTable(new RoomPriceModel(editPrices != null ? editPrices.getId() : -1));
        mainPanel.add(new JScrollPane(servicesTable), "grow");
        mainPanel.add(new JScrollPane(roomTypesTable), "grow");

        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");
        mainPanel.add(btnOK, "split 2, right");
        mainPanel.add(btnCancel, "right");

        if (editPrices != null) {
            fillFields();
        }

        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateFields()) {
                    savePrices();
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
    }

    private boolean validateFields() {
        if (startDateChooser.getDate() == null || endDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Morate uneti datume.", "Greška", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private void savePrices() {
        LocalDate startDate = startDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = endDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        Map<RoomType, Double> pricePerRoom = ((RoomPriceModel) roomTypesTable.getModel()).getPrices();
        Map<AdditionalServices, Double> pricePerService = ((ServicePriceModel) servicesTable.getModel()).getPrices();

        if (editPrices != null) {
            editPrices.setStartDate(startDate);
            editPrices.setEndDate(endDate);
            editPrices.setPricePerRoom(pricePerRoom);
            editPrices.setPricePerService(pricePerService);
        } else {
            manager.getPricesMan().addPrices(pricePerRoom, pricePerService, startDate, endDate);
        }
    }

    private void fillFields() {
        startDateChooser.setDate(Date.from(editPrices.getStartDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        endDateChooser.setDate(Date.from(editPrices.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        ((RoomPriceModel) roomTypesTable.getModel()).setPrices(editPrices.getPricePerRoom());
        ((ServicePriceModel) servicesTable.getModel()).setPrices(editPrices.getPricePerService());
    }
}
