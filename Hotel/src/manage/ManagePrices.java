package manage;

import java.util.ArrayList;
import java.util.List;

import reservations.Prices;

public class ManagePrices {
	private List<Prices> prices;
	
	// constructors
	public ManagePrices() {
	}
	
	public ManagePrices(List<Prices> prices) {
		this.prices = new ArrayList<Prices>(prices);
	}
	
	// copy constructor
	public ManagePrices(ManagePrices managePrices) {
		this.prices = new ArrayList<Prices>(managePrices.prices);
	}
	
	// getters
	public List<Prices> getPrices() {
		return prices;
	}
	
	// setters
	public void setPrices(List<Prices> prices) {
		this.prices = new ArrayList<Prices>(prices);
	}
	
	// methods
	public void addPrices(Prices prices) {
		this.prices.add(prices);
	}
	
	public void removePrices(Prices prices) {
		this.prices.remove(prices);
	}
	
	public void updatePrices(Prices prices, Prices newPrices) {
		this.prices.set(this.prices.indexOf(prices), newPrices);
	}
	
	public void printPrices() {
		for (Prices prices : this.prices) {
			System.out.println(prices.toString());
		}
	}
}
