package controller;

import java.sql.SQLException;

import db.EmployeeDB;
import db.EmployeeDBIF;
import model.Employee;

public class EmployeeController {
	private EmployeeDBIF employeeDBIF;

	public EmployeeController() throws DataAccessException, SQLException {
		employeeDBIF = new EmployeeDB();
	}

	public Employee findEmployeeByEmployeeId(int employeeId, boolean fullAssociation) throws  DataAccessException {
		return employeeDBIF.findEmployeeByEmployeeId(employeeId, fullAssociation);
	}
}
