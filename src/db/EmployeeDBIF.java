package db;
import controller.GeneralException;

public interface EmployeeDBIF {

Employee findEmployeeByEmployeeId(int employeeId) throws GeneralException;
}
