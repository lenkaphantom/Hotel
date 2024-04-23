package reservations;

public class AdditionalServices {
	private static int idCounter = 1;
	private int id;
	private String service;

	// constructors
	public AdditionalServices() {
		this.id = idCounter++;
	}

	public AdditionalServices(String service) {
		this.id = idCounter++;
		this.service = service;
	}

	// copy constructor
	public AdditionalServices(AdditionalServices additionalServices) {
		this.id = idCounter++;
		this.service = additionalServices.service;
	}

	// getters
	public int getId() {
		return this.id;
	}

	public String getService() {
		return this.service;
	}

	// setters
	public void setService(String service) {
		this.service = service;
	}

	// methods
	@Override
	public String toString() {
		return this.id + " | " + this.service;
	}
}
