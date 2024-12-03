package controller;

import model.Employee;

private EmployeeDBIF employeeDBIF;

public class EmployeeController {

	public EmployeeController() {
		employeeDBIF = new EmplyeeDBIF;
	}
public Employee findEmployeeByEmployeeId(int employeeId) {
	return employeeDBIF.findEmployeeByEmployeeId(employeeId);
}
}
