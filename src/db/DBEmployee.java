package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DataAccessException;
import controller.GeneralException;
import model.Address;
import model.Employee;

public class DBEmployee implements EmployeeDBIF {

private static final String FIND_EMPLOYEE = " select * FROM Employee,Person";
private PreparedStatement findAllEmployees; 
private static final String FIND_ADDRESS = " select * FROM Address,Zipcodes where AddressId = ?";
private PreparedStatement findAddress; 
private static final String FIND_BY_EMPLOYEEID = FIND_EMPLOYEE + " where Employee.PersonId = Person.PersonId AND employeeId = ? ";
private PreparedStatement find_by_id;
	
public DBEmployee() throws SQLException, DataAccessException {
	findAllEmployees = DBConnection.getInstance().getConnection().prepareStatement(FIND_EMPLOYEE);
	findAddress = DBConnection.getInstance().getConnection().prepareStatement(FIND_ADDRESS);
	find_by_id =  DBConnection.getInstance().getConnection().prepareStatement(FIND_BY_EMPLOYEEID);
}
	





@Override
public Employee findEmployeeByEmployeeId(String employeeId, boolean fullAssertion) throws GeneralException, DataAccessException {
	Employee foundEmployee = new Employee(null, null,null,null,null,null);
	
	try {
		find_by_id.setString(1,employeeId);
		ResultSet resultSet = find_by_id.executeQuery();
		
		if(resultSet.next()) {
			foundEmployee  = buildObject(resultSet, fullAssertion);
		}
	} catch (SQLException e) {
		throw new DataAccessException("Can't find Employee by EmployeeId",e);
	}
	
	return foundEmployee;
}

public Employee buildObject(ResultSet resultSet, boolean fullAssertion) throws DataAccessException {
	Employee foundEmployee = new Employee();

	try {
		foundEmployee.setfName(resultSet.getString("FirstName"));
		foundEmployee.setlName(resultSet.getString("LastName"));
		foundEmployee.setPhoneNo(resultSet.getString("phoneNo"));
		foundEmployee.setEmail(resultSet.getString("Email"));
		foundEmployee.setAddress(null);
		//Enum i dette tilfælge er et problem 
//		foundEmployee.setEmployeeType(resultSet.getString("EmployeeType"));
		foundEmployee.setCpr(resultSet.getString("cpr"));
		foundEmployee.setEmployeeId(resultSet.getInt("EmployeeId"));
		
		
		if(fullAssertion == true) {
			String addressId = String.valueOf(resultSet.getInt("AddressId"));
			findAddress.setString(1, addressId);
			ResultSet addressResultSet = findAddress.executeQuery();
			
			if(addressResultSet.next()) {
				Address foundAddress = new Address();
				foundAddress.setStreetName(addressResultSet.getString("StreetName"));
				foundAddress.setBuildingNo(addressResultSet.getString("BuildingNo"));
				foundAddress.setFloor(addressResultSet.getInt("FloorNo"));
				foundAddress.setZipCode(addressResultSet.getString("zipcode"));
				foundAddress.setCity(addressResultSet.getString("City"));
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
