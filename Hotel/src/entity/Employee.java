package users;

import java.time.LocalDate;

import enumeracije.Gender;
import enumeracije.Qualifications;
import enumeracije.Type;

public abstract class Employee extends User {
	private static int idCounter = 1;
	private int id;
	private Qualifications qualification;
	private double salary;
	private int yearsOfExperience;
	private Type type;

	// constructors
	public Employee() {
		super();
		this.id = idCounter++;
	}

	public Employee(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password, Qualifications qualification, double salary, int yearsOfExperience,
			Type type) {
		super(firstName, lastName, gender, date, phone, address, username, password);
		this.id = idCounter++;
		this.qualification = qualification;
		this.salary = salary;
		this.yearsOfExperience = yearsOfExperience;
		this.type = type;
	}

	// copy constructor
	public Employee(Employee employee) {
		super(employee);
		this.id = idCounter++;
		this.qualification = employee.qualification;
		this.salary = employee.salary;
		this.yearsOfExperience = employee.yearsOfExperience;
		this.type = employee.type;
	}

	// getters
	public int getId() {
		return this.id;
	}
	
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
		int totalHoursPerWeek = 40;
		int totalWeeksPerMonth = 4;
	}
	
	@Override
	public String toString() {
		return super.toString() + " | " + this.qualification + " | " + this.salary + " | " + this.yearsOfExperience
				+ " | " + this.type;
	}
}
