package main;

import manage.ManageHotel;
import view.MainFrame;

public class Hotel {

	public static void main(String[] args) {
		ManageHotel manager = ManageHotel.getInstance();		
		manager.loadData();
		manager.writeData();
		MainFrame mainFrame = new MainFrame();
	}
}
