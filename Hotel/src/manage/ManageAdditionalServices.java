package manage;

import java.util.ArrayList;
import java.util.List;

import reservations.AdditionalServices;

public abstract class ManageAdditionalServices {
	private List<AdditionalServices> additionalServices;
	
	// constructors
	public ManageAdditionalServices() {
	}
	
	public ManageAdditionalServices(List<AdditionalServices> additionalServices) {
		this.additionalServices = new ArrayList<AdditionalServices>(additionalServices);
	}
	
	// copy constructor
	public ManageAdditionalServices(ManageAdditionalServices manageAdditionalServices) {
		this.additionalServices = new ArrayList<AdditionalServices>(manageAdditionalServices.additionalServices);
	}
	
	// getters
	public List<AdditionalServices> getAdditionalServices() {
		return additionalServices;
	}
	
	// setters
	public void setAdditionalServices(List<AdditionalServices> additionalServices) {
		this.additionalServices = new ArrayList<AdditionalServices>(additionalServices);
	}
	
	// methods
	public void addAdditionalServices(AdditionalServices additionalServices) {
		this.additionalServices.add(additionalServices);
	}
	
	public void removeAdditionalServices(AdditionalServices additionalServices) {
		this.additionalServices.remove(additionalServices);
	}
}
