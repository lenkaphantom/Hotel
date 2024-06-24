package manage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import entity.AdditionalServices;
import entity.Guest;
import entity.Prices;
import entity.RoomType;
import enumeracije.Gender;

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
	public void addPrices(Map<RoomType, Double> pricePerRoom, Map<AdditionalServices, Double> pricePerService,
			LocalDate startDate, LocalDate endDate) {
		Prices price = new Prices(pricePerRoom, pricePerService, startDate, endDate);
		this.prices.put(price.getId(), price);
	}

	public void removePrices(int id) {
		Prices price = this.prices.get(id);
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

	public void loadPrices(String path, ManageHotel manager) {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" \\| ");
				String[] roomPrices = parts[1].trim().split(" ");
				Map<RoomType, Double> pricePerRoom = new HashMap<>();
				for (String roomPrice : roomPrices) {
					String[] roomParts = roomPrice.split("\\*");
					int roomTypeId = Integer.parseInt(roomParts[0].trim());
					RoomType roomType = manager.getRoomTypesMan().getRoomTypes().get(roomTypeId);
					Double price = Double.parseDouble(roomParts[1].trim());
					pricePerRoom.put(roomType, price);
				}

				String[] servicePrices = parts[2].trim().split(" ");
				Map<AdditionalServices, Double> pricePerService = new HashMap<>();
				for (String servicePrice : servicePrices) {
					String[] serviceParts = servicePrice.split("\\*");
					int serviceId = Integer.parseInt(serviceParts[0].trim());
					AdditionalServices service = manager.getAdditionalServicesMan().getAdditionalServices().get(serviceId);
					Double price = Double.parseDouble(serviceParts[1].trim());
					pricePerService.put(service, price);
				}

				LocalDate startDate = LocalDate.parse(parts[3].trim());
				LocalDate endDate = LocalDate.parse(parts[4].trim());

				addPrices(pricePerRoom, pricePerService, startDate, endDate);
			}
		} catch (IOException e) {
			System.out.println("Greška prilikom čitanja iz fajla.");
		}
	}

	public void writePrices(String path) {
		try {
			FileWriter writer = new FileWriter(path);
			for (Prices price : this.prices.values()) {
				writer.write(price.toStringFile() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Greška prilikom upisa u fajl.");
		}
	}
	
	public void removeServicePrices(int serviceId) {
		for (Prices price : this.prices.values()) {
			for (AdditionalServices service : price.getPricePerService().keySet()) {
				if (service.getId() == serviceId) {
					price.getPricePerService().remove(service);
				}
			}
		}
	}
	
	public void removeRoomTypePrices(int roomId) {
		for (Prices price : this.prices.values()) {
			for (RoomType roomType : price.getPricePerRoom().keySet()) {
				if (roomType.getId() == roomId) {
					price.getPricePerRoom().remove(roomType);
				}
			}
		}
	}
}
