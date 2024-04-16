package users;

import java.time.LocalDate;

public class Administrator extends User {
	// constructors
	public Administrator() {
	}
	
	public Administrator(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password) {
		super(firstName, lastName, gender, date, phone, address, username, password);
	}

	// copy constructor
	public Administrator(User user) {
		super(user);
	}
	
	// methods
	
}
