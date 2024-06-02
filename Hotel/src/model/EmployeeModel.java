package model;

import javax.swing.table.AbstractTableModel;

import entity.Employee;
import manage.ManageHotel;

public class EmployeeModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private ManageHotel manager = ManageHotel.getInstance();
	private String[] columnNames = { "ID", "Ime", "Prezime", "Pol", "Datum rodjenja", "Broj telefona", "Adresa",
			"Korisnicko ime", "Lozinka", "Kvalifikacija", "Plata", "Godine staza", "Radno mesto" };

	public EmployeeModel() {
	}

	@Override
	public int getRowCount() {
		return manager.getEmployeesMan().getEmployees().size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Employee employee = this.manager.getEmployeesMan().getEmployees().get(rowIndex + 1);
		switch (columnIndex) {
		case 0:
			return employee.getId();
		case 1:
			return employee.getFirstName();
		case 2:
			return employee.getLastName();
		case 3:
			return employee.getGender();
		case 4:
			return employee.getDate();
		case 5:
			return employee.getPhone();
		case 6:
			return employee.getAddress();
		case 7:
			return employee.getUsername();
		case 8:
			return employee.getPassword();
		case 9:
			return employee.getQualification();
		case 10:
			return employee.getSalary();
		case 11:
			return employee.getYearsOfExperience();
		case 12:
			return employee.getType();
		default:
			return null;
		}
	}
	
	@Override
	public String getColumnName(int column) {
		return this.columnNames[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return this.getValueAt(0, columnIndex).getClass();
	}
}
