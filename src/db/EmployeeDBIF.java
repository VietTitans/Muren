package db;
import controller.DataAccessException;
import controller.GeneralException;
import model.Employee;

public interface EmployeeDBIF {

Employee findEmployeeByEmployeeId(int employeeId, boolean fullAsserstion) throws GeneralException, DataAccessException;
}
