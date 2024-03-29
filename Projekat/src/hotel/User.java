package hotel;

import java.time.LocalDate;

enum Gender {Male, Female, Other}

public class User {
	protected String firstName;
	protected String lastName;
	protected Gender gender;
	protected LocalDate date;
}
