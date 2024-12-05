package model;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.DataAccessException;
import db.DBConnection;
import db.MaterialDB;

public class TestStockReservation {
	
	static MaterialDB materialDB;
	static DBConnection connection;
	
	@BeforeAll
	void setUp() throws DataAccessException {
		materialDB = new MaterialDB();
		connection = DBConnection.getInstance();
		connection.getConnection();
	}
	
	@AfterAll
	void tearDown() throws DataAccessException {
		connection.disconnect();
	}

	@Test
	public void testStockReservationConstructor() {
		//Arrange
		
		//Act
		StockReservation stockReservation = new StockReservation(20, LocalDateTime.now(), 7, 7, false);
		//Assert
		assertNotNull(stockReservation);
	}
	
	@Test
	void testFindAndAddStockReservationByStockMaterialId() throws DataAccessException {
		//TODO:STUBS
	}
	
}
