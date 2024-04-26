package entity;

import enumeracije.TypeOfRoom;

public class RoomType {
	private static int idCounter = 1;
	private int id;
	private TypeOfRoom type;
	private String beds;

	// constructors
	public RoomType() {
		this.id = idCounter++;
	}

	public RoomType(TypeOfRoom type, String beds) {
		this.id = idCounter++;
		this.type = type;
		this.beds = beds;
	}

	// copy constructor
	public RoomType(RoomType roomType) {
		this.id = idCounter++;
		this.type = roomType.type;
		this.beds = roomType.beds;
	}

	// getters
	public int getId() {
		return this.id;
	}

	public TypeOfRoom getType() {
		return this.type;
	}
	
	public String getBeds() {
		return this.beds;
	}

	// setters
	public void setType(TypeOfRoom type) {
		this.type = type;
	}
	
	public void setBeds(String beds) {
		this.beds = beds;
	}

	// methods
	@Override
	public String toString() {
		return this.id + " | " + this.type + " | " + this.beds;
	}
}
