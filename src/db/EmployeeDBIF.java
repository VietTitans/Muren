package db;
import controller.DataAccessException;
import model.Employee;

public interface EmployeeDBIF {

Employee findEmployeeByEmployeeId(int employeeId, boolean fullAssociation) throws  DataAccessException;
}
