package rooms;

public class RoomType {
	protected int numberOfPeople;
	protected int numberOfBeds;

	// constructors
	public RoomType() {
	}

	public RoomType(int numberOfPeople, int numberOfBeds) {
		this.numberOfPeople = numberOfPeople;
		this.numberOfBeds = numberOfBeds;
	}

	// copy constructor
	public RoomType(RoomType roomType) {
		this.numberOfPeople = roomType.numberOfPeople;
		this.numberOfBeds = roomType.numberOfBeds;
	}

	// getters
	public int getOccupancy() {
		return numberOfPeople;
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}

	// setters
	public void setOccupancy(int numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}
}
