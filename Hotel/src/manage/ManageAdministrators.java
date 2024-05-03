package manage;

import java.util.HashMap;
import java.util.Map;

import entity.Administrator;

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
	public void addAdministrator(Administrator admin) {
		this.administrators.put(admin.getId(), admin);
	}

	public void removeAdministrator(Administrator admin) {
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
}
