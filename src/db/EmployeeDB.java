package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DataAccessException;
import controller.GeneralException;
import model.Address;
import model.Employee;
import model.EmployeeType;

public class EmployeeDB implements EmployeeDBIF {

	private static final String FIND_EMPLOYEE = " select * FROM Employee,Person,EmployeeType ";
	private PreparedStatement findAllEmployees;
	private static final String FIND_ADDRESS = " select * FROM Address,Zipcodes where AddressId = ?";
	private PreparedStatement findAddress;
	private static final String FIND_BY_EMPLOYEEID = FIND_EMPLOYEE
			+ " where Employee.PersonId = Person.PersonId AND  Employee.EmployeeTypeNo = EmployeeType.EmployeeTypeNo AND employeeId = ?  ";
	private PreparedStatement find_by_id;

	public EmployeeDB() throws SQLException, DataAccessException {
		findAllEmployees = DBConnection.getInstance().getConnection().prepareStatement(FIND_EMPLOYEE);
		findAddress = DBConnection.getInstance().getConnection().prepareStatement(FIND_ADDRESS);
		find_by_id = DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_EMPLOYEEID);
	}

	@Override
	public Employee findEmployeeByEmployeeId(int employeeId, boolean fullAssociation) 
			throws GeneralException, DataAccessException {
		Employee foundEmployee = null;

		try {
			find_by_id.setString(1, String.valueOf(employeeId));
			ResultSet resultSet = find_by_id.executeQuery();

			if (resultSet.next()) {
				foundEmployee = buildObject(resultSet, fullAssociation);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Can't find Employee by EmployeeId", e);
		}

		return foundEmployee;
	}

	public Employee buildObject(ResultSet resultSet, boolean fullAssociation) throws DataAccessException {
		Employee foundEmployee = new Employee();

		try {
			foundEmployee.setfName(resultSet.getString("FirstName"));
			foundEmployee.setlName(resultSet.getString("LastName"));
			foundEmployee.setPhoneNo(resultSet.getString("phoneNo"));
			foundEmployee.setEmail(resultSet.getString("Email"));
			foundEmployee.setAddress(null);
			String employeeTypeString = resultSet.getString("EmployeeTypeName");
	        if (employeeTypeString != null) {
	            foundEmployee.setEmployeeType(EmployeeType.valueOf(employeeTypeString.toUpperCase()));
	        }
			foundEmployee.setCpr(resultSet.getString("cpr"));
			foundEmployee.setEmployeeId(resultSet.getInt("EmployeeId"));

			if (fullAssociation == true) {
				String addressId = String.valueOf(resultSet.getInt("AddressId"));
				findAddress.setString(1, addressId);
				ResultSet addressResultSet = findAddress.executeQuery();

				if (addressResultSet.next()) {
					Address foundAddress = new Address();
					foundAddress.setStreetName(addressResultSet.getString("StreetName"));
					foundAddress.setBuildingNo(addressResultSet.getString("BuildingNo"));
					foundAddress.setFloor(addressResultSet.getInt("FloorNo"));
					foundAddress.setZipCode(addressResultSet.getString("zipcode"));
					foundAddress.setCity(addressResultSet.getString("CityName"));
					foundAddress.setCountry(addressResultSet.getString("Country"));
					foundEmployee.setAddress(foundAddress);

				}
			}

		} catch (SQLException e) {
			throw new DataAccessException("Could Not Build", e);
		}

		return foundEmployee;

	}
}
