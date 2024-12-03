package db;
import controller.DataAccessException;
import controller.GeneralException;
import model.Employee;

public interface EmployeeDBIF {

Employee findEmployeeByEmployeeId(String employeeId, boolean fullAsserstion) throws GeneralException, DataAccessException;
}
