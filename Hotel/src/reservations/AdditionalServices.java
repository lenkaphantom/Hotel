package reservations;

public class AdditionalServices {
	private String service;
	
	// constructors
	public AdditionalServices() {
	}
	
	public AdditionalServices(String service) {
		this.service = service;
	}
	
	// copy constructor
	public AdditionalServices(AdditionalServices additionalServices) {
		this.service = additionalServices.service;
	}
	
	// getters
	public String getService() {
		return service;
	}
	
	// setters
	public void setService(String service) {
		this.service = service;
	}
}
