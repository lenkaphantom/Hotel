package reservations;

import java.util.ArrayList;
import java.util.List;

public class AdditionalServices {
	private List<String> services;
	
	// constructors
	public AdditionalServices() {
	}
	
	public AdditionalServices(List<String> services) {
		this.services = new ArrayList<>(services);
	}
	
	// copy constructor
	public AdditionalServices(AdditionalServices additionalServices) {
		this.services = new ArrayList<>(additionalServices.services);
	}
	
	// getters
	public List<String> getServices() {
		return services;
	}
	
	// setters
	public void setServices(List<String> services) {
		this.services = new ArrayList<>(services);
	}
}
