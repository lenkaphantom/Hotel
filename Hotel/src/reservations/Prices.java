package reservations;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import rooms.RoomType;

public class Prices {
	private Map<RoomType, Double> pricePerRoom;
	private Map<String, Double> pricePerService;
	private LocalDate startDate;
	private LocalDate endDate;

	// constructors
	public Prices() {
	}

	public Prices(Map<RoomType, Double> pricePerRoom, Map<String, Double> pricePerService, LocalDate startDate,
			LocalDate endDate) {
		this.pricePerRoom = new HashMap<>(pricePerRoom);
		this.pricePerService = new HashMap<>(pricePerService);
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	// copy constructor
	public Prices(Prices prices) {
		this.pricePerRoom = new HashMap<>(prices.pricePerRoom);
		this.pricePerService = new HashMap<>(prices.pricePerService);
		this.startDate = prices.startDate;
		this.endDate = prices.endDate;
	}
	
	// getters
	public Map<RoomType, Double> getPricePerRoom() {
		return pricePerRoom;
	}
	
	public Map<String, Double> getPricePerService() {
		return pricePerService;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	// setters
	public void setPricePerRoom(Map<RoomType, Double> pricePerRoom) {
		this.pricePerRoom = new HashMap<>(pricePerRoom);
	}
	
	public void setPricePerService(Map<String, Double> pricePerService) {
		this.pricePerService = new HashMap<>(pricePerService);
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
}
