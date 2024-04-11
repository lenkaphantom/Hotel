package rooms;

public class RoomType {
	protected int numberOfPeople;
	protected int numberOfBeds;

	// constructors
	public RoomType() {
		this.numberOfPeople = 0;
		this.numberOfBeds = 0;
	}

	public RoomType(int numberOfPeople, int numberOfBeds) {
		this.numberOfPeople = numberOfPeople;
		this.numberOfBeds = numberOfBeds;
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
