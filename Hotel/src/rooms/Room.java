package rooms;

import java.time.LocalDate;
import java.util.List;

enum RoomStatus {
	Slobodna, Zauzeta, Spremanje
}

public class Room {
	private RoomType roomType;
	private List<LocalDate> occupiedDates;

	// constructors
	public Room(RoomType roomType, List<LocalDate> occupiedDates) {
		this.roomType = roomType;
		this.occupiedDates = occupiedDates;
	}

	public Room() {
		this.roomType = new RoomType();
        this.occupiedDates = null;
	}
}
