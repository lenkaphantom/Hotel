package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import entity.Administrator;
import enumeracije.Gender;

public class ManageAdministrators {
	private Map<Integer, Administrator> administrators;

	// constructors
	public ManageAdministrators() {
		this.administrators = new HashMap<Integer, Administrator>();
	}

	// getters
	public Map<Integer, Administrator> getAdministrators() {
		return this.administrators;
	}

	// methods
	public void addAdministrator(String firstName, String lastName, Gender gender, LocalDate date, String phone,
			String address, String username, String password) {
		Administrator admin = new Administrator(firstName, lastName, gender, date, phone, address, username, password);
		this.administrators.put(admin.getId(), admin);
	}

	public void removeAdministrator(int id) {
		Administrator admin = this.administrators.get(id);
		admin.setIsDeleted(true);
		this.administrators.remove(admin.getId());
	}

	public void changeAdministrator(int id, String firstName, String lastName, String phone, String address,
			String username, String password) {
		if (!this.administrators.containsKey(id)) {
			System.out.println("Administrator sa id-jem " + id + " ne postoji.");
			return;
		}

		Administrator admin = this.administrators.get(id);

		if (firstName != null) {
			admin.setFirstName(firstName);
		}
		if (lastName != null) {
			admin.setLastName(lastName);
		}
		if (phone != null) {
			admin.setPhone(phone);
		}
		if (address != null) {
			admin.setAddress(address);
		}
		if (username != null) {
			admin.setUsername(username);
		}
		if (password != null) {
			admin.setPassword(password);
		}
	}

	public void printAdministrators() {
		for (Administrator admin : this.administrators.values()) {
			System.out.println(admin.toString());
		}
	}

	public void loadAdministrators(String path) {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" \\| ");
				this.addAdministrator(parts[1], parts[2], Gender.valueOf(parts[3]), LocalDate.parse(parts[4]), parts[5],
						parts[6], parts[7], parts[8]);
			}
		} catch (IOException e) {
			System.out.println("Greška prilikom čitanja iz fajla.");
		}
	}

	public void writeAdministrators(String path) {
		try {
			FileWriter writer = new FileWriter(path);
			for (Administrator administrator : this.administrators.values()) {
				writer.write(administrator.toStringFile() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Greška prilikom upisa u fajl.");
		}
	}
}
