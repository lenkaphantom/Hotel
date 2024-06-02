package view.addedit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import entity.Employee;
import enumeracije.Gender;
import enumeracije.Qualifications;
import manage.ManageHotel;
import net.miginfocom.swing.MigLayout;
import validation.Validation;
import view.AdministratorFrame;

public class AddEditEmployeeDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();
	private Employee editE;
	private JFrame parent;

	private JTextField txtIme;
	private JTextField txtPrezime;
	private JComboBox<String> cbPol;
	private JDateChooser dcDatumRodjenja;
	private JTextField txtBrojTelefona;
	private JTextField txtAdresa;
	private JTextField txtKorisnickoIme;
	private JPasswordField txtLozinka;
	private JComboBox<String> cbKvalifikacija;
	private JTextField txtGodineStaza;
	private JComboBox<String> cbRadnoMesto;

	public AddEditEmployeeDialog(JFrame parent, int id) {
		super(parent, true);
		this.parent = parent;
		if (id != 0) {
			setTitle("Izmena zaposlenog");
		} else {
			setTitle("Dodavanje zaposlenog");
		}
		this.editE = manager.getEmployeesMan().getEmployees().get(id);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		pack();
	}

	private void initGUI() {
		MigLayout ml = new MigLayout("wrap 2", "[][grow]", "[]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]10[]20[]");
		setLayout(ml);

		JLabel lblIme = new JLabel("Ime");
		add(lblIme);
		txtIme = new JTextField(20);
		add(txtIme, "growx");

		JLabel lblPrezime = new JLabel("Prezime");
		add(lblPrezime);
		txtPrezime = new JTextField(20);
		add(txtPrezime, "growx");

		JLabel lblPol = new JLabel("Pol");
		add(lblPol);
		cbPol = new JComboBox<>(new String[] { "MALE", "FEMALE", "OTHER" });
		add(cbPol, "growx");

		JLabel lblDatumRodjenja = new JLabel("Datum rodjenja");
		add(lblDatumRodjenja);
		dcDatumRodjenja = new JDateChooser();
		add(dcDatumRodjenja, "growx");

		JLabel lblBrojTelefona = new JLabel("Broj telefona");
		add(lblBrojTelefona);
		txtBrojTelefona = new JTextField(20);
		add(txtBrojTelefona, "growx");

		JLabel lblAdresa = new JLabel("Adresa");
		add(lblAdresa);
		txtAdresa = new JTextField(20);
		add(txtAdresa, "growx");

		JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
		add(lblKorisnickoIme);
		txtKorisnickoIme = new JTextField(20);
		add(txtKorisnickoIme, "growx");

		JLabel lblLozinka = new JLabel("Lozinka");
		add(lblLozinka);
		txtLozinka = new JPasswordField(20);
		add(txtLozinka, "growx");

		JLabel lblKvalifikacija = new JLabel("Kvalifikacija");
		add(lblKvalifikacija);
		cbKvalifikacija = new JComboBox<>(new String[] { "NONE", "BASIC", "INTERMEDIATE", "ADVANCED" });
		add(cbKvalifikacija, "growx");

		JLabel lblGodineStaza = new JLabel("Godine staza");
		add(lblGodineStaza);
		txtGodineStaza = new JTextField(20);
		add(txtGodineStaza, "growx");

		JLabel lblRadnoMesto = new JLabel("Radno mesto");
		add(lblRadnoMesto);
		cbRadnoMesto = new JComboBox<>(new String[] { "Receptionist", "HouseKeeper" });
		add(cbRadnoMesto, "growx");

		JButton btnCancel = new JButton("Cancel");
		add(btnCancel);

		JButton btnOK = new JButton("OK");
		add(btnOK);

		if (editE != null) {
			txtIme.setText(editE.getFirstName());
			txtPrezime.setText(editE.getLastName());
			cbPol.setSelectedItem(editE.getGender().toString());
			dcDatumRodjenja.setDate(java.sql.Date.valueOf(editE.getDate()));
			txtBrojTelefona.setText(editE.getPhone());
			txtAdresa.setText(editE.getAddress());
			txtKorisnickoIme.setText(editE.getUsername());
			txtLozinka.setText(editE.getPassword());
			cbKvalifikacija.setSelectedItem(editE.getQualification().toString());
			txtGodineStaza.setText(String.valueOf(editE.getYearsOfExperience()));
			cbRadnoMesto.setSelectedItem(editE.getType());
		}

		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validateFields()) {
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					Gender pol = Gender.valueOf(cbPol.getSelectedItem().toString());
					Date datumRodjenja = dcDatumRodjenja.getDate();
					String brojTelefona = txtBrojTelefona.getText().trim();
					String adresa = txtAdresa.getText().trim();
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String lozinka = new String(txtLozinka.getPassword());
					Qualifications kvalifikacija = Qualifications.valueOf(cbKvalifikacija.getSelectedItem().toString());
					int godineStaza = Integer.parseInt(txtGodineStaza.getText().trim());
					enumeracije.Type radnoMesto = enumeracije.Type.valueOf(cbRadnoMesto.getSelectedItem().toString());

					LocalDate localDatumRodjenja = datumRodjenja.toInstant().atZone(ZoneId.systemDefault())
							.toLocalDate();

					if (editE != null) {
						manager.getEmployeesMan().changeEmployee(editE.getId(), ime, prezime, pol, brojTelefona, adresa,
								korisnickoIme, lozinka, kvalifikacija, godineStaza);
					} else {
						manager.getEmployeesMan().addEmployee(ime, prezime, pol, localDatumRodjenja, brojTelefona,
								adresa, korisnickoIme, lozinka, kvalifikacija, godineStaza, radnoMesto);
					}
					((AdministratorFrame) parent).refreshEmployeeTable();
					AddEditEmployeeDialog.this.dispose();
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddEditEmployeeDialog.this.dispose();
			}
		});
	}

	private boolean validateFields() {
		if (txtIme.getText().trim().isEmpty() || txtPrezime.getText().trim().isEmpty()
				|| cbPol.getSelectedItem() == null || dcDatumRodjenja.getDate() == null
				|| txtBrojTelefona.getText().trim().isEmpty() || txtAdresa.getText().trim().isEmpty()
				|| txtKorisnickoIme.getText().trim().isEmpty() || txtLozinka.getPassword().length == 0
				|| cbKvalifikacija.getSelectedItem() == null || txtGodineStaza.getText().trim().isEmpty()
				|| cbRadnoMesto.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Sva polja moraju biti popunjena.", "Greška",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Integer.parseInt(txtGodineStaza.getText().trim());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Godine staza moraju biti broj.", "Greška", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (Validation.isNumberNegative(Integer.parseInt(txtGodineStaza.getText().trim()))) {
			JOptionPane.showMessageDialog(this, "Godine staza ne mogu biti negativne.", "Greška",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (Validation.isDateAfterCurrentDate(new java.sql.Date(dcDatumRodjenja.getDate().getTime()))) {
			JOptionPane.showMessageDialog(this, "Datum rodjenja ne moze biti u buducnosti.", "Greška",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (Validation.isLetterInPhoneNumber(txtBrojTelefona.getText().trim())) {
			JOptionPane.showMessageDialog(this, "Broj telefona ne moze sadrzati slova.", "Greška",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!Validation.isNumberInPassword(txtLozinka.getPassword())) {
			JOptionPane.showMessageDialog(this, "Lozinka mora sadrzati bar jedan broj.", "Greška",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (Validation.isPasswordShort(txtLozinka.getPassword())) {
			JOptionPane.showMessageDialog(this, "Lozinka mora imati bar 8 karaktera.", "Greška",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (Validation.isCharInText(txtIme.getText().trim(), '|')) {
			JOptionPane.showMessageDialog(this, "Ime ne moze sadrzati '|'.", "Greška", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (Validation.isCharInText(txtPrezime.getText().trim(), '|')) {
			JOptionPane.showMessageDialog(this, "Prezime ne moze sadrzati '|'.", "Greška", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (Validation.isCharInText(txtAdresa.getText().trim(), '|')) {
			JOptionPane.showMessageDialog(this, "Adresa ne moze sadrzati '|'.", "Greška", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (Validation.isCharInText(txtKorisnickoIme.getText().trim(), '|')) {
			JOptionPane.showMessageDialog(this, "Korisnicko ime ne moze sadrzati '|'.", "Greška",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (Validation.isCharInText(txtLozinka.getPassword(), '|')) {
			JOptionPane.showMessageDialog(this, "Lozinka ne moze sadrzati '|'.", "Greška", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (Validation.isCharInText(txtBrojTelefona.getText().trim(), '|')) {
			JOptionPane.showMessageDialog(this, "Broj telefona ne moze sadrzati '|'.", "Greška",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public static void main(String[] args) {
		try {
			AddEditEmployeeDialog dialog = new AddEditEmployeeDialog(new JFrame(), 0);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
