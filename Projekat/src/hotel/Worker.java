package hotel;

import java.time.LocalDate;

enum Qualifications {None, Basic, Intermediate, Advanced}

public class Worker extends User{
	protected Qualifications qualification;
	protected double salary;
	protected int yearsOfExperience;
	
	//constructors
	public Worker() {
		super();
		this.qualification = Qualifications.None;
		this.salary = 0;
		this.yearsOfExperience = 0;
	}
	public Worker(String firstName, String lastName, Gender gender, LocalDate date, String phone, String address,
			String username, String password, Qualifications qualification, double salary, int yearsOfExperience) {
		super(firstName, lastName, gender, date, phone, address, username, password);
		this.qualification = qualification;
		this.salary = salary;
		this.yearsOfExperience = yearsOfExperience;
	}
	
	//copy constructor
	public Worker(Worker worker) {
		super(worker);
		this.qualification = worker.qualification;
		this.salary = worker.salary;
		this.yearsOfExperience = worker.yearsOfExperience;
	}
	
	//getters
	public Qualifications getQualification() {
		return this.qualification;
	}

	public double getSalary() {
		return this.salary;
	}

	public int getYearsOfExperience() {
		return this.yearsOfExperience;
	}
	
	//setters
	public void setQualification(Qualifications qualification) {
		this.qualification = qualification;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
}
