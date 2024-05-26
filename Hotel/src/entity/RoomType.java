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

	public RoomType(TypeOfRoom type, int index) {
		this.id = idCounter++;
		this.type = type;
		this.beds = type.getBedLayouts()[index];
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
	
	public void setBeds(int index) {
		this.beds = this.type.getBedLayouts()[index];
	}

	// methods
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.getClass() != obj.getClass())
			return false;
		RoomType other = (RoomType) obj;
		return this.id == other.id;
	}
	
	@Override
	public String toString() {
		return "Tip sobe " + this.id + ": " + this.type + "(" + this.beds + ")";
	}
	
	public String toStringFile() {
		int index = -1;
		for (int i = 0; i < this.type.getBedLayouts().length; i++) {
			if (this.beds.equals(this.type.getBedLayouts()[i])) {
				index = i;
				break;
			}
		}
		return this.id + " | " + this.type + " | " + index;
	}
}
