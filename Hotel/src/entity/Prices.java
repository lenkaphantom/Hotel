package entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Prices {
	private static int idCounter = 1;
	private int id;
	private Map<RoomType, Double> pricePerRoom;
	private Map<AdditionalServices, Double> pricePerService;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean isDeleted = false;

	// constructors
	public Prices() {
		this.id = idCounter++;
	}

	public Prices(Map<RoomType, Double> pricePerRoom, Map<AdditionalServices, Double> pricePerService,
			LocalDate startDate, LocalDate endDate) {
		this.id = idCounter++;
		this.pricePerRoom = new HashMap<>(pricePerRoom);
		this.pricePerService = new HashMap<>(pricePerService);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	// copy constructor
	public Prices(Prices prices) {
		this.id = idCounter++;
		this.pricePerRoom = new HashMap<>(prices.pricePerRoom);
		this.pricePerService = new HashMap<>(prices.pricePerService);
		this.startDate = prices.startDate;
		this.endDate = prices.endDate;
	}

	// getters
	public int getId() {
		return id;
	}

	public Map<RoomType, Double> getPricePerRoom() {
		return pricePerRoom;
	}

	public Map<AdditionalServices, Double> getPricePerService() {
		return pricePerService;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	// setters
	public void setPricePerRoom(Map<RoomType, Double> pricePerRoom) {
		this.pricePerRoom = new HashMap<>(pricePerRoom);
	}

	public void setPricePerService(Map<AdditionalServices, Double> pricePerService) {
		this.pricePerService = new HashMap<>(pricePerService);
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	// methods
	@Override
	public String toString() {
		String pricePerRoom = "";
		for (Map.Entry<RoomType, Double> entry : this.pricePerRoom.entrySet()) {
			String tmp = entry.getKey().getType() + "(" + entry.getKey().getBeds() + ")";
			pricePerRoom += String.format("%-20s | %.2f\n", tmp, entry.getValue());
		}
		String pricePerService = "";
		for (Map.Entry<AdditionalServices, Double> entry : this.pricePerService.entrySet()) {
			pricePerService += String.format("%-10s | %.2f\n", entry.getKey().getService(), entry.getValue());
		}
		return "--------- Cenovnik " + this.id + " ---------\n" + "      Tip sobe       | Cena\n" + pricePerRoom
				+ "------------------------------\n" + "Tip usluge | Cena\n" + pricePerService
				+ "------------------------------\n" + "Datum pocetka: " + this.startDate + "\nDatum kraja: "
				+ this.endDate + "\n------------------------------";
	}

	public String toStringFile() {
		String pricePerRoom = "";
		for (Map.Entry<RoomType, Double> entry : this.pricePerRoom.entrySet()) {
			pricePerRoom += entry.getKey().getId() + "*" + entry.getValue() + " ";
		}
		String pricePerService = "";
		for (Map.Entry<AdditionalServices, Double> entry : this.pricePerService.entrySet()) {
			pricePerService += entry.getKey().getId() + "*" + entry.getValue() + " ";
		}
		return this.id + " | " + pricePerRoom + "| " + pricePerService + "| " + this.startDate + " | " + this.endDate
				+ " | " + isDeleted;
	}
}
