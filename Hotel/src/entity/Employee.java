package entity;

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
		double salary = 0.0;
		double index = 0.0;
		if (this.qualification == Qualifications.NONE) {
			index = 1.0;
		} else if (this.qualification == Qualifications.BASIC) {
			index = 1.5;
		} else if (this.qualification == Qualifications.INTERMEDIATE) {
			index = 2.0;
		} else if (this.qualification == Qualifications.ADVANCED) {
			index = 2.5;
		}
		double hourlyRate = 150.0 * index;
		if (this.type == Type.Receptionist) {
			salary = 2 * hourlyRate * totalHoursPerWeek * totalWeeksPerMonth;
		} else {
			salary = hourlyRate * totalHoursPerWeek * totalWeeksPerMonth;
		}
		this.setSalary(salary);
	}

	@Override
	public String toString() {
		return "------- Zaposleni " + this.id + " -------\n" + super.toString() + "\nStepen strucne spreme: "
				+ this.qualification + "\nPlata: " + this.salary + "\nGodine staza: " + this.yearsOfExperience
				+ "\nRadno mesto: " + this.type + "\n";
	}

	public String toStringFile() {
		return this.id + " | " + super.toStringFile() + " | " + this.qualification + " | " + this.salary + " | "
				+ this.yearsOfExperience + " | " + this.type;
	}
}
