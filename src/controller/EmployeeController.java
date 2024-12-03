package controller;

import model.Employee;
import db.EmployeeDBIF;
import db.EmployeeDB;

private EmployeeDBIF employeeDBIF;

public class EmployeeController {

	public EmployeeController() {
		employeeDBIF = new EmployeeDB();
	}
public Employee findEmployeeByEmployeeId(int employeeId) {
	return employeeDBIF.findEmployeeByEmployeeId(employeeId);
}
}
