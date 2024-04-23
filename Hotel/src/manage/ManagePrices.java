package manage;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import reservations.AdditionalServices;
import reservations.Prices;
import rooms.RoomType;

public class ManagePrices {
	private static Map<Integer, Prices> prices = new HashMap<Integer, Prices>();

	// constructors
	private ManagePrices() {}

	// getters
	public static Map<Integer, Prices> getPrices() {
		return prices;
	}

	// methods
	public static void addPrices(Prices price) {
		prices.put(price.getId(), price);
	}

	public static void removePrices(Prices price) {
		prices.remove(price.getId());
	}

	public static void changePrices(int id, Map<RoomType, Double> pricePerRoom,
			Map<AdditionalServices, Double> pricePerService, LocalDate startDate, LocalDate endDate) {
		if (!prices.containsKey(id)) {
			System.out.println("Cenovnik sa id-jem " + id + " ne postoji.");
			return;
		}

		Prices price = prices.get(id);

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

	public static void printPrices() {
		for (Prices price : prices.values()) {
			System.out.println(price.toString());
		}
	}
}
