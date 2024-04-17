package manage;

import java.util.ArrayList;
import java.util.List;

import users.Employee;

public abstract class ManageEmployers {
	private List<Employee> employees;
	
	// constructors
	public ManageEmployers() {
	}
	
	public ManageEmployers(List<Employee> employees) {
		this.employees = new ArrayList<Employee>(employees);
	}
	
	// copy constructor
	public ManageEmployers(ManageEmployers manageEmployers) {
		this.employees = new ArrayList<Employee>(manageEmployers.employees);
	}
	
	// getters
	public List<Employee> getEmployees() {
		return employees;
	}
	
	// setters
	public void setEmployees(List<Employee> employees) {
		this.employees = new ArrayList<Employee>(employees);
	}
	
	// methods
	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}
	
	public void removeEmployee(Employee employee) {
		this.employees.remove(employee);
	}
	
	public void changeEmployee(Employee oldEmployee, Employee newEmployee) {
		this.employees.set(this.employees.indexOf(oldEmployee), newEmployee);
	}
	
	public void printEmployees() {
		for (Employee employee : employees) {
			System.out.println(employee.toString());
		}
	}
}
