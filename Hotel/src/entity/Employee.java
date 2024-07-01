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
			String username, String password, Qualifications qualification, int yearsOfExperience, Type type) {
		super(firstName, lastName, gender, date, phone, address, username, password);
		this.id = idCounter++;
		this.qualification = qualification;
		this.yearsOfExperience = yearsOfExperience;
		this.type = type;
		this.salary = this.calculateSalary();
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
	public double calculateSalary() {
		int totalHoursPerWeek = 40;
		int totalWeeksPerMonth = 4;
		double salary = 0.0;
		double index = 0.0;
		double bonus = 0.0;
		if (this.getQualification() == Qualifications.NONE) {
			index = 1.0;
		} else if (this.getQualification() == Qualifications.BASIC) {
			index = 1.5;
		} else if (this.getQualification() == Qualifications.INTERMEDIATE) {
			index = 2.0;
		} else if (this.getQualification() == Qualifications.ADVANCED) {
			index = 2.5;
		}
		if (this.getYearsOfExperience() > 5) {
			bonus = 1.0;
		} else if (this.getYearsOfExperience() > 10) {
			bonus = 1.5;
		} else if (this.getYearsOfExperience() > 15) {
			bonus = 2.0;
		} else if (this.getYearsOfExperience() > 20) {
			bonus = 2.5;
		} else if (this.getYearsOfExperience() > 25) {
			bonus = 3.0;
		}

		double hourlyRate = 4.0 * index;
		if (this.getType() == Type.Receptionist) {
			salary = 2 * hourlyRate * totalHoursPerWeek * totalWeeksPerMonth * bonus;
		} else {
			salary = hourlyRate * totalHoursPerWeek * totalWeeksPerMonth * bonus;
		}
		return salary;
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
