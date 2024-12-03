package db;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import controller.GeneralException;
import model.Employee;

class TestDBEmployee {
private DBEmployee dbEmployee; 
	
	
	
	@BeforeEach
	void setup() throws SQLException, DataAccessException {
		
	}
	@Test
	void testFindByEmployeeId() throws GeneralException, DataAccessException, SQLException {
		DBEmployee dbEmployee = new DBEmployee();
		
		Employee foundEmployee = new Employee();
		foundEmployee = dbEmployee.findEmployeeByEmployeeId("1", false);
		
		System.out.println(foundEmployee.getfName());
		System.out.println(foundEmployee.getlName());
		System.out.println(foundEmployee.getPhoneNo());
		System.out.println(foundEmployee.getEmail());
		System.out.println(foundEmployee.getCpr());
		System.out.println(foundEmployee.getEmployeeId());
		
	}

}
