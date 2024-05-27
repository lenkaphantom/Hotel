package main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.AdditionalServices;
import manage.ManageHotel;

public class Hotel {

	public static void main(String[] args) {
		ManageHotel manager = ManageHotel.getInstance();		
		manager.loadData();
		
		List<AdditionalServices> additionalServices1 = new ArrayList();
		additionalServices1.add(manager.getAdditionalServicesMan().getAdditionalServices().get(1));
		additionalServices1.add(manager.getAdditionalServicesMan().getAdditionalServices().get(3));
		
		List<AdditionalServices> temp = new ArrayList();
		temp.add(manager.getAdditionalServicesMan().getAdditionalServices().get(5));
		manager.makeReservation(1, 5, LocalDate.of(2024, 5, 27), LocalDate.of(2024, 6, 3), additionalServices1);
		
		manager.addRoomToReservation(4);
		manager.addRoomToReservation(3);
		manager.checkIn(4, temp);
		
		manager.checkOut(4);
		manager.cleanRooms(2);
		System.out.println(manager.getRoomsMan().getRooms().get(2));
		
		manager.makeReservation(2, 5, LocalDate.of(2024, 5, 29), LocalDate.of(2024, 6, 1), additionalServices1);
		manager.addRoomToReservation(5);
		manager.getReservationsMan().printReservations();
		manager.writeData();
		// MainFrame mainFrame = new MainFrame();
	}
}
