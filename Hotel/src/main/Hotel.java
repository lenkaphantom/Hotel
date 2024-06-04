package main;

import manage.ManageHotel;
import view.MainFrame;

public class Hotel {

	public static void main(String[] args) {
		ManageHotel manager = ManageHotel.getInstance();		
		manager.loadData();
		System.out.println("hello");
		MainFrame mainFrame = new MainFrame();
		manager.writeData();
	}
}
