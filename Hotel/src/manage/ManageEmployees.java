package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import entity.Employee;
import entity.HouseKeeper;
import entity.Receptionist;
import entity.Room;
import enumeracije.Gender;
import enumeracije.Qualifications;
import enumeracije.Type;

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

	public Map<Integer, HouseKeeper> getHouseKeepers() {
		Map<Integer, HouseKeeper> houseKeepers = new HashMap<>();
		for (Employee employee : this.employees.values()) {
			if (employee instanceof HouseKeeper) {
				houseKeepers.put(employee.getId(), (HouseKeeper) employee);
			}
		}
		return houseKeepers;
	}

	public Map<Integer, Receptionist> getReceptionists() {
		Map<Integer, Receptionist> receptionists = new HashMap<>();
		for (Employee employee : this.employees.values()) {
			if (employee instanceof Receptionist) {
				receptionists.put(employee.getId(), (Receptionist) employee);
			}
		}
		return receptionists;
	}

	// methods
	public void addEmployee(String firstName, String lastName, Gender gender, LocalDate date, String phone,
			String address, String username, String password, Qualifications qualification,
			int yearsOfExperience, Type type) {
		if (type == Type.Receptionist) {
			Receptionist receptionist = new Receptionist(firstName, lastName, gender, date, phone, address, username,
					password, qualification, yearsOfExperience, type);
			this.employees.put(receptionist.getId(), receptionist);
		} else if (type == Type.HouseKeeper) {
			HouseKeeper houseKeeper = new HouseKeeper(firstName, lastName, gender, date, phone, address, username,
					password, qualification, yearsOfExperience, type);
			this.employees.put(houseKeeper.getId(), houseKeeper);
		}
	}

	public void removeEmployee(int id) {
		Employee employee = this.employees.get(id);
		this.employees.remove(employee.getId());
	}

	public void changeEmployee(int id, String firstName, String lastName, Gender gender, String phone, String address,
			String username, String password, Qualifications qualification, int yearsOfExperience) {
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
		if (gender != null) {
			employee.setGender(gender);
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
		if (qualification != null) {
			employee.setQualification(qualification);
		}
		if (yearsOfExperience != 0) {
			employee.setYearsOfExperience(yearsOfExperience);
		}
		
		employee.calculateSalary();
	}

	public void printEmployees() {
		for (Employee employee : this.employees.values()) {
			System.out.println(employee.toString());
		}
	}

	public void loadEmployees(String path, ManageHotel manager) {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" \\| ");
				this.addEmployee(parts[1], parts[2], Gender.valueOf(parts[3]), LocalDate.parse(parts[4]), parts[5],
						parts[6], parts[7], parts[8], Qualifications.valueOf(parts[9]),
						Integer.parseInt(parts[11]), Type.valueOf(parts[12]));
				if (parts[12].equals("HouseKeeper") && parts.length > 13) {
					HouseKeeper houseKeeper = (HouseKeeper) this.employees.get(Integer.parseInt(parts[0]));
					String[] data = parts[13].split(":");
					String[] rooms = data[1].split(",");
					for (String room : rooms) {
						Room roomToAdd = manager.getRoomsMan().getRooms().get(Integer.parseInt(room));
						houseKeeper.getRoomsToClean().put(LocalDate.parse(data[0]), roomToAdd);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Greška prilikom čitanja iz fajla.");
		}
	}

	public void writeEmployees(String path) {
		try {
			FileWriter writer = new FileWriter(path);
			for (Employee employee : this.employees.values()) {
				writer.write(employee.toStringFile() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Greška prilikom upisa u fajl.");
		}
	}
}