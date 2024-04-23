package manage;

import java.util.HashMap;
import java.util.Map;

import users.Employee;

public class ManageEmployees {
	private static Map<Integer, Employee> employees = new HashMap<Integer, Employee>();

	// constructors
	private ManageEmployees() {}

	// getters
	public static Map<Integer, Employee> getEmployees() {
		return employees;
	}

	// methods
	public static void addEmployee(Employee employee) {
		employees.put(employee.getId(), employee);
	}

	public static void removeEmployee(Employee employee) {
		employees.remove(employee.getId());
	}

	public static void changeEmployee(int id, String firstName, String lastName, String phone, String address,
			String username, String password) {
		if (!employees.containsKey(id)) {
			System.out.println("Zaposleni sa id-jem " + id + " ne postoji.");
			return;
		}

		Employee employee = employees.get(id);

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

	public static void printEmployees() {
		for (Employee employee : employees.values()) {
			System.out.println(employee.toString());
		}
	}
}