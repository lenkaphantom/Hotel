package manage;

import java.util.HashMap;
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

	// methods
	public void addAdditionalService(AdditionalServices additionalService) {
		this.additionalServices.put(additionalService.getId(), additionalService);
	}

	public void removeAdditionalService(AdditionalServices additionalService) {
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
}