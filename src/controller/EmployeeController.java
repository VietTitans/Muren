package controller;

import model.Employee;
import db.EmployeeDBIF;

import java.sql.SQLException;

import db.EmployeeDB;

public class EmployeeController {
	private EmployeeDBIF employeeDBIF;


	public EmployeeController() throws DataAccessException, SQLException {
		employeeDBIF = new EmployeeDB();
	}
public Employee findEmployeeByEmployeeId(int employeeId, boolean fullAssertion) throws GeneralException, DataAccessException {
	return employeeDBIF.findEmployeeByEmployeeId(employeeId, fullAssertion);
}
}
