package manage;

import java.util.HashMap;
import java.util.Map;

import entity.Employee;

public class ManageEmployees {
	private Map<Integer, Employee> employees;

	// constructors
	public ManageEmployees() {
		this.employees = new HashMap<Integer, Employee>();
	}

	// getters
	public Map<Integer, Employee> getEmployees() {
		return this.employees;
	}

	// methods
	public void addEmployee(Employee employee) {
		this.employees.put(employee.getId(), employee);
	}

	public void removeEmployee(Employee employee) {
		this.employees.remove(employee.getId());
	}

	public void changeEmployee(int id, String firstName, String lastName, String phone, String address,
			String username, String password) {
		if (!this.employees.containsKey(id)) {
			System.out.println("Zaposleni sa id-jem " + id + " ne postoji.");
			return;
		}

		Employee employee = this.employees.get(id);

		if (firstName != null) {
			employee.setFirstName(firstName);
		}
		if (lastName != null) {
			employee.setLastName(lastName);
		}
		if (phone != null) {
			employee.setPhone(phone);
		}
		if (address != null) {
			employee.setAddress(address);
		}
		if (username != null) {
			employee.setUsername(username);
		}
		if (password != null) {
			employee.setPassword(password);
		}
	}

	public void printEmployees() {
		for (Employee employee : this.employees.values()) {
			System.out.println(employee.toString());
		}
	}
}