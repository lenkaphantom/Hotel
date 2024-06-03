package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.AdditionalServices;

public class ManageAdditionalServices {
	private Map<Integer, AdditionalServices> additionalServices;

	// constructors
	public ManageAdditionalServices() {
		this.additionalServices = new HashMap<Integer, AdditionalServices>();
	}

	// getters
	public Map<Integer, AdditionalServices> getAdditionalServices() {
		return this.additionalServices;
	}
	
	public List<String> getAdditionalServicesList() {
		List<String> services = new ArrayList<>();
		for (AdditionalServices additionalService : this.additionalServices.values()) {
			services.add(additionalService.getService());
		}
		return services;
	}

	// methods
	public void addAdditionalService(String service) {
		AdditionalServices additionalService = new AdditionalServices(service);
		this.additionalServices.put(additionalService.getId(), additionalService);
	}

	public void removeAdditionalService(int id) {
		AdditionalServices additionalService = this.additionalServices.get(id);
		this.additionalServices.remove(additionalService.getId());
	}

	public void changeAdditionalService(int id, String name) {
		if (!this.additionalServices.containsKey(id)) {
			System.out.println("Dodatna usluga sa id-jem " + id + " ne postoji.");
			return;
		}

		AdditionalServices additionalService = this.additionalServices.get(id);

		if (name != null) {
			additionalService.setService(name);
		}
	}

	public void printAdditionalServices() {
		for (AdditionalServices additionalService : this.additionalServices.values()) {
			System.out.println(additionalService.toString());
		}
	}

	public void loadAdditionalServices(String path) {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" \\| ");
				this.addAdditionalService(parts[1]);
			}
		} catch (IOException e) {
			System.out.println("Greška prilikom čitanja iz fajla.");
		}
	}
	
	public void writeAdditionalServices(String path) {
		try {
			FileWriter writer = new FileWriter(path);
			for (AdditionalServices additionalService : this.additionalServices.values()) {
				writer.write(additionalService.toStringFile() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Greška prilikom upisa u fajl.");
		}
	}
	
	public List<AdditionalServices> getAdditionalServicesFromNames(List<String> names) {
		List<AdditionalServices> services = new ArrayList<>();
		for (String name : names) {
			for (AdditionalServices service : this.additionalServices.values()) {
				if (service.getService().equals(name)) {
					services.add(service);
					break;
				}
			}
		}
		return services;
	}
}