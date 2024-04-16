package users;

import java.time.LocalDate;

enum Gender {
	Male, Female, Other
}

public abstract class User {
	protected String firstName;
	protected String lastName;
	protected Gender gender;
	protected LocalDate date;
	protected String phone;
	protected String address;
	protected String username;
	protected String password;

	// constructors
	public User() {
	}

	public User(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.date = date;
		this.phone = phone;
		this.address = address;
		this.username = username;
		this.password = password;
	}

	// copy constructor
	public User(User user) {
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.gender = user.gender;
		this.date = user.date;
		this.phone = user.phone;
		this.address = user.address;
		this.username = user.username;
		this.password = user.password;
	}

	// getters
	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public Gender getGender() {
		return this.gender;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public String getPhone() {
		return this.phone;
	}

	public String getAddress() {
		return this.address;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	// setters
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// methods
	@Override
	public String toString() {
		return "First Name: " + this.firstName + "\nLast Name: " + this.lastName + "\nGender: " + this.gender
				+ "\nDate of Birth: " + this.date + "\nPhone: " + this.phone + "\nAddress: " + this.address
				+ "\nUsername: " + this.username + "\nPassword: " + this.password;
	}
}
