package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import db.CustomerDB;
import db.DBConnection;

class TestCustomerController {

	static CustomerDB customerDB;
	private DBConnection connection;

	@BeforeAll
	void setUp() throws Exception {
		customerDB = new CustomerDB();
		connection = DBConnection.getInstance();
		connection.getConnection();
	}

	@AfterAll
	void tearDown() throws DataAccessException {
		connection.disconnect();
	}

	@Test
	void testFindCustomerByPhoneNo() throws DataAccessException {
		//TODO: STUB
	}
	
	
	//Material tests
	@Test
	void testMaterialDoesntExists() throws DataAccessException {
		//TODO:STUBS
	}
	
	@Test
	void testNegativeMaterialInput() throws DataAccessException {
		//TODO:STUBS
	}
	
	@Test
	void testZeroMaterialInput() throws DataAccessException {
		//TODO:STUBS
	}
	

}
