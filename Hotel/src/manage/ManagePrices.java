package manage;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import entity.AdditionalServices;
import entity.Prices;
import entity.RoomType;

public class ManagePrices {
	private Map<Integer, Prices> prices;

	// constructors
	public ManagePrices() {
		this.prices = new HashMap<Integer, Prices>();
	}

	// getters
	public Map<Integer, Prices> getPrices() {
		return this.prices;
	}

	// methods
	public void addPrices(Prices price) {
		this.prices.put(price.getId(), price);
	}

	public void removePrices(Prices price) {
		this.prices.remove(price.getId());
	}

	public void changePrices(int id, Map<RoomType, Double> pricePerRoom,
			Map<AdditionalServices, Double> pricePerService, LocalDate startDate, LocalDate endDate) {
		if (!this.prices.containsKey(id)) {
			System.out.println("Cenovnik sa id-jem " + id + " ne postoji.");
			return;
		}

		Prices price = this.prices.get(id);

		if (pricePerRoom != null) {
			price.setPricePerRoom(pricePerRoom);
		}
		if (pricePerService != null) {
			price.setPricePerService(pricePerService);
		}
		if (startDate != null) {
			price.setStartDate(startDate);
		}
		if (endDate != null) {
			price.setEndDate(endDate);
		}
	}

	public void printPrices() {
		for (Prices price : this.prices.values()) {
			System.out.println(price.toString());
		}
	}
}
