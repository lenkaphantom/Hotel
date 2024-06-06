package view.addedit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entity.AdditionalServices;
import manage.ManageHotel;
import net.miginfocom.swing.MigLayout;
import validation.Validation;
import view.AdministratorFrame;

public class AddEditServicesDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();
	private final JPanel contentPanel = new JPanel();
	private AdditionalServices editS;
	private JFrame parent;

	private JTextField txtName;

	public static void main(String[] args) {
		try {
			AddEditServicesDialog dialog = new AddEditServicesDialog(null, 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AddEditServicesDialog(JFrame parent, int id) {
		super(parent, true);
		this.parent = parent;
		if (id != 0) {
			setTitle("Izmena usluge");
		} else {
			setTitle("Dodavanje usluge");
		}
		this.editS = manager.getAdditionalServicesMan().getAdditionalServices().get(id);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		pack();
	}

	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[]");
		setLayout(layout);

		JLabel lblName = new JLabel("Usluga");
		add(lblName);
		txtName = new JTextField(20);
		add(txtName, "growx");

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);

		if (editS != null) {
			txtName.setText(editS.getService());
		}

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText().trim();

				if (!validateFields()) {
					return;
				}

				if (name.equals("")) {
					JOptionPane.showMessageDialog(AddEditServicesDialog.this, "Morate uneti sve podatke", "Greska",
							JOptionPane.WARNING_MESSAGE);
				} else {
					if (editS != null) {
						editS.setService(name);
					} else {
						manager.getAdditionalServicesMan().addAdditionalService(name);
					}
					((AdministratorFrame) parent).refreshAdditionalServicesTable();
					AddEditServicesDialog.this.dispose();
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditServicesDialog.this.dispose();
			}
		});
	}

	private boolean validateFields() {
		if (Validation.isStringEmpty(txtName.getText())) {
			JOptionPane.showMessageDialog(this, "Morate uneti sve podatke", "Greska", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (!Validation.isValidService(txtName.getText())) {
			JOptionPane.showMessageDialog(this, "Naziv usluge mora da sadrzi samo slova od kojih je prvo veliko",
					"Greska", JOptionPane.WARNING_MESSAGE);
			return false;
		}

		for (AdditionalServices s : manager.getAdditionalServicesMan().getAdditionalServices().values()) {
			if (s.getService().equals(txtName.getText())) {
				JOptionPane.showMessageDialog(this, "Usluga vec postoji", "Greska", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		return true;
	}
}
