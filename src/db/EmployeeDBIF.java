package db;
import controller.GeneralException;
import model.Employee;

public interface EmployeeDBIF {

	public Employee findEmployeeByEmployeeId(int employeeId) throws GeneralException;
}
