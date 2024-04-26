package rooms;

public class RoomType {
	private static int idCounter = 1;
	private int id;
	private String type;

	// constructors
	public RoomType() {
		this.id = idCounter++;
	}

	public RoomType(String type) {
		this.id = idCounter++;
		this.type = type;
	}

	// copy constructor
	public RoomType(RoomType roomType) {
		this.id = idCounter++;
		this.type = roomType.type;
	}

	// getters
	public int getId() {
		return this.id;
	}

	public String getType() {
		return this.type;
	}

	// setters
	public void setType(String type) {
		this.type = type;
	}

	// methods
	@Override
	public String toString() {
		return this.id + " | " + this.type;
	}
}
