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

	// copy constructor
	public Employee(Employee employee) {
		super(employee);
		this.qualification = employee.qualification;
		this.salary = employee.salary;
		this.yearsOfExperience = employee.yearsOfExperience;
		this.type = employee.type;
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

	@Override
	public String toString() {
		return super.toString() + "\nQualification: " + this.qualification + "\nSalary: " + this.salary
				+ "\nYears of Experience: " + this.yearsOfExperience + "\nType: " + this.type;
	}
}
