package users;

import java.time.LocalDate;

enum Qualifications {
	None, Basic, Intermediate, Advanced
}

enum Type {
	Administrator, Receptionist, HouseKeeper
}

public abstract class Employee extends User {
	protected Qualifications qualification;
	protected double salary;
	protected int yearsOfExperience;
	protected Type type;

	// constructors
	public Employee() {
		super();
		this.qualification = Qualifications.None;
		this.salary = 0;
		this.yearsOfExperience = 0;
		this.type = Type.HouseKeeper;
	}

	public Employee(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password, Qualifications qualification, double salary, int yearsOfExperience,
			Type type) {
		super(firstName, lastName, gender, date, phone, address, username, password);
		this.qualification = qualification;
		this.salary = salary;
		this.yearsOfExperience = yearsOfExperience;
		this.type = type;
	}

	// getters
	public Qualifications getQualification() {
		return this.qualification;
	}

	public double getSalary() {
		return this.salary;
	}

	public int getYearsOfExperience() {
		return this.yearsOfExperience;
	}

	public Type getType() {
		return this.type;
	}

	// setters
	public void setQualification(Qualifications qualification) {
		this.qualification = qualification;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public void setType(Type type) {
		this.type = type;
	}

	// methods
	public void calculateSalary() {
	}
}
