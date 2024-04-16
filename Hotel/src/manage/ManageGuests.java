package manage;

import java.util.ArrayList;
import java.util.List;

import users.Guest;

public class ManageGuests {
	private List<Guest> guests;
	
	// constructors
	public ManageGuests() {
	}
	
	public ManageGuests(List<Guest> guests) {
		this.guests = new ArrayList<Guest>(guests);
	}
	
	// copy constructor
	public ManageGuests(ManageGuests manageGuests) {
		this.guests = new ArrayList<Guest>(manageGuests.guests);
	}
	
	// getters
	public List<Guest> getGuests() {
		return guests;
	}
	
	// setters
	public void setGuests(List<Guest> guests) {
		this.guests = new ArrayList<Guest>(guests);
	}
	
	// methods
	public void addGuest(Guest guest) {
		this.guests.add(guest);
	}
	
	public void removeGuest(Guest guest) {
		this.guests.remove(guest);
	}
	
	public void changeGuest(Guest oldGuest, Guest newGuest) {
		this.guests.set(this.guests.indexOf(oldGuest), newGuest);
	}
	
	public void printGuests() {
		for (Guest guest : guests) {
			System.out.println(guest.toString());
		}
	}
}
