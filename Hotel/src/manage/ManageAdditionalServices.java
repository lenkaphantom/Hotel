package manage;

import java.util.HashMap;
import java.util.Map;

import reservations.AdditionalServices;

public class ManageAdditionalServices {
	private static Map<Integer, AdditionalServices> additionalServices = new HashMap<Integer, AdditionalServices>();

	// constructors
	private ManageAdditionalServices() {}

	// getters
	public static Map<Integer, AdditionalServices> getAdditionalServices() {
		return additionalServices;
	}

	// methods
	public static void addAdditionalService(AdditionalServices additionalService) {
		additionalServices.put(additionalService.getId(), additionalService);
	}

	public static void removeAdditionalService(AdditionalServices additionalService) {
		additionalServices.remove(additionalService.getId());
	}

	public static void changeAdditionalService(int id, String name) {
		if (!additionalServices.containsKey(id)) {
			System.out.println("Dodatna usluga sa id-jem " + id + " ne postoji.");
			return;
		}

		AdditionalServices additionalService = additionalServices.get(id);
		
		if (name != null) {
			additionalService.setService(name);
		}
	}

	public static void printAdditionalServices() {
		for (AdditionalServices additionalService : additionalServices.values()) {
			System.out.println(additionalService.toString());
		}
	}
}