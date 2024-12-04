package db;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DataAccessException;
import model.Customer;
import model.Address;


public class CustomerDB implements CustomerDBIF {
	
	private static final String FIND_CUSTOMER ="select * FROM Customer,Person ";
	private PreparedStatement findAllCustomers;
	
	private static final String FIND_BY_PHONENO = FIND_CUSTOMER + "where Customer.PersonId = Person.PersonId AND phoneNo = ?";
	private PreparedStatement findByPhoneNo;
	
	public CustomerDB() throws DataAccessException {
		try {
			findAllCustomers = DBConnection.getInstance().getConnection()
					.prepareStatement(FIND_CUSTOMER);
			findByPhoneNo = DBConnection.getInstance().getConnection()
					.prepareStatement(FIND_BY_PHONENO);
		}
		catch (SQLException e) {
			throw new DataAccessException("Statement could not be prepared", e);
		}
	}

	@Override
	public Customer findCustomerByPhoneNo(String phoneNo, boolean fullAssertion) throws DataAccessException {
		Customer foundCustomer = new Customer();
		try {
			findByPhoneNo.setString(1, phoneNo);
			ResultSet resultSet = findByPhoneNo.executeQuery();
			
			if (resultSet.next()) {
				foundCustomer = buildObject(resultSet, false);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not find Customer by PhoneNo", e);
		}
		return foundCustomer;
		
	
	}

	public Customer buildObject(ResultSet resultSet, boolean fullAssertion) throws SQLException, DataAccessException{
		Customer customer = new Customer();
		
		try {
			customer.setfName(resultSet.getString("FirstName"));
			customer.setlName(resultSet.getString("LastName"));
			customer.setPhoneNo(resultSet.getString("PhoneNo"));
			customer.setEmail(resultSet.getString("Email"));
//			customer.setAddress(resultSet.getObject("Adress"));
			
		if (fullAssertion = true) {
			// TODO Finish Method when a method requires the use of an Address
		}
		} catch (SQLException e) {
			throw new DataAccessException("Could not Build Object", e);
		}
		return customer;
	
	}
}